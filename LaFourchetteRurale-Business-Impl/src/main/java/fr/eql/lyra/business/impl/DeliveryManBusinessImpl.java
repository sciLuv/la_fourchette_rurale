package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.AuthenticationException;
import fr.eql.lyra.business.DeliveryManBusiness;
import fr.eql.lyra.dao.DeliveryManDao;
import fr.eql.lyra.entity.DeliveryMan;
import fr.eql.lyra.entity.dto.DeliveryManDto;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(DeliveryManBusiness.class)
@Stateless
public class DeliveryManBusinessImpl implements DeliveryManBusiness {

    @EJB
    private DeliveryManDao deliveryManDao;

    public DeliveryManBusinessImpl() {
    }

    public DeliveryManBusinessImpl(DeliveryManDao deliveryManDao) {
        this.deliveryManDao = deliveryManDao;
    }

    @Override
    public List<DeliveryMan> getAllDeliveryMan() {
        return deliveryManDao.findAllDeliveryMans();
    }

    @Override
    public DeliveryManDto authenticate(String email, String password) throws AuthenticationException {
        DeliveryMan deliveryMan = deliveryManDao.authenticate(email, password);
        if (deliveryMan == null){
            throw new AuthenticationException("L'adresse email/ou mot de passe incorrect(s).");
        }
        return new DeliveryManDto(deliveryMan.getLastName(),deliveryMan.getFirstName(),deliveryMan.getEmail());
    }

    @Override
    public void resetPassword(String email, String newPassword) throws AuthenticationException {

    }
}
