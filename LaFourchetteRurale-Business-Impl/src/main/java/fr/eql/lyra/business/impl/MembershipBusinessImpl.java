package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.MemberShipBusiness;
import fr.eql.lyra.business.OrderDetailBusiness;
import fr.eql.lyra.dao.MemberShipDao;
import fr.eql.lyra.entity.Membership;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(MemberShipBusiness.class)
@Stateless
public class MembershipBusinessImpl implements MemberShipBusiness {

    @EJB
    MemberShipDao memberShipDao;

    @Override
    public List<Membership> getAllTypeOfMemberShip(int memberId) {
        return memberShipDao.findAllMembershipForAMember(memberId);
    }
}
