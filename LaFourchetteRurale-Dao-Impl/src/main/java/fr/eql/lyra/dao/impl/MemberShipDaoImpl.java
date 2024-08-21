package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.MemberShipDao;
import fr.eql.lyra.dao.OrderDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Diet;
import fr.eql.lyra.entity.Membership;
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

@Remote(MemberShipDao.class)
@Stateless
public class MemberShipDaoImpl implements MemberShipDao{
    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_ALL_MEMBER_MEMBERSHIP =
            "SELECT * FROM adhesion WHERE id_adherent = ?";

    public List<Membership> findAllMembershipForAMember(int memberId){
        List<Membership> memberships = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_MEMBER_MEMBERSHIP);
            statement.setInt(1, memberId);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer idTypeAdhesion = resultSet.getInt("id_type_adhesion");
                Integer idAdherent = resultSet.getInt("id_adherent");
                LocalDate debut = resultSet.getDate("debut") != null ? resultSet.getDate("debut").toLocalDate() : null;
                LocalDate finPrevu = resultSet.getDate("fin_prevu") != null ? resultSet.getDate("fin_prevu").toLocalDate() : null;
                LocalDate finEffective = resultSet.getDate("fin_effective") != null ? resultSet.getDate("fin_effective").toLocalDate() : null;
                LocalDate datePaiement = resultSet.getDate("date_paiement") != null ? resultSet.getDate("date_paiement").toLocalDate() : null;
                LocalDate dateEmissionFacture = resultSet.getDate("date_emission_facture") != null ? resultSet.getDate("date_emission_facture").toLocalDate() : null;

                memberships.add(new Membership(resultSet.getInt("id_adhesion"), idTypeAdhesion, idAdherent, debut, finPrevu, finEffective, datePaiement, dateEmissionFacture));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }
        return memberships;
    }

    public static void main(String[] args) {
        MemberShipDaoImpl memberShipDao = new MemberShipDaoImpl();
        List<Membership> oui = memberShipDao.findAllMembershipForAMember(1);
        System.out.println("test");
        for (Membership membership : oui) {
            System.out.println("testé2");
            System.out.println(membership.getMembershipId());
        }
    }
}
