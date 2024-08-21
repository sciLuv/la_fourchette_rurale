package fr.eql.lyra.dao.impl;


import fr.eql.lyra.dao.DeliveryManADao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.DeliveryAMan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Remote(DeliveryManADao.class)
@Stateless
public class DeliveryManADaoImpl implements DeliveryManADao {

	private static final Logger logger = LogManager.getLogger();

	private static final String REQ_AUTH_DELIVERY = "SELECT * FROM livreur WHERE mail = ? AND mot_de_passe = ?";
//	private static final String REQ_FIND_SESSION = "SELECT * FROM session WHERE token = ?";
//	private static final String REQ_UPDATE_SESSION = "INSERT INTO session (token, timestamp, member_id) VALUES (?,?,?) " +
//		"ON DUPLICATE KEY UPDATE token = ?, timestamp = ?";
//	private static final String REQ_FIND_ID_BY_TOKEN = "SELECT id FROM session WHERE token = ?";
	private static final String REQ_ROLE_BY_ID_MEMBER = "SELECT role FROM deliveryman WHERE id = ?";
	private static final String REQ_FIND_ALL_BUT_SELF_DELIVERY = "SELECT * FROM id_livreur WHERE id_livreur != ? ORDER BY nom";

	private final DataSource dataSource = new LaFourchetteRuraleDataSource();

	@Override
	public DeliveryAMan authenticateDeliveryMan(String username, String password) {
		DeliveryAMan deliveryAMan = null;
		try (Connection connection = dataSource.getConnection()){
			PreparedStatement statement = connection.prepareStatement(REQ_AUTH_DELIVERY);
			statement.setString(1, username);
			statement.setString(2, password);
			System.out.println(statement);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				deliveryAMan = new DeliveryAMan(
						resultSet.getInt("id_livreur"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("mot_de_passe"),
						resultSet.getString("mail"),
						resultSet.getString("role")
				);
				System.out.println(deliveryAMan.getMail());
				System.out.println(deliveryAMan.getPassword());

			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite " +
					"lors de la consultation du propriétaire en base de données", e);
		}
		return deliveryAMan;
	}

	@Override
	public List<DeliveryAMan> findAllButSelfDeliveryMan(int deliveryManId) {
		List<DeliveryAMan> deliveryMen = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()){
			PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_BUT_SELF_DELIVERY);
			statement.setLong(1, deliveryManId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				deliveryMen.add(new DeliveryAMan(
						resultSet.getInt("id_livreur"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("mot_de_passe"),
						resultSet.getString("mail"),
				        resultSet.getString("role")));
			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite " +
					"lors de la consultation des propriétaire en base de données", e);
		}
		return deliveryMen;
	}
}
