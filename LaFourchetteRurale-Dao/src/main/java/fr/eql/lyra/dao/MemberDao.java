package fr.eql.lyra.dao;

import fr.eql.lyra.entity.DeliveryAvailability;
import fr.eql.lyra.entity.FoodSpecificityFavorite;
import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.Membership;
import fr.eql.lyra.entity.dto.Member1Dto;

import java.util.List;


public interface MemberDao {
    long addNewUser(Member1Dto member);
    String addNewMember(Member member, List<DeliveryAvailability> deliveryAvailabilities, FoodSpecificityFavorite foodSpecificityFavorite, Membership membership );
}
