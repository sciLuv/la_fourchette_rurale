package fr.eql.lyra.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.eql.lyra.dao.DeliveryRDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.dto.DeliveryinformationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.ws.rs.PathParam;


@Remote(DeliveryRDao.class)
@Stateless
public class DeliveryRDaoImpl implements DeliveryRDao{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_PACKAGE_FROM_DATE_AND_TOWN =
            "SELECT DISTINCT cl.id_colis " +
                    "FROM ville v " +
                    "JOIN adh a ON v.ID_ville = a.ID_ville " +
                    "JOIN commande com ON a.id_adherent = com.id_adherent " +
                    "JOIN detail_com det ON com.id_commande = det.id_commande " +
                    "JOIN colis cl ON cl.id_colis = det.id_colis " +
                    "WHERE v.ID_ville = ? AND cl.livraison_prevu = ? AND cl.livraison_effective IS NULL";

    private static final String REQ_DELIVERY_INFORMATION =
            "SELECT DISTINCT a.nom, a.prenom, a.rue_livraison, v.Nom_ville, cl.id_colis, a.id_adherent\n" +
                    "FROM ville v \n" +
                    "JOIN adh a ON v.ID_ville = a.ID_ville \n" +
                    "JOIN commande com ON a.id_adherent = com.id_adherent \n" +
                    "JOIN detail_com det ON com.id_commande = det.id_commande \n" +
                    "JOIN colis cl ON cl.id_colis = det.id_colis \n" +
                    "WHERE cl.id_colis = ?";

    private static final String UPDATE_TOURNEE_BEGIN =
                    "UPDATE tournee\n" +
                    "SET debut_tournee = ?\n" +
                    "WHERE id_tournee = ?";

    private static final String REQ_DELIVERY_MEMBER_INFO =
            "SELECT adh.nom, adh.prenom, adh.telephone, adh.rue_livraison, adh.information_supplementaire  FROM adh WHERE id_adherent = ?";


    private static final String UPDATE_PACKAGE_END_DELIVERY_SUCCESS =
    "UPDATE colis SET livraison_effective = ? WHERE id_colis = ?";

    private static final String UPDATE_PACKAGE_END_DELIVERY_FAIL =
    "UPDATE colis SET livraison_effective = ?, id_echec_livraison = ? WHERE id_colis = ?";

    private static final String UPDATE_END_TOURNEE =
    "UPDATE tournee SET fin_tournee = ? WHERE id_tournee = ?";


    private static final String FIND_TOWN =
            "SELECT Nom_ville FROM ville\n" +
                    "WHERE ID_Ville = ?";
    public List<DeliveryinformationDto> findAndUploadPackage(int id, int idVille, LocalDate dateOfDelivery) throws SQLException {

        System.out.println("TEST");
        List<DeliveryinformationDto> deliveryinformationDto = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            System.out.println("TEST2");
            String dateStringDelivery = dateOfDelivery.toString() + " 01:00:00";
            LocalDateTime localDateTimeDelivery  = LocalDateTime.parse(dateStringDelivery, formatter);
            Timestamp timestampPackage = Timestamp.valueOf(localDateTimeDelivery);
            System.out.println("TEST2");
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_PACKAGE_FROM_DATE_AND_TOWN);
            statement.setLong(1, idVille);
            statement.setTimestamp(2, timestampPackage);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int orderDetailId = resultSet.getInt("id_colis");

                System.out.println(orderDetailId);

                // Mettre à jour l'id de tournée pour chaque colis trouvé
                PreparedStatement updateStatement = connection.prepareStatement("UPDATE colis SET id_tournee = ? WHERE id_colis = ?");
                updateStatement.setInt(1, id);
                updateStatement.setInt(2, orderDetailId);
                updateStatement.executeUpdate();

