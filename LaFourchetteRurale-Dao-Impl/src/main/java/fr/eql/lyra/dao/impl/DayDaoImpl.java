package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.DayDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Day;
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


@Remote(DayDao.class)
@Stateless
public class DayDaoImpl implements DayDao {
    private static final Logger logger = LogManager.getLogger();
    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_ALL_DAY = "SELECT * FROM jour_semaine";


    public List<Day> findAllDays(){
        logger.info("test");
        List<Day> days = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_DAY);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                days.add(new Day(
                        resultSet.getInt("id_jour"),
                        resultSet.getString("libelle_jour")
                ));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }
        return days;
    }
}
