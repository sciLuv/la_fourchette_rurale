package fr.eql.lyra.controller;

import fr.eql.lyra.business.MemberBusiness;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.dto.NewMemberEntityDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/member")
public class NewMemberController {

    @EJB
    MemberBusiness memberBusiness;
    @POST
    @Path("/new/member")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response test2(NewMemberEntityDto newMemberEntityDto){
        System.out.println(newMemberEntityDto.getBirthdate());
        System.out.println(newMemberEntityDto.getDateCarte());
        for (int deliveryDay : newMemberEntityDto.getDeliveryDays()) {
            System.out.println(deliveryDay);
        }
        String test = memberBusiness.sendNewMember(newMemberEntityDto);
        return Response.ok(test).build();
    }
}
