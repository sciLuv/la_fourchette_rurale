package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.AuthenticationException;
import fr.eql.lyra.business.SecurityABusiness;
import fr.eql.lyra.dao.DeliveryManADao;
import fr.eql.lyra.dao.MemberADao;
import fr.eql.lyra.entity.DeliveryAMan;
import fr.eql.lyra.entity.MemberA;
import fr.eql.lyra.entity.dto.DeliveryManADto;
import fr.eql.lyra.entity.dto.MemberADto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(SecurityABusiness.class)
@Stateless
public class SecurityABusinessImpl implements SecurityABusiness {

    private static final int SESSION_TIME = 30 * 60 * 1000;

    @EJB
    MemberADao memberDao;

    @Override
    public MemberADto authenticate(String username, String password) throws AuthenticationException {
        MemberA memberA = memberDao.authenticate(username, password);
        if (memberA == null) {
            throw new AuthenticationException("Identifiant et/ou mot de passe incorrect(s).");
        }
//        String token = issueToken(username);
//        memberDao.updateSession(token, memberA.getId());
//        return new MemberDto(memberA.getId(), memberA.getFirstname(), memberA.getLastname(), token);
        return new MemberADto(memberA.getId(), memberA.getFirstname(), memberA.getLastname(),
                memberA.getMail(), memberA.getPassword(), memberA.getPhone(), memberA.getIdgender(), memberA.getBirthdate(), memberA.getStreet(), memberA.getDeliveryInformation(),
                memberA.getBillAdress(), memberA.getCreditCard(), memberA.getCreditCardDate(), memberA.getCreditCardMember(), memberA.getIdDiet(),
                memberA.getIdTown(), memberA.getIdTownBill(), memberA.getRole());
    }

    @EJB
    DeliveryManADao deliveryManADao;

    @Override
    public DeliveryManADto authenticateDeliveryMan(String username, String password) throws AuthenticationException {
        DeliveryAMan deliveryAMan = deliveryManADao.authenticateDeliveryMan(username, password);
        if (deliveryAMan == null) {
            throw new AuthenticationException("Identifiant et/ou mot de passe incorrect(s).");
        }


        return new DeliveryManADto(deliveryAMan.getDeliveryManId(), deliveryAMan.getLastName(), deliveryAMan.getFirstName(),
                deliveryAMan.getPassword(), deliveryAMan.getMail(), deliveryAMan.getRole());
    }


    @Override
    public boolean authorizeAdmin(String username, String password) throws AuthenticationException {
        boolean isAdmin;
        if ((username.equals("admin")) && (password.equals("Admin123+"))) {
            isAdmin = true;
            System.out.println("test");
        } else {
            isAdmin = false;
            System.out.println("test2");
        }
        return isAdmin;
    }
}

//    @Override
//    public void authorize(String authorization, Role role) throws AuthorizationException {
//        String token = authorization.substring(7);
//        Session session = memberDao.findSession(token);
//        if (session == null) {
//            throw new AuthorizationException("Pas de session correspondant au token fourni.");
//        }
//        if (Timestamp.from(Instant.now()).getTime() - session.getTimestamp().getTime() > SESSION_TIME) {
//            throw new AuthorizationException("Session expirée.");
//        }
//        Role memberRole = memberDao.findRoleByIdMember(session.getMemberId());
//        checkRole(role, memberRole);
//    }

//    private void checkRole(Role authorizedRole, Role memberRole) throws AuthorizationException {
//        switch (memberRole) {
//            case GUEST:
//                if (authorizedRole.equals(Role.MEMBER)) {
//                    throw new AuthorizationException("Rôle insuffisant.");
//                }
//            case MEMBER:
//                break;
//            default:
//                if (authorizedRole.equals(Role.GUEST) || authorizedRole.equals(Role.MEMBER)) {
//                    throw new AuthorizationException("Rôle insuffisant.");
//                }
//        }
//    }

//    private String issueToken(String username) {
//        return "123_" + username;
//    }

