package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.DietDao;
import fr.eql.lyra.dao.FoodSpecificityDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.FoodSpecificity;
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

@Remote(FoodSpecificityDao.class)
@Stateless
public class FoodSpecificityDaoImpl implements FoodSpecificityDao {

    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_ALL_VALID_FOOD_SPECIFICITIES =
            "SELECT * FROM specificite_alimentaire WHERE fin_prise_en_charge IS NULL";

    @Override
    public List<FoodSpecificity> getAllFoodSpecificity() {
            List<FoodSpecificity> foodSpecificities = new ArrayList<>();
            try (Connection connection = dataSource.getConnection()){
                PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_VALID_FOOD_SPECIFICITIES);
                System.out.println(statement.toString());
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    foodSpecificities.add(new FoodSpecificity(
                            resultSet.getInt("id_specificite_alimentaire"),
                            resultSet.getString("nom"),
                            resultSet.getDate("debut_prise_en_charge").toLocalDate()
                    ));
                }
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
            }
        return foodSpecificities;
    }

    public static void main(String[] args) {
        FoodSpecificityDaoImpl foodSpecificity = new FoodSpecificityDaoImpl();
        List<FoodSpecificity> test = foodSpecificity.getAllFoodSpecificity();
        for (FoodSpecificity specificity : test) {
            System.out.println(specificity.getName());
        }
    }
}
