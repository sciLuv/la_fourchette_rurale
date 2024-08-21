package fr.eql.lyra.dao.impl;

import fr.eql.lyra.dao.TourneeDao;
import fr.eql.lyra.dao.impl.connection.LaFourchetteRuraleDataSource;
import fr.eql.lyra.entity.Tournee;
import fr.eql.lyra.entity.Ville;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Remote(TourneeDao.class)
@Stateless
public class TourneeDaoImpl implements TourneeDao {

    private final DataSource dataSource = new LaFourchetteRuraleDataSource();
    private static final Logger logger = LogManager.getLogger();

    private static final String REQ_ADD_TOURNEE = "INSERT INTO tournee (id_livreur, date, debut_tournee, fin_tournee) VALUES (?, ?, null, null)";
    private static final String REQ_FIND_BY_ID = "SELECT * FROM tournee WHERE id_tournee = ?";
    private static final String REQ_FIND_BY_DATE="SELECT * FROM tournee WHERE date = ?";
    private static final String REQ_UPDATE_TOURNEE =
            "UPDATE tournee " +
            "SET id_livreur = ?, "+
            "    date = ? " +
            "WHERE id_tournee = ?";

    private static final String REQ_DELETE_TOURNEE = "DELETE FROM tournee WHERE id_tournee = ?";
    private static final String REQ_FIND_BY_VILLE=
            "SELECT tr.id_tournee " +
                    "FROM ville v " +
                    "JOIN adh a ON v.ID_ville = a.ID_ville " +
                    "JOIN commande com ON a.id_adherent = com.id_adherent " +
                    "JOIN detail_com det ON com.id_commande = det.id_commande "+
                    "JOIN colis cl ON cl.id_colis = det.id_colis "+
                    "JOIN tournee tr ON tr.id_tournee = cl.id_tournee "+
                    "WHERE v.Nom_ville = ? ";

private static final String REQ_FIND_BY_DISTRIBUER=
        "SELECT id_ville " +
                "FROM distribuer dt " +
                "JOIN tournee tr ON tr.id_tournee = dt.id_tournee " +
                "WHERE dt.id_tournee = ? ";

private static final String REQ_ADD_TOWROUND = "INSERT INTO distribuer (ID_ville, id_tournee)VALUES(?,?)";

private static final String REQ_FIND_TOURNEE_BY_DELIVERYMAN_ID = "SELECT * FROM tournee WHERE id_livreur = ? AND fin_tournee IS NULL ";
private static final String REQ_FIND_VILLEID_BY_TOURNEEID = "SELECT ID_ville FROM distribuer WHERE id_tournee = ?";

