package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.DeliveryManDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.DeliveryMan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Remote(DeliveryManDao.class)
@Stateless
public class DeliveryManDaoImpl implements DeliveryManDao {

    private final DataSource dataSource = new LaFourchetteRuraleDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_AUTH = "SELECT * FROM livreur WHERE mail = ? AND mot_de_passe = ? ";
    private static final String REQ_UPDATE_PASSWORD = "UPDATE livreur SET mot_de_passe = ? WHERE mail = ?";
    private static final String REQ_FIND_ALL = "SELECT * FROM livreur";

    @Override
    public DeliveryMan authenticate(String email, String password) {
        DeliveryMan deliveryMan = null;
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_AUTH);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                deliveryMan = new DeliveryMan(
                        resultSet.getInt("id_livreur"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("mail"),
                        resultSet.getString("mot_de_passe")
                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation du livreur en base de données", e);
        }
        return deliveryMan;
    }


    @Override
    public void updatePassword(String email, String newPassword) {

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_PASSWORD);
            statement.setString(1, newPassword);
            statement.setString(2, email);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                logger.error("Aucune ligne mise à jour pour l'email : {}", email);
            } else {
                logger.info("Mot de passe mis à jour avec succès pour l'email : {}", email);
            }
        } catch (SQLException e) {
            logger.error("Une erreur est survenue lors de la mise à jour du mot de passe pour l'email : {}", email, e);
        }

    }

    @Override
    public List<DeliveryMan> findAllDeliveryMans() {
        List<DeliveryMan> deliveryMans = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_ALL);
            while (resultSet.next()) {
                int deliveryManId = resultSet.getInt("id_livreur");
                String lastName = resultSet.getString("nom");
                String firstName = resultSet.getString("prenom");
                String email = resultSet.getString("mail");
                String password = resultSet.getString("mot_de_passe");
                deliveryMans.add(new DeliveryMan(deliveryManId, lastName, firstName, email, password));

            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des villes en base de données", e);
        }
        return deliveryMans;
    }
    }


//        public static void main(String[] args) {
//            // 创建 DeliveryManDaoImpl 实例
//            DeliveryManDao deliveryManDao = new DeliveryManDaoImpl();
//
//            // 指定 Martin Julien 的邮箱和新密码
//            String email = "julien.martin@bonjour.fr";
//            String newPassword = "newPassword123";
//
//            // 调用 updatePassword 方法来更新密码
//            deliveryManDao.updatePassword(email, newPassword);
//        }

//}



