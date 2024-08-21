package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.MemberDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.*;
import fr.eql.lyra.entity.dto.Member1Dto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Remote(MemberDao.class)
@Stateless
public class MemberDaoImpl implements MemberDao {
    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String ADD_NEW_USER =
            "INSERT INTO ADH (nom, prenom, mail, mot_de_passe) VALUES\n" +
                    "(?, ?, ?, ?)";

    private static final String ADD_NEW_MEMBER =
            "UPDATE adh\n" +
                    "SET id_genre = ?,\n" +
                    "    id_regime_alimentaire = ?,\n" +
                    "    ID_ville = ?,\n" +
                    "    Vil_ID_ville = ?,\n" +
                    "    telephone = ?,\n" +
                    "    naissance = ?,\n" +
                    "    rue_livraison = ?,\n" +
                    "    information_supplementaire = ?,\n" +
                    "    adresse_facture = ?,\n" +
                    "    numero_carte_bancaire = ?,\n" +
                    "    date_validite_carte_bancaire = ?,\n" +
                    "    nom_porteur_carte_bancaire = ?\n" +
                    "WHERE id_adherent = ?";

    private static final String ADD_NEW_FOOD_SPECIFICITY_FAVORITE =
            "INSERT INTO prefere (id_adherent, id_specificite_alimentaire) VALUES (?, ?)";
    private static final String ADD_NEW_MEMBERSHIP =
            "INSERT INTO adhesion " +
                    "(id_type_adhesion, id_adherent, debut, fin_prevu, fin_effective, date_paiement, date_emission_facture) " +
                    "VALUES (?, ?, ?, ?, NULL, ?, ?)";

    private static final String ADD_NEW_DELIVERY_AVAILABILITIES =
            "INSERT INTO disponibilite_livraison (id_adherent, id_jour, Date_de_choix, Date_de_retrait) VALUES " +
                    "(?, ?, ?, NULL)";

