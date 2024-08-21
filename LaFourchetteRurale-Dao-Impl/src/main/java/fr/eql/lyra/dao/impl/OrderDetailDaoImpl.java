package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.OrderDao;
import fr.eql.lyra.dao.OrderDetailDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Membership;
import fr.eql.lyra.entity.OrderDetail;
import fr.eql.lyra.entity.dto.OrderDetailIdsAndDateDto;
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

@Remote(OrderDetailDao.class)
@Stateless
public class OrderDetailDaoImpl implements OrderDetailDao {

    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_ALL_MEMBER_ORDER_DETAIL =
            "SELECT dcom.* FROM adh " +
                    "INNER JOIN commande com ON adh.id_adherent = com.id_adherent " +
                    "INNER JOIN detail_com dcom ON com.id_commande = dcom.id_commande " +
                    "INNER JOIN colis c ON dcom.id_colis = c.id_colis " +
                    "INNER JOIN plat p ON dcom.id_plat = p.id_plat " +
                    "WHERE adh.id_adherent = ? ";

    private static final String UPDATE_ORDER_DETAIL_PAIMENT =
            "UPDATE detail_com SET detail_com.Date_de_reglement = ?" +
            "WHERE detail_com.Id_detail_com = ?";

    public List<OrderDetail> findAllOrderDetailForAMember(int memberId){
        List<OrderDetail> orderDetails = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_MEMBER_ORDER_DETAIL);
            statement.setInt(1, memberId);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                int orderDetailId = resultSet.getInt("Id_detail_com");
                int packageId = resultSet.getInt("id_colis");
                int orderId = resultSet.getInt("id_commande");
                Integer dishId1 = resultSet.getInt("id_plat");
                Integer dishId2 = resultSet.getInt("Pla_id_plat");
                Integer dishId3 = resultSet.getInt("Pla_id_plat2");
                Integer selectedMenuId = resultSet.getInt("id_menu_ouvert_selection");
                LocalDate choiceDate = resultSet.getDate("Date_choix") != null ? resultSet.getDate("Date_choix").toLocalDate() : null;
                LocalDate choiceValidationDate = resultSet.getDate("date_validation_choix") != null ? resultSet.getDate("date_validation_choix").toLocalDate() : null;
                LocalDate actualMealDate = resultSet.getDate("date_effective_repas") != null ? resultSet.getDate("date_effective_repas").toLocalDate() : null;
                LocalDate paymentDate = resultSet.getDate("Date_de_reglement") != null ? resultSet.getDate("Date_de_reglement").toLocalDate() : null;
                LocalDate billDate = resultSet.getDate("date_emission_facture") != null ? resultSet.getDate("date_emission_facture").toLocalDate() : null;

                orderDetails.add(
                        new OrderDetail(orderDetailId, packageId, orderId, dishId1, dishId2, dishId3, selectedMenuId, choiceDate, choiceValidationDate, actualMealDate, paymentDate, billDate)
                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }
        return orderDetails;
    }

    public String putOrderDetailPaymentDate(int[] orderDetailIds) {
        boolean test = true;

        LocalDateTime currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Convertir la date formatée en Timestamp
        Timestamp currentTimestamp = Timestamp.valueOf(formattedDateTime);

        for (int i = 0; i < orderDetailIds.length; i++) {
            try (Connection connection = dataSource.getConnection()) {
                System.out.println("oui c'est ici que je suis");
                PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_DETAIL_PAIMENT);
                statement.setTimestamp(1, currentTimestamp);
                statement.setInt(2, orderDetailIds[i]);

                int lignesModifiees = statement.executeUpdate();
                // Vérifier le nombre de lignes modifiées
                if (lignesModifiees > 0) {
                    System.out.println("La valeur a été mise à jour avec succès.");
                } else {
                    System.out.println("Aucune ligne n'a été mise à jour.");
                    test = false;
                }
            } catch (SQLException e) {
                test = false;
            }
        }

        if (test) {
            return "Toutes les mises à jour ont été effectuées avec succès.";
        } else {
            return "Erreur lors de la mise à jour des détails de commande.";
        }
    }


    public static void main(String[] args) {
        OrderDetailDaoImpl od = new OrderDetailDaoImpl();
        List<OrderDetail> truc = od.findAllOrderDetailForAMember(2);
        for (OrderDetail orderDetail : truc) {
            System.out.println(orderDetail.getOrderDetailId());
        }

    }
}
