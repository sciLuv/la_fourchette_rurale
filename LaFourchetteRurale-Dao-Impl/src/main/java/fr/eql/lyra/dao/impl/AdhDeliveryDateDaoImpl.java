package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.AdhDeliveryDateDao;
import fr.eql.lyra.dao.DayDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
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

@Remote(AdhDeliveryDateDao.class)
@Stateless
public class AdhDeliveryDateDaoImpl implements AdhDeliveryDateDao {

    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String FIND_USER_DATE =
            "SELECT * FROM disponibilite_livraison\n" +
                    "WHERE disponibilite_livraison.id_adherent = ?";


//    @Override
    public List<Integer> findDaysOfDelivery(int id) {
        List<Integer> daysOfDelivery = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(FIND_USER_DATE);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                daysOfDelivery.add(resultSet.getInt("id_jour"));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite " +
                    "lors de la consultation des propriétaire en base de données", e);
        }
        return daysOfDelivery;
    }
}
