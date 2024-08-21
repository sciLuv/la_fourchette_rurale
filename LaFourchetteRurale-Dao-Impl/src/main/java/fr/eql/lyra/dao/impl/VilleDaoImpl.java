package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.VilleDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Ville;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Remote(VilleDao.class)
@Stateless
public class VilleDaoImpl implements VilleDao {

    private final DataSource dataSource = new LaFourchetteRuraleDataSource();

    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_INSERT_VILLE = "INSERT INTO ville (nom_ville, CP_ville, date_integ) VALUES (?, ?, ?)";
    private static final String REQ_FIND_BY_NAME = "SELECT * FROM ville WHERE Nom_ville = ? AND date_sortie IS NULL ";
    private static final String REQ_FIND_BY_CP = "SELECT * FROM ville WHERE CP_ville = ? AND date_sortie IS NULL";
    private static final String REQ_FIND_ALL = "SELECT * FROM ville";
    private static final String REQ_DISABLE_CITY = "UPDATE ville SET date_sortie = ? WHERE ID_ville = ?";
    private static final String REQ_SORT_BY_CITY_CP_AND_NAME =
            "SELECT * FROM ville " +
                    "WHERE date_sortie IS NULL " +
                    "ORDER BY CP_ville, Nom_ville";

    private static final String REQ_FIND_PACKAGE_CITIES =
            "SELECT v.Nom_ville" +
            "FROM ville v" +
            "JOIN adh a ON v.ID_ville = a.ID_ville" +
            "JOIN commande com ON a.id_adherent = com.id_adherent" +
            "JOIN detail_com det ON com.id_commande = det.id_commande";

    @Override
    public void insertNewVille(Ville ville) {
        try (Connection connection = dataSource.getConnection()) {
            // vérifier si la ville existe dans le système
            if (checkCityNameExists(connection, ville.getCityName())) {
                logger.warn("La ville " + ville.getCityName() + "existe déjà dans le système");
                return;
            }
            //Ajouter la ville
            PreparedStatement statement = connection.prepareStatement(REQ_INSERT_VILLE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ville.getCityName());
            statement.setInt(2, ville.getCityCP());
            statement.setDate(3, Date.valueOf(ville.getIntegDate()));
            statement.executeUpdate();

            // Obtenir l'ID des données nouvellement insérées
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    ville.setId(id);
                }
            }

        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de l'insertion de " + ville.getCityName() + " ou " + ville.getCityCP(), e);
        }

    }

    @Override
    public Ville findByCityName(String cityName) {
        Ville ville = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_NAME);
            statement.setString(1, cityName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ID_ville");
                int cityCP = resultSet.getInt("CP_ville");
                ville = new Ville(id, cityName, cityCP, null, null);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation de la ville en base de données", e);

        }
//        System.out.println(ville.getCityName());
        return ville;

    }

    @Override
    public List<Ville> findByCityCP(int cityCP) {
        List<Ville> villes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_CP);
            statement.setInt(1, cityCP);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_ville");
                String cityName = resultSet.getString("Nom_ville");
                Ville ville = new Ville(id, cityName, cityCP, null, null);
                villes.add(ville);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation de la ville en base de données", e);
        }
        System.out.println(villes);
        return villes;
    }


    @Override
    public List<Ville> findAllCities() {
        List<Ville> villes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_FIND_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_ville");
                String cityName = resultSet.getString("Nom_ville");
                int cityCP = resultSet.getInt("CP_ville");
                villes.add(new Ville(id, cityName, cityCP, null, null));

            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des villes en base de données", e);
        }
        return villes;
    }

    @Override
    public void disableCity(int cityId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_DISABLE_CITY);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, cityId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la désactivation de la ville avec l'ID " + cityId, e);
        }

    }


    //Vérifier si la ville existe déjà
    private boolean checkCityNameExists(Connection connection, String cityName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM ville WHERE Nom_ville = ?");
        statement.setString(1, cityName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0;
        }
        return false;
    }

    public List<Ville> sortByCityCPAndName() {
        List<Ville> villes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(REQ_SORT_BY_CITY_CP_AND_NAME);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID_ville");
                String cityName = resultSet.getString("Nom_ville");
                int cityCP = resultSet.getInt("CP_ville");
                LocalDate integDate = resultSet.getDate("date_integ").toLocalDate();
                LocalDate sortieDate = resultSet.getDate("date_sortie") != null ? resultSet.getDate("date_sortie").toLocalDate() : null;
                villes.add(new Ville(id, cityName, cityCP, integDate, sortieDate));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors du tri des villes par code postal et nom", e);
        }
        return villes;
    }


    @Override
    public List<Ville> findPackageCities() {
        List<Ville> packageCities = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(REQ_FIND_PACKAGE_CITIES);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ville ville = new Ville();
                ville.setCityName(resultSet.getString("Nom_ville"));

                packageCities.add(ville);
            }

        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la recherche des villes associées aux colis.", e);
        }

        return packageCities;
    }


//    public static void main(String[] args) {
//        try {
//            // 创建 VilleDaoImpl 实例
//            VilleDaoImpl villeDaoImpl = new VilleDaoImpl();

//            // 查找一个存在的城市并打印信息
//            Ville existingCity = villeDaoImpl.findByCityName("Bof");
//            if (existingCity != null) {
//                System.out.println("Ville existe: " + existingCity);
//            } else {
//                System.out.println("Ville non trouvée.");
//            }

//            // 禁用这个城市并打印信息
//            if (existingCity != null) {
//                villeDaoImpl.disableCity(existingCity.getId());
//                System.out.println("La ville " + existingCity.getCityName() + " a été retirée.");
//            } else {
//                System.out.println("Impossible de désactiver la ville car elle n'a pas été trouvée.");
//            }
//
//            // 试图插入一个已经存在的城市
//            Ville villeExistante = new Ville("Melun", 77700, LocalDate.now(), null);
//            villeDaoImpl.insertNewVille(villeExistante);
//            System.out.println("Insertion de la ville existante terminée.");

//
//            // 试图插入一个新的城市
//            Ville nouvelleVille = new Ville("Jossiny", 77600, LocalDate.now(), null);
//            villeDaoImpl.insertNewVille(nouvelleVille);
//            System.out.println("Insertion de la nouvelle ville terminée.");
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        VilleDaoImpl villeDaoImpl = new VilleDaoImpl();
//        List<Ville> sortedCities = villeDaoImpl.sortByCityCPAndName();
//        System.out.println("Sorted Cities:");
//        for (Ville city : sortedCities) {
//            System.out.println(city.getCityName() + ", " + city.getCityCP());
//        }
//    }

}







