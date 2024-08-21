package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.MemberShipDao;
import fr.eql.lyra.dao.MembershipTypeDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Membership;
import fr.eql.lyra.entity.MembershipType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Remote(MembershipTypeDao.class)
@Stateless
public class MembershipTypeDaoImpl implements MembershipTypeDao {

    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_ALL_MEMBERSHIP_TYPE =
            "SELECT * FROM type_adhesion WHERE date_de_fin_validite IS NULL";

    public List<MembershipType> findAllMembershipType(){
        List<MembershipType> membershipTypes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_MEMBERSHIP_TYPE);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                long idTypeAdhesion = resultSet.getLong("id_type_adhesion");
                long idBizarre = resultSet.getLong("typ_id_type_adhesion");
                String name = resultSet.getString("nom");
                Long time = resultSet.getLong("duree");
                long tarif = resultSet.getLong("tarif");
                LocalDate endDate = resultSet.getDate("date_de_fin_validite") != null ? resultSet.getDate("date_paiement").toLocalDate() : null;
                membershipTypes.add(
                        new MembershipType(
                                idTypeAdhesion,
                                idBizarre,
                                name,
                                time,
                                tarif,
                                endDate
                                )
                );
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }
        return membershipTypes;
    }

    public static void main(String[] args) {
        MembershipTypeDaoImpl membershipTypeDao = new MembershipTypeDaoImpl();
        List<MembershipType> truc = membershipTypeDao.findAllMembershipType();

        for (MembershipType membershipType : truc) {
            System.out.println(membershipType.getName());
        }
    }
}
