package fr.eql.lyra.business;

import fr.eql.lyra.entity.dto.DeliveryManADto;
import fr.eql.lyra.entity.dto.MemberADto;

public interface SecurityABusiness {

	MemberADto authenticate(String username, String password) throws AuthenticationException;
//	void authorize(String authorization, Role role) throws AuthorizationException;

	DeliveryManADto authenticateDeliveryMan(String username, String password) throws AuthenticationException;

	boolean authorizeAdmin(String username, String password) throws AuthenticationException;
}