                // Récupérer les informations de livraison pour chaque colis modifié
                PreparedStatement statement2 = connection.prepareStatement(REQ_DELIVERY_INFORMATION);
                statement2.setInt(1, orderDetailId);
                System.out.println(statement2);
                ResultSet resultSet2 = statement2.executeQuery();
                if (resultSet2.next()) {
                    deliveryinformationDto.add(new DeliveryinformationDto(
                            resultSet2.getString("a.nom"),
                            resultSet2.getString("a.prenom"),
                            resultSet2.getString("a.rue_livraison"),
                            resultSet2.getString("v.Nom_ville"),
                            resultSet2.getLong("cl.id_colis"),
                            resultSet2.getLong("a.id_adherent")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }
        return deliveryinformationDto;
    }

    public String addANewBeginDate(int tourneeId, LocalDateTime localDateTime){
        // Définissez le format de date et d'heure requis
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Formatez la date et l'heure actuelles
        String formattedDateTime = localDateTime.plusHours(1).format(formatter);
        // Affichez la date et l'heure formatées
        System.out.println(formattedDateTime);
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_TOURNEE_BEGIN);
            statement.setTimestamp(1, Timestamp.valueOf(formattedDateTime));
            statement.setLong(2,tourneeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }


        return "toto";
    }

    public Member findDeliveryInfoMember(long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_DELIVERY_MEMBER_INFO);
            statement.setLong(1, id); // Définir le paramètre ID
            ResultSet resultSet = statement.executeQuery();

            // S'assurer qu'il y a un résultat
            if (resultSet.next()) {
                Member member = new Member(
                        resultSet.getString("nom"), // Assurez-vous que les noms de colonnes sont corrects
                        resultSet.getString("prenom"),
                        resultSet.getInt("telephone"), // Utilisez getInt si le téléphone est stocké en tant qu'entier
                        resultSet.getString("rue_livraison"),
                        resultSet.getString("information_supplementaire")
                );
                return member;
            } else {
                // Aucun résultat trouvé avec cet ID
                return null;
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des propriétaires en base de données", e);
            return null;
        }
    }

    public String endOfDelivery(int nolivraisonId, int packageId){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Formatez la date et l'heure actuelles
        String formattedDateTime = currentDateTime.plusHours(1).format(formatter);

        if(nolivraisonId != 0){
            System.out.println("Test");
            try (Connection connection = dataSource.getConnection()){
                PreparedStatement statement = connection.prepareStatement(UPDATE_PACKAGE_END_DELIVERY_FAIL);
                statement.setTimestamp(1, Timestamp.valueOf(formattedDateTime));
                statement.setLong(2,nolivraisonId);
                statement.setLong(3,packageId);
                statement.executeUpdate();
                return "true";
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
                return "false";
            }
        } else {
            System.out.println("test2");
            try (Connection connection = dataSource.getConnection()){
                PreparedStatement statement = connection.prepareStatement(UPDATE_PACKAGE_END_DELIVERY_SUCCESS);
                statement.setTimestamp(1, Timestamp.valueOf(formattedDateTime));
                statement.setLong(2,packageId);
                statement.executeUpdate();
                return "true";
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
                return "false";
            }
        }
}

    public List<String> findTownById(int[] ids) {
        List<String> towns = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(FIND_TOWN);
            for (int id : ids) {
                statement.setInt(1, id); // Définir le paramètre ID
                ResultSet resultSet = statement.executeQuery();
                // S'assurer qu'il y a un résultat
                if (resultSet.next()) {
                    towns.add(resultSet.getString("Nom_ville"));
                } else {
                    // Aucun résultat trouvé avec cet ID, ajouter null à la liste des villes
                    towns.add(null);
                }
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des propriétaires en base de données", e);
            return null;
        }
        return towns;
    }

    public void endOfTournee(int id) {
        String sql = "UPDATE tournee SET fin_tournee = ? WHERE id_tournee = ?";

        try (Connection connection = dataSource.getConnection()) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Formatez la date et l'heure actuelles
            String formattedDateTime = currentDateTime.plusHours(1).format(formatter);

            PreparedStatement statement = connection.prepareStatement(UPDATE_END_TOURNEE);
            statement.setTimestamp(1, Timestamp.valueOf(formattedDateTime));
            statement.setLong(2,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la tournée : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {

        int[] ids = {1, 2, 3, 4, 5};
        DeliveryRDaoImpl d = new DeliveryRDaoImpl();


    }
}
