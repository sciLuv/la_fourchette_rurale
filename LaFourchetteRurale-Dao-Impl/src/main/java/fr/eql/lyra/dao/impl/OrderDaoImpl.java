package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.OrderDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Dish;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.MenuOpenSelection;
import fr.eql.lyra.entity.OrderDetail;
import fr.eql.lyra.entity.Package;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Remote(OrderDao.class)
@Stateless
public class OrderDaoImpl implements OrderDao{

    private static final Logger logger = LogManager.getLogger();
    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();
    static LocalDate localDate = LocalDate.now();
    static LocalDate testDate = LocalDate.of(2024, 03, 01);
    private static final String REQ_FIND_OPEN_MENU =
            "SELECT * FROM menu_ouvert_selection WHERE ? > ouverture_selection AND ? < fermeture_selection";
    private static final String REQ_FIND_MENU_BY_ID =
            "SELECT * FROM menu WHERE id_Menu = ?";
    private static final String REQ_FIND_DISH_BY_MENU_ID =
            "SELECT plat.id_plat, plat.id_regime_alimentaire, " +
                    "plat.id_type_plat, plat.nom_plat, plat.description, " +
                    "plat.url_image FROM composition_menu cm\n" +
                    "INNER JOIN plat ON cm.id_plat = plat.id_plat\n" +
                    "WHERE cm.id_menu = ?";

    private static final String REQ_ADD_ORDER =
            "INSERT INTO commande (id_adherent, validation_com) VALUES (?,?)";

    private static final String REQ_ADD_ORDER_DETAIL =
            "INSERT INTO detail_com (id_colis, id_commande, id_plat, Pla_id_plat, Pla_id_plat2, date_effective_repas, " +
                    "Date_choix, date_validation_choix, date_emission_facture) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String REQ_ADD_PACKAGE =
            "INSERT INTO colis (livraison_prevu) VALUES (?)";

