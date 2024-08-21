package fr.eql.lyra.business;

import fr.eql.lyra.entity.dto.Member1Dto;
import fr.eql.lyra.entity.dto.NewMemberEntityDto;

public interface MemberBusiness {
    public long sendNewUSer(Member1Dto member);

    public String sendNewMember(NewMemberEntityDto newMemberEntityDto);


}
