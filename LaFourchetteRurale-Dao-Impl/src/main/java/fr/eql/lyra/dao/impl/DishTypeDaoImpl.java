package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.DishTypeDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Diet;
import fr.eql.lyra.entity.DishType;
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

@Remote(DishTypeDao.class)
@Stateless
public class DishTypeDaoImpl implements DishTypeDao {

    private static final Logger logger = LogManager.getLogger();

    private static final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final String REQ_FIND_ALL_VALID_DISHTYPE =
            "SELECT * FROM type_plat WHERE Date_de_retrait IS NULL";

    public List<DishType> sendAllActiveDishType(){
        List<DishType> dishsTypes = new ArrayList<>();

            try (Connection connection = dataSource.getConnection()){
                PreparedStatement statement = connection.prepareStatement(REQ_FIND_ALL_VALID_DISHTYPE);
                System.out.println(statement.toString());
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    dishsTypes.add(new DishType(
                            resultSet.getInt("id_type_plat"),
                            resultSet.getInt("typ_id_type_plat"),
                            resultSet.getString("type_plat"),
                            resultSet.getInt("tarif"),
                            null
                    ));
                }

                for (DishType dishType : dishsTypes) {
                    System.out.println(dishType.getDishTypeName());
                }
            } catch (SQLException e) {
                logger.error("Une erreur s'est produite lors de la consultation en base de données", e);
            }
            return dishsTypes;
        }
}
