package fr.eql.lyra.dao.impl;


import fr.eql.lyra.dao.MemberADao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.MemberA;
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

@Remote(MemberADao.class)
@Stateless
public class MemberADaoImpl implements MemberADao {

	private static final Logger logger = LogManager.getLogger();

	private static final String REQ_AUTH = "SELECT * FROM adh WHERE mail = ? AND mot_de_passe = ?";
//	private static final String REQ_FIND_SESSION = "SELECT * FROM session WHERE token = ?";
//	private static final String REQ_UPDATE_SESSION = "INSERT INTO session (token, timestamp, member_id) VALUES (?,?,?) " +
//		"ON DUPLICATE KEY UPDATE token = ?, timestamp = ?";
//	private static final String REQ_FIND_ID_BY_TOKEN = "SELECT id FROM session WHERE token = ?";
	private static final String REQ_ROLE_BY_ID_MEMBER = "SELECT role FROM member WHERE id = ?";
	private static final String REQ_FIND_ALL_BUT_SELF = "SELECT * FROM id_adherent WHERE id_adherent != ? ORDER BY nom";

	private final DataSource dataSource = new LaFourchetteRuraleDataSource();

	@Override
	public MemberA authenticate(String username, String password) {
		MemberA memberA = null;
		try (Connection connection = dataSource.getConnection()){
			PreparedStatement statement = connection.prepareStatement(REQ_AUTH);
			statement.setString(1, username);
			statement.setString(2, password);
			System.out.println(statement);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				memberA = new MemberA(
						resultSet.getLong("id_adherent"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("mail"),
						resultSet.getString("mot_de_passe"),
						resultSet.getLong("telephone"),
				        resultSet.getLong("id_genre"),
						resultSet.getString("rue_livraison"),
						resultSet.getString("information_supplementaire"),
						resultSet.getString("adresse_facture"),
						resultSet.getLong("numero_carte_bancaire"),
						resultSet.getDate("date_validite_carte_bancaire") != null ?
								resultSet.getDate("date_validite_carte_bancaire").toLocalDate() : null,
						resultSet.getString("nom_porteur_carte_bancaire"),
						resultSet.getDate("naissance") != null ?
								resultSet.getDate("naissance").toLocalDate() : null,
						resultSet.getLong("id_regime_alimentaire"),
						resultSet.getLong("ID_ville"),
						resultSet.getLong("Vil_ID_ville"),
						resultSet.getString("role")

				);
				System.out.println(memberA.getMail());
				System.out.println(memberA.getPassword());

			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite " +
					"lors de la consultation du propriétaire en base de données", e);
		}
		return memberA;
	}

	@Override
	public List<MemberA> findAllButSelf(long id) {
		List<MemberA> memberAS = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()){
			PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_BUT_SELF);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				memberAS.add(new MemberA(
						resultSet.getLong("id_adherent"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("mail"),
						resultSet.getString("mot_de_passe"),
						resultSet.getLong("telephone"),
						resultSet.getLong("id_genre"),
						resultSet.getString("rue_livraison"),
						resultSet.getString("information_supplementaire"),
						resultSet.getString("adresse_facture"),
						resultSet.getLong("numero_carte_bancaire"),
						resultSet.getDate("date_validite_carte_bancaire") != null ?
								resultSet.getDate("date_validite_carte_bancaire").toLocalDate() : null,
						resultSet.getString("nom_porteur_carte_bancaire"),
						resultSet.getDate("naissance") != null ?
								resultSet.getDate("naissance").toLocalDate() : null,
						resultSet.getLong("id_regime_alimentaire"),
						resultSet.getLong("ID_ville"),
						resultSet.getLong("Vil_ID_ville"),
				        resultSet.getString("role")));
			}
		} catch (SQLException e) {
			logger.error("Une erreur s'est produite " +
					"lors de la consultation des propriétaire en base de données", e);
		}
		return memberAS;
	}
}
