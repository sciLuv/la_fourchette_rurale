package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.TourneeRDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Tournee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Remote(TourneeRDao.class)
@Stateless
public class TourneeRDaoImpl implements TourneeRDao {

    private final DataSource dataSource = new LaFourchetteRuraleDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_FIND_BY_DATE="SELECT * FROM tournee WHERE date > ?";

    @Override
    public List<Tournee> findByDate(LocalDate date) {
        List<Tournee> tournees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_DATE);
            statement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int tourneeId = resultSet.getInt("id_tournee");
                int deliveryManId = resultSet.getInt("id_livreur");
                LocalDate livraisonDate = resultSet.getDate("date").toLocalDate();
                // 创建 Tournee 对象并添加到列表中
                tournees.add(new Tournee(tourneeId, deliveryManId, livraisonDate, null, null));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des tournées par date en base de données", e);
        }
        return tournees;
    }
}
