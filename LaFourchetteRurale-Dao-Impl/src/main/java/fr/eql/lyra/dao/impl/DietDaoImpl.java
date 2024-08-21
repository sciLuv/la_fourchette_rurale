package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.DietDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Diet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Remote(DietDao.class)
@Stateless
public class DietDaoImpl implements DietDao {

    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_ALL_VALID_DIET =
            "SELECT * FROM regime_alimentaire WHERE fin_prise_en_charge IS NULL";

    public List<Diet> findAllActiveDiet(){
        List<Diet> diets = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_VALID_DIET);
            System.out.println(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                diets.add(new Diet(
                        resultSet.getInt("id_regime_alimentaire"),
                        resultSet.getString("nom"),
                        resultSet.getDate("debut_prise_en_charge").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
        }
        return diets;
    }
}
