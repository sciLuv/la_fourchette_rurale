package fr.eql.lyra.controller;


import fr.eql.lyra.business.AdhDeliveryDateBusiness;
import fr.eql.lyra.entity.Ville;

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
@Path("/AdhDeliveryDate")
public class AdhDeliveryDateController {

    @EJB
    AdhDeliveryDateBusiness adhDeliveryDateBusiness;


    @GET
    @Path("/allDates/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test10(@PathParam("id") int id) {
        List<Integer> dates = adhDeliveryDateBusiness.getDeliveryDateOfMember(id);
            return Response.ok(dates).build();
    }
}
