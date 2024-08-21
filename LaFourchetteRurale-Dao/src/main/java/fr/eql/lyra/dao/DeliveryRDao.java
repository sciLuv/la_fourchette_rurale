package fr.eql.lyra.dao;

import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.dto.DeliveryinformationDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



public interface DeliveryRDao {
    List<DeliveryinformationDto> findAndUploadPackage(int id, int idVille, LocalDate dateOfDelivery) throws SQLException;
    String addANewBeginDate(int tourneeId, LocalDateTime localDateTime);

    Member findDeliveryInfoMember(long id);
    String endOfDelivery(int nolivraisonId, int packageId);

    List<String> findTownById(int[] ids);

    void endOfTournee(int id);
}