    @Override
    public String addNewMember(Member member, List<DeliveryAvailability> deliveryAvailabilities, FoodSpecificityFavorite foodSpecificityFavorite, Membership membership) {

        Long creditCardNumber = member.getCreditCard() != null ? member.getCreditCard() : 1L;

        // Vérifier si le nom du porteur de carte est null, sinon, utiliser une valeur par défaut
        String creditCardOwner = member.getCreditCardOwner() != null ? member.getCreditCardOwner() : "none";

        // Vérifier si la date de validité de la carte est null, sinon, utiliser une valeur par défaut
        LocalDate creditCardDate = member.getCreditCardDate() != null ? member.getCreditCardDate() : null;

        // Formatter pour la date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Conversion de la date en timestamp
        Timestamp timestampCreditCard = creditCardDate != null ? Timestamp.valueOf(creditCardDate.atStartOfDay()) : null;


        String dateStringBirthDay = member.getBirthdate().toString() + " 01:00:00";
        LocalDateTime localDateTimeBirthDay = LocalDateTime.parse(dateStringBirthDay, formatter);
        Timestamp timestampBirthDay = Timestamp.valueOf(localDateTimeBirthDay);



            try (Connection connection = dataSource.getConnection()) {
                System.out.println("oui 1");
                PreparedStatement statement = connection.prepareStatement(ADD_NEW_MEMBER);
                statement.setLong(1, member.getGender());
                statement.setLong(2, member.getDiet());
                statement.setLong(3, member.getTown());
                statement.setLong(4, -1);
                System.out.println(member.getPhone());
                statement.setLong(5, member.getPhone());
                statement.setTimestamp(6, timestampBirthDay);
                statement.setString(7, member.getStreet());
                statement.setString(8, member.getDeliveryInformation());
                statement.setString(9, member.getBillAdress());
                statement.setLong(10, creditCardNumber);
                statement.setTimestamp(11, timestampCreditCard);
                statement.setString(12, creditCardOwner);
                statement.setInt(13, member.getId());
                System.out.println(statement.toString());

                int lignesModifiees = statement.executeUpdate();
                // Vérifier le nombre de lignes modifiées

                    try (PreparedStatement statement2 = connection.prepareStatement(ADD_NEW_DELIVERY_AVAILABILITIES)) {
                        System.out.println("oui 2");
                        statement2.setLong(1, member.getId());
                        statement2.setLong(2, foodSpecificityFavorite.getDietarySpecificityId());
                        System.out.println(statement2);
                        int lignesModifiees2 = statement2.executeUpdate();
                        // Vérifier le nombre de lignes modifiées
                        if (lignesModifiees2 > 0) {
                            System.out.println("La valeur a été mise à jour avec succès.");
                            return "true";
                        } else {
                            System.out.println("Aucune ligne n'a été mise à jour.");
                        }
                    } catch (SQLException e) {
                    }

                for (DeliveryAvailability deliveryAvailability : deliveryAvailabilities) {
                    System.out.println(deliveryAvailability.getSelectionDate());
                    String dateStringChoiceDate = deliveryAvailability.getSelectionDate().toString() + " 01:00:00";
                    LocalDateTime localDateTimeChoiceDate = LocalDateTime.parse(dateStringChoiceDate, formatter);
                    Timestamp timestampChoiceDate = Timestamp.valueOf(localDateTimeChoiceDate);

                    try (PreparedStatement statement3 = connection.prepareStatement(ADD_NEW_DELIVERY_AVAILABILITIES)) {
                        System.out.println("oui 3");
                        statement3.setLong(1, member.getId());
                        statement3.setLong(2, deliveryAvailability.getDayId());
                        statement3.setTimestamp(3,timestampChoiceDate);

                        System.out.println(statement3);
                        int lignesModifiees3 = statement3.executeUpdate();
                        // Vérifier le nombre de lignes modifiées
                        if (lignesModifiees3 > 0) {
                            System.out.println("La valeur a été mise à jour avec succès.");
                        } else {
                            System.out.println("Aucune ligne n'a été mise à jour.");
                            return "false";
                        }
                    } catch (SQLException e) {
                        System.out.println("tasty");
                        return "false";
                    }
                }

                try (PreparedStatement statement4 = connection.prepareStatement(ADD_NEW_FOOD_SPECIFICITY_FAVORITE)) {
                    System.out.println("heyoh 4");
                    statement4.setLong(1, member.getId());
                    statement4.setLong(2, foodSpecificityFavorite.getDietarySpecificityId());

                    System.out.println(statement4);
                    int lignesModifiees4 = statement4.executeUpdate();
                    // Vérifier le nombre de lignes modifiées
                    if (lignesModifiees4 > 0) {
                        System.out.println("La valeur a été mise à jour avec succès.");
                    } else {
                        System.out.println("Aucune ligne n'a été mise à jour.");
                        return "false";
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    System.out.println("testouillasse");
                }


                try (PreparedStatement statement5 = connection.prepareStatement(ADD_NEW_MEMBERSHIP)) {
                    System.out.println("pourquoi moi");
                    String dateStringBegging = membership.getStartDate().toString() + " 01:00:00";
                    LocalDateTime localDateTimeBegging = LocalDateTime.parse(dateStringBegging, formatter);
                    Timestamp timestampBegging = Timestamp.valueOf(localDateTimeBegging);

                    String dateStringEnd = membership.getExpectedEndDate().toString() + " 01:00:00";
                    LocalDateTime localDateTimeEnd = LocalDateTime.parse(dateStringEnd, formatter);
                    Timestamp timestampEnd = Timestamp.valueOf(localDateTimeEnd);

                    System.out.println("heyoh 5");
                    statement5.setLong(1, membership.getMembershipTypeId());
                    statement5.setLong(2, member.getId());
                    statement5.setTimestamp(3,timestampBegging);
                    statement5.setTimestamp(4,timestampEnd);
                    statement5.setTimestamp(5,timestampBegging);
                    statement5.setTimestamp(6,timestampBegging);

                    System.out.println(statement5);
                    int lignesModifiees5 = statement5.executeUpdate();
                    // Vérifier le nombre de lignes modifiées
                    if (lignesModifiees5 > 0) {
                        System.out.println("La valeur a été mise à jour avec succès.");
                    } else {
                        System.out.println("Aucune ligne n'a été mise à jour.");
                        return "false";
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    System.out.println("testouillasse");
                    return "false";
                }
            } catch (SQLException e) {
                System.out.println("heyheyhey");
                return "false";
            }
            return "hey";
    }
    public long addNewUser(Member1Dto member){
        List<DishType> dishsTypes = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            // Vérifier si l'utilisateur existe déjà
            if (userExists(connection, member.getMail())) {
                // Utilisateur déjà existant, retourner un identifiant spécial ou un code d'erreur
                return -2; // par exemple, pour indiquer que l'utilisateur existe déjà
            }

            PreparedStatement statement = connection.prepareStatement(ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,member.getLastname());
            statement.setString(2,member.getFirstname());
            statement.setString(3,member.getMail());
            statement.setString(4,member.getPassword());
            System.out.println(statement);


            int affectedRowsPackage = statement.executeUpdate();
            if (affectedRowsPackage > 0) {
                try (ResultSet generatedKeysPackage = statement.getGeneratedKeys()) {
                    if (generatedKeysPackage.next()) {
                        long id = generatedKeysPackage.getLong(1);
                        System.out.println("ID de l'adhérent généré : " + id);
                        return id;
                    } else {
                        System.err.println("Aucun ID généré pour l'adhérent.");
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de l'ajout en base de données", e);
            return -1;
        }
    }

    // Méthode pour vérifier si l'utilisateur existe déjà dans la base de données
    private boolean userExists(Connection connection, String email) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM adh WHERE mail = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }


}
