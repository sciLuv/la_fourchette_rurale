package fr.eql.lyra.controller;

import fr.eql.lyra.business.DietBusiness;
import fr.eql.lyra.business.MembershipTypeBusiness;
import fr.eql.lyra.entity.Diet;
import fr.eql.lyra.entity.MembershipType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Path("/membershipType")
public class MembershipTypeController {

    @EJB
    MembershipTypeBusiness membershipTypeBusiness;
    @GET
    @Path("/membershipTypes/valid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test4(){
        List<MembershipType> membershipTypes = membershipTypeBusiness.getAllMembershipType();
        return Response.ok(membershipTypes).build();
    }
}
