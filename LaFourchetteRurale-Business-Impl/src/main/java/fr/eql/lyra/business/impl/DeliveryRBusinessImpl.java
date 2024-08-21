package fr.eql.lyra.business.impl;


import fr.eql.lyra.business.DayBusiness;
import fr.eql.lyra.business.DeliveryRBusiness;
import fr.eql.lyra.dao.DeliveryRDao;
import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.dto.DeliveryinformationDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Remote(DeliveryRBusiness.class)
@Stateless
public class DeliveryRBusinessImpl implements DeliveryRBusiness {

    @EJB
    DeliveryRDao deliveryRDao;

    @Override
    public List<DeliveryinformationDto> findAndUpdatePackage(int id, int idVille, LocalDate dateOfDelivery) {
        try {
            return deliveryRDao.findAndUploadPackage(id, idVille, dateOfDelivery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String addBeginTournee(int tourneeId, LocalDateTime localDateTime) {
        return deliveryRDao.addANewBeginDate(tourneeId, localDateTime);
    }

    @Override
    public Member searchMember(int idMember){
        return deliveryRDao.findDeliveryInfoMember(idMember);
    }

    @Override
    public String setEndOfDelivery(int nolivraisonId,  int packageId){
        return deliveryRDao.endOfDelivery(nolivraisonId, packageId);
    }

    @Override
    public List<String>  getTownName(int[] ids){
        return deliveryRDao.findTownById(ids);
    }

    @Override
    public void addEndOfTournee(int id){
        deliveryRDao.endOfTournee(id);
    }

}
