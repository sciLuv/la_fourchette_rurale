package fr.eql.lyra.controller;


import fr.eql.lyra.business.MemberBusiness;
import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.dto.Member1Dto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/registration")
public class RegistrationController {

    @EJB
    MemberBusiness memberBusiness;

    @POST
    @Path("/new/user")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response test2(Member1Dto member){
        long response = memberBusiness.sendNewUSer(member);
        return Response.ok(response).build();
    }
}
