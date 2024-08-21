package fr.eql.lyra.controller;

import fr.eql.lyra.business.TourneeBusinessR;
import fr.eql.lyra.entity.Tournee;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Stateless
@Path("/tourneeR")
public class TourneeControllerR {

    @EJB
    TourneeBusinessR tourneeBusiness;


    @GET
    @Path("/byDate/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTourneesByDate(@PathParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Tournee> tournees = tourneeBusiness.findTourneesByDate(localDate);
        if (tournees != null) {
            return Response.ok(tournees).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}