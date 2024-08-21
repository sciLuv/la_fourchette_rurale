package fr.eql.lyra.business.impl;


import fr.eql.lyra.business.MemberBusiness;
import fr.eql.lyra.dao.MemberDao;
import fr.eql.lyra.entity.DeliveryAvailability;
import fr.eql.lyra.entity.FoodSpecificityFavorite;
import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.Membership;
import fr.eql.lyra.entity.dto.Member1Dto;
import fr.eql.lyra.entity.dto.NewMemberEntityDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Remote(MemberBusiness.class)
@Stateless
public class MemberBusinessImpl implements MemberBusiness{

    @EJB
    MemberDao memberDao;

    @Override
    public long sendNewUSer(Member1Dto member) {
        return memberDao.addNewUser(member);
    }

    @Override
    public String sendNewMember(NewMemberEntityDto newMemberEntityDto) {
        long id = newMemberEntityDto.getId();
        Membership membership = new Membership(
                -1,
                (int) newMemberEntityDto.getMembershipType(),
                (int) newMemberEntityDto.getId(),
                newMemberEntityDto.getMembershipBegin(),
                newMemberEntityDto.getMembershipEnd(),
                null,
                newMemberEntityDto.getMembershipBegin(),
                newMemberEntityDto.getMembershipBegin()
        );
        FoodSpecificityFavorite foodSpecificityFavorite =
                new FoodSpecificityFavorite(
                        newMemberEntityDto.getMembershipType(),
                        newMemberEntityDto.getSelectedFood()
                );
        List<DeliveryAvailability> deliveryAvailabilities = new ArrayList<>();
        for (int deliveryDay : newMemberEntityDto.getDeliveryDays()) {
            deliveryAvailabilities.add(
                    new DeliveryAvailability(
                            (int) newMemberEntityDto.getMembershipType(),
                            deliveryDay,
                            newMemberEntityDto.getMembershipBegin()
                    )
            );
        }
        Member member = new Member(
                (int) newMemberEntityDto.getId(),
                (int) newMemberEntityDto.getGender(),
                newMemberEntityDto.getDiet(),
                (int) newMemberEntityDto.getTown(),
                "personne",
                "personne",
                "pasdemail",
                (int) newMemberEntityDto.getPhone(),
                newMemberEntityDto.getBirthdate(),
                newMemberEntityDto.getStreet(),
                newMemberEntityDto.getDeliveryInformation(),
                newMemberEntityDto.getBillAdress(),
                "pasdepassword",
                newMemberEntityDto.getNumCarte(),
                newMemberEntityDto.getDateCarte(),
                newMemberEntityDto.getPorteurCarte()
        );

        return memberDao.addNewMember(member, deliveryAvailabilities, foodSpecificityFavorite, membership);
    }
}
