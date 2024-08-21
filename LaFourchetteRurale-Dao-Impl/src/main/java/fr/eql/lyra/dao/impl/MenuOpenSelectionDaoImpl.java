package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.MenuOpenSelectionDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.MenuOpenSelection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Remote(MenuOpenSelectionDao.class)
@Stateless
public class MenuOpenSelectionDaoImpl implements MenuOpenSelectionDao {
    private static final Logger logger = LogManager.getLogger();
    private final DataSource dataSource = new LaFourchetteRuraleDataSource();

    // ** SQL queries **
    private static final String REQ_FIND_MENUS_BY_DIET = "SELECT * FROM menu WHERE Caracteristique_type = ? AND retrait IS NULL ";

    private static final String REQ_INSERT_MENU_OPEN_SELECTION = "INSERT INTO menu_ouvert_selection " +
            "(id_type_repas, id_menu, ouverture_selection, fermeture_selection, menu_defaut,position_jour) " +
            "VALUES (?,?,?,?,?,?)";

    private static final String REQ_FIND_LAST_DATE = "SELECT fermeture_selection " +
            "FROM menu_ouvert_selection " +
            "ORDER BY fermeture_selection DESC " +
            "LIMIT 1";
    // ***************


    //// Methods ////


    @Override
    public List<Menu> findMenusAvailableByDiet(String diet) {
        List<Menu> dietMenus = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_MENUS_BY_DIET);
            statement.setString(1, diet);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                dietMenus.add(new Menu(
                        resultSet.getInt("id_menu"),
                        resultSet.getString("nom"),
                        resultSet.getString("Caracteristique_type"),
                        resultSet.getDate("creation").toLocalDate()
                        ));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des menus dans la bdd", e);
        }

        return dietMenus;
    }

    @Override
    public void insertMenuOpenSelection(MenuOpenSelection menuOpenSelection) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_INSERT_MENU_OPEN_SELECTION);
            statement.setInt(1, menuOpenSelection.getMealTypeId());
            statement.setInt(2, menuOpenSelection.getMenuId());
            statement.setDate(3, Date.valueOf(menuOpenSelection.getSelectionOpening()));
            statement.setDate(4, Date.valueOf(menuOpenSelection.getSelectionClosing()));
            statement.setBoolean(5, menuOpenSelection.getDefaultMenu());
            statement.setInt(6, menuOpenSelection.getDayPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de l'insertion d'un menu ouvert à la sélection dans la bdd", e);
        }
    }

    @Override
    public LocalDate getLastEndSelectionDate() {
        LocalDate endSelectionDate = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_LAST_DATE);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                endSelectionDate = resultSet.getDate("fermeture_selection").toLocalDate();
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des menus ouverts à la sélection en bdd");
        }
        return endSelectionDate;


    }
}







