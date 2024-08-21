package fr.eql.lyra.business;

import fr.eql.lyra.entity.DeliveryMan;
import fr.eql.lyra.entity.dto.DeliveryManDto;
import java.util.List;

public interface DeliveryManBusiness {

    List<DeliveryMan> getAllDeliveryMan();

    DeliveryManDto authenticate(String email, String password) throws AuthenticationException;

    void resetPassword(String email, String newPassword) throws AuthenticationException;
}