    public List<MenuOpenSelection> findMenuOpenToSelection(String date){
        String test1 = date + " 00:00:01";
        String test2 = date + " 23:59:59";
        List<MenuOpenSelection> menuOpen = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_OPEN_MENU);
            statement.setString(1, test1);
            statement.setString(2, test2);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                menuOpen.add(new MenuOpenSelection(
                        resultSet.getInt("id_menu_ouvert_selection"),
                        resultSet.getInt("id_type_repas"),
                        resultSet.getInt("id_menu"),
                        resultSet.getDate("ouverture_selection").toLocalDate(),
                        resultSet.getDate("fermeture_selection").toLocalDate(),
                        resultSet.getBoolean("menu_defaut"),
                        resultSet.getInt("position_jour")
                ));
            }
            System.out.println("hello");
            for (MenuOpenSelection menuOpenSelection : menuOpen) {
                System.out.println(menuOpenSelection.getSelectionOpening());
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }
        return menuOpen;
    }

    public List<Menu> findMenuFromMenuOpenToSelection(int[] menusId){
        List<Menu> menus = new ArrayList<>();
        System.out.println("----");
        for (int i = 0; i < menusId.length; i++) {
            System.out.println(menusId[i]);
        }
        for (int i = 0; i < menusId.length; i++) {
            try (Connection connection = dataSource.getConnection()){


                PreparedStatement statement = connection.prepareStatement(REQ_FIND_MENU_BY_ID);
                statement.setInt(1, menusId[i]);
                System.out.println(statement.toString());
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    LocalDate endDate = resultSet.getDate("retrait") != null ? resultSet.getDate("retrait").toLocalDate() : null;
                    menus.add(new Menu(
                            resultSet.getInt("id_menu"),
                            resultSet.getString("nom"),
                            resultSet.getString("Caracteristique_type"),
                            resultSet.getDate("creation").toLocalDate(),
                            endDate

                    ));
                }
                for (Menu menu : menus) {
                    System.out.println(menu.getMenuId());
                    System.out.println(menu.getName());
                }
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
            }
        }
        System.out.println("----");

        return menus;
    }

    public List<Dish> findDishFromMenu(int[] menusId){
        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < menusId.length; i++) {
            System.out.println(menusId[i]);
            try (Connection connection = dataSource.getConnection()){
                PreparedStatement statement = connection.prepareStatement(REQ_FIND_DISH_BY_MENU_ID);
                statement.setInt(1, menusId[i]);
                System.out.println(statement.toString());
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    dishes.add(new Dish(
                            resultSet.getInt("id_plat"),
                            resultSet.getLong("id_regime_alimentaire"),
                            resultSet.getInt("id_type_plat"),
                            resultSet.getString("nom_plat"),
                            resultSet.getString("description"),
                            resultSet.getString("url_image"),
                            null, null
                    ));
                }
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
            }
        }
        return dishes;
    }

    public String addOrderAndOrderDetail(long memberId, List<OrderDetail> orderDetails, List<Package> packages,List<List<Integer>> association ){

        String dateString = orderDetails.get(0).getChoiceDate().toString() + " 01:00:00";
        // Créer un formateur de date et heure pour le format spécifié
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Analyser la chaîne en LocalDateTime en utilisant le formateur
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_ADD_ORDER, Statement.RETURN_GENERATED_KEYS); // Ajout de Statement.RETURN_GENERATED_KEYS
            statement.setLong(1, memberId);
            // Convertir LocalDateTime en Timestamp
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            statement.setTimestamp(2, timestamp);
            System.out.println(statement);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long orderId = generatedKeys.getLong(1);
                        System.out.println("ID de la commande généré : " + orderId);

                        List<Long> packagesId = new ArrayList<>();
                        for (Package aPackage : packages) {
                            String dateStringPackage = aPackage.getExpectedDelivery().toString() + " 01:00:00";
                            LocalDateTime localDateTimePackage = LocalDateTime.parse(dateStringPackage, formatter);
                            Timestamp timestampPackage = Timestamp.valueOf(localDateTimePackage);
                            try (PreparedStatement statementPackage = connection.prepareStatement(REQ_ADD_PACKAGE, Statement.RETURN_GENERATED_KEYS)) {
                                statementPackage.setTimestamp(1, timestampPackage);
                                int affectedRowsPackage = statementPackage.executeUpdate();
                                if (affectedRowsPackage > 0) {
                                    try (ResultSet generatedKeysPackage = statementPackage.getGeneratedKeys()) {
                                        if (generatedKeysPackage.next()) {
                                            long packageId = generatedKeysPackage.getLong(1);
                                            System.out.println("ID du colis généré : " + packageId);
                                            packagesId.add(packageId);
                                        } else {
                                            System.err.println("Aucun ID généré pour le colis.");
                                        }
                                    }
                                }
                            }
                        }
                        System.out.println("liste des id des colis");
                        for (Long l : packagesId) {
                            System.out.println(l);
                        }


                            for (int i = 0; i < association.size(); i++) {
                                System.out.println("integer de la table association : " + i);
                                for (int j = 0; j < association.get(i).size(); j++) {
                                    System.out.println("integer de la sous table d'association : " + j);
                                    String dateStringDetailOrderMealDate = orderDetails.get(j).getActualMealDate().toString() + " 01:00:00";
                                    System.out.println(dateStringDetailOrderMealDate);
                                    LocalDateTime localDateTimeDetailOrderMealDate = LocalDateTime.parse(dateStringDetailOrderMealDate, formatter);
                                    Timestamp timestampDetailOrderMealDate = Timestamp.valueOf(localDateTimeDetailOrderMealDate);
                                    System.out.println("lol");
                                    String dateStringDetailOrderChoiceDate = orderDetails.get(j).getChoiceDate().toString() + " 01:00:00";
                                    System.out.println(dateStringDetailOrderChoiceDate);
                                    LocalDateTime localDetailOrderChoiceDate = LocalDateTime.parse(dateStringDetailOrderChoiceDate, formatter);
                                    Timestamp timestampDetailOrderChoiceDate = Timestamp.valueOf(localDetailOrderChoiceDate);
                                    System.out.println("lol1");
                                    String dateStringDetailOrderChoiceValidationDate = orderDetails.get(j).getChoiceValidationDate().toString() + " 01:00:00";
                                    LocalDateTime localDateDetailOrderChoiceValidationDate = LocalDateTime.parse(dateStringDetailOrderChoiceValidationDate, formatter);
                                    Timestamp timestampDetailOrderChoiceValidationDate = Timestamp.valueOf(localDateDetailOrderChoiceValidationDate);
                                    System.out.println("lol3");
                                    String dateStringDetailOrderBillDate = orderDetails.get(j).getBillDate().toString() + " 01:00:00";
                                    LocalDateTime localDateDetailOrderBillDate = LocalDateTime.parse(dateStringDetailOrderBillDate, formatter);
                                    Timestamp timestampDetailOrderBillDate = Timestamp.valueOf(localDateDetailOrderBillDate);
                                    System.out.println("lol3");
                                    long packageId = packagesId.get(i);
                                    long orderId2 = orderId;

                                    try (PreparedStatement statementOrderDetail = connection.prepareStatement(REQ_ADD_ORDER_DETAIL, Statement.RETURN_GENERATED_KEYS)) {
                                        statementOrderDetail.setLong(1, packageId);
                                        statementOrderDetail.setLong(2, orderId2);
                                        statementOrderDetail.setLong(3, orderDetails.get(j).getDishId1());
                                        statementOrderDetail.setLong(4, orderDetails.get(j).getDishId2());
                                        statementOrderDetail.setLong(5, orderDetails.get(j).getDishId3());
                                        statementOrderDetail.setTimestamp(6, timestampDetailOrderMealDate);
                                        statementOrderDetail.setTimestamp(7, timestampDetailOrderChoiceDate);
                                        statementOrderDetail.setTimestamp(8, timestampDetailOrderChoiceValidationDate);
                                        statementOrderDetail.setTimestamp(9, timestampDetailOrderBillDate);
                                        int affectedRowsOrderDetail = statementOrderDetail.executeUpdate();
                                        if (affectedRowsOrderDetail > 0) {
                                            try (ResultSet generatedKeysOrderDetail = statementOrderDetail.getGeneratedKeys()) {
                                                if (generatedKeysOrderDetail.next()) {
                                                    long orderDetailId = generatedKeysOrderDetail.getLong(1);
                                                    System.out.println("ID du détail de commande généré : " + orderDetailId);
                                                    packagesId.add(packageId);
                                                } else {
                                                    System.err.println("Aucun ID généré pour le detail de commande.");
                                                }
                                            }
                                        }
                                    }
                                }
                            }


                        
                        System.out.println("fin de la liste des colis");
                    } else {
                        System.err.println("Aucun ID généré pour la commande.");
                    }
                }
            }
            return "commande prise en compte";
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la mise à jour de la session utilisateur en base de données", e);
            return "commande non prise en compte";
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }



    public static void main(String[] args) {
//        System.out.println(localDate);
//        System.out.println(testDate);
//        OrderDaoImpl orderDao = new OrderDaoImpl();
//        List<MenuOpenSelection> truc = orderDao.findMenuOpenToSelection("test");
//        int[] tableau = {1, 2, 3, 4};
//        List<Menu> truc2 = orderDao.findMenuFromMenuOpenToSelection(tableau);
//        System.out.println(OrderDaoImpl.getDataSource());
//
//        System.out.println("----");
//        for (Menu menu : truc2) {
//            System.out.println(menu.getName());
//        }
//
//        List<Dish> truc3 = orderDao.findDishFromMenu(tableau);
//        for (Dish dish : truc3) {
//            System.out.println(dish.getDishName());
//        }
//        OrderDaoImpl orderDao = new OrderDaoImpl();
//        List<Diet> lol = orderDao.FindAllActiveDiet();
//
//        for (Diet diet : lol) {
//            System.out.println(diet.getName());
//        }
    }
}
