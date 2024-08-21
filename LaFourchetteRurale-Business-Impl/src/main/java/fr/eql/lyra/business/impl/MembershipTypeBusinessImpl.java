package fr.eql.lyra.business.impl;

import fr.eql.lyra.business.MemberShipBusiness;
import fr.eql.lyra.business.MembershipTypeBusiness;
import fr.eql.lyra.dao.MembershipTypeDao;
import fr.eql.lyra.entity.MembershipType;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Remote(MembershipTypeBusiness.class)
@Stateless
public class MembershipTypeBusinessImpl implements MembershipTypeBusiness {

    @EJB
    MembershipTypeDao membershipTypeDao;

    @Override
    public List<MembershipType> getAllMembershipType(){
        return membershipTypeDao.findAllMembershipType();
    };

}