    @Override
    public Tournee findById(int id) {
        Tournee tournee = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int tourneeId = resultSet.getInt("id_tournee");
                int deliveryManId = resultSet.getInt("id_livreur");
                LocalDate livraisonDate = resultSet.getDate("date").toLocalDate();
                LocalDate debutDate = resultSet.getDate("debut_tournee").toLocalDate();
                LocalDate finDate = resultSet.getDate("fin_tournee").toLocalDate();
                tournee = new Tournee(tourneeId, deliveryManId, livraisonDate, null, null);
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation de la ville en base de données", e);

        }
        return tournee;

    }


    @Override
    public List<Tournee> findByDate(LocalDate date) {
        List<Tournee> tournees = new ArrayList<>();
        try ( //连接数据库，创建连接对象connection
              Connection connection = dataSource.getConnection()) {
            //创建预编译环境
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_DATE);
            //设置SQL语句中的参数
            statement.setDate(1, Date.valueOf(date));
            //执行语句
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //用结果集查询
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

    @Override
    public List<Tournee> findByVille(Ville ville) {
        List<Tournee> tournees = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_BY_VILLE);
            statement.setString(1, ville.getCityName());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int tourneeId = resultSet.getInt("id_tournee");
                // 创建 Tournee 对象并添加到列表中
                tournees.add(new Tournee(tourneeId, 0, null, null, null));
            }
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des tournées par ville en base de données", e);
        }
        return tournees;
    }

    @Override
    public void addTournee(Tournee tournee) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_ADD_TOURNEE);
            statement.setInt(1, tournee.getDeliveryManId());
            statement.setObject(2, tournee.getDate());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de l'ajout de la tournée en base de données", e);
        }
    }

    @Override
    public void update(Tournee tournee, List<Ville> villes) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_UPDATE_TOURNEE);
            statement.setInt(1, tournee.getDeliveryManId());
            statement.setDate(2, Date.valueOf(tournee.getDate()));
            statement.setInt(3, tournee.getTourneeId());

            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la mise à jour de la tournée en base de données", e);
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_DELETE_TOURNEE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la suppression de la tournée en base de données", e);
        }

    }

    @Override
    public String addingNew(List<Tournee> tourneesList, List<int[]> distribuerList) {
        int counter = 0;
        try (Connection connection = dataSource.getConnection()) {
            for (int i = 0; i < tourneesList.size(); i++) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dateStringTournees = tourneesList.get(i).getDate().toString() + " 01:00:00";
                LocalDateTime localDateTimeTournees = LocalDateTime.parse(dateStringTournees, formatter);
                Timestamp timestampTournees = Timestamp.valueOf(localDateTimeTournees);

                try (PreparedStatement statementTournees = connection.prepareStatement(REQ_ADD_TOURNEE, Statement.RETURN_GENERATED_KEYS)) {
                    statementTournees.setLong(1, tourneesList.get(i).getDeliveryManId());
                    statementTournees.setTimestamp(2, timestampTournees);

                    int affectedRowsTournees = statementTournees.executeUpdate();
                    if (affectedRowsTournees > 0) {
                        try (ResultSet generatedKeysRound = statementTournees.getGeneratedKeys()) {
                            if (generatedKeysRound.next()) {
                                long tourneeId = generatedKeysRound.getLong(1); // Récupération de l'ID généré
                                for (int i1 : distribuerList.get(i)) {
                                        try (PreparedStatement statement2 = connection.prepareStatement(REQ_ADD_TOWROUND)) {
                                            statement2.setLong(1, i1);
                                            statement2.setLong(2, tourneeId); // Utilisation de l'ID de la tournée
                                            int lignesModifiees2 = statement2.executeUpdate();
                                            if (lignesModifiees2 > 0) {
                                                System.out.println("La valeur a été mise à jour avec succès.");
                                            } else {
                                                System.out.println("Aucune ligne n'a été mise à jour.");
                                            }
                                        } catch (SQLException e) {
                                            System.out.println("Erreur lors de l'insertion dans la table TOWROUND.");
                                        }
                                }
//                                for (int k = 0; k<distribuerList.size(); k++){
//                                    for (int i1 : distribuerList.get(k)) {
//
//                                    }
//
                            } else {
                                System.err.println("Aucun ID généré pour le détail de la commande.");
                            }
                        }
                        counter++;
                    }
                } catch (SQLException e) {
                    System.out.println("Erreur lors de l'insertion dans la table TOURNEE.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'établissement de la connexion à la base de données.");
        }

        return null;
    }


    @Override
    public List<Tournee> findByIdDeliveryMan(int deliveryManId) {
        System.out.println("test daoImpl 1");
        List<Tournee> tournees = new ArrayList<>();
        System.out.println("test daoImpl 2");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_TOURNEE_BY_DELIVERYMAN_ID);
            statement.setInt(1, deliveryManId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tournees.add(new Tournee(
                        resultSet.getInt("id_tournee"),
                        resultSet.getInt("id_livreur"),
                        resultSet.getDate("date").toLocalDate(),
                        null,
                        null
                        )
                );
            }
            System.out.println("test 1");
            for (Tournee tournee : tournees) {
                System.out.println(tournee.getDate());
            }
            System.out.println("test 2");
            return tournees;
        } catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des tournées par id_livreur en base de données", e);
            return null;
        }
    }

    @Override
    public List<Integer> findTownByTournee(int tourneeId){
        List<Integer> idTowns = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(REQ_FIND_VILLEID_BY_TOURNEEID);
            statement.setInt(1, tourneeId);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);

            while (resultSet.next()) {
                idTowns.add(resultSet.getInt("ID_ville"));
            }
        }catch (SQLException e) {
            logger.error("Une erreur s'est produite lors de la consultation des tournées par id_livreur en base de données", e);
            return null;
        }
        return idTowns;
    }

    public static void main(String[] args) {
        TourneeDaoImpl t = new TourneeDaoImpl();

        List<Integer> truc = t.findTownByTournee(44);
        for (Integer i : truc) {
            System.out.println(i);
        }
    }
}






