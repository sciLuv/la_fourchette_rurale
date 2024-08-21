package fr.eql.lyra.business;

import fr.eql.lyra.entity.Day;
import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.dto.DeliveryinformationDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryRBusiness {
    List<DeliveryinformationDto> findAndUpdatePackage(int id, int idVille, LocalDate dateOfDelivery);

    String addBeginTournee(int tourneeId, LocalDateTime localDateTime);

    Member searchMember(int idMember);

    String setEndOfDelivery(int nolivraisonId,  int packageId);

    List<String>  getTownName(int[] ids);

    void addEndOfTournee(int id);
}
