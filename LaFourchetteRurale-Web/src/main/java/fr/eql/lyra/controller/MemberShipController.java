package fr.eql.lyra.controller;


import fr.eql.lyra.business.MemberShipBusiness;
import fr.eql.lyra.entity.Membership;
import fr.eql.lyra.entity.OrderDetail;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/membership")
public class MemberShipController {

    @EJB
    MemberShipBusiness memberShipBusiness;

    @GET
    @Path("/member/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(@PathParam("id") int memberId){
        List<Membership> memberships = memberShipBusiness.getAllTypeOfMemberShip(memberId);
        return Response.ok(memberships).build();
    }
}
