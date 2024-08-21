package fr.eql.lyra.controller;

import fr.eql.lyra.business.TourneeBusiness;
import fr.eql.lyra.entity.Tournee;
import fr.eql.lyra.entity.Ville;
import fr.eql.lyra.entity.dto.TourneeDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Stateless
@Path("/tournee")
public class TourneeController {

    @EJB
    TourneeBusiness tourneeBusiness;

    @POST
    @Path("/addListTournee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(TourneeDto[] tournees) {
        tourneeBusiness.addTournee(tournees);
        return Response.ok("coucou").build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTourneeById(@PathParam("id") int id) {
        Tournee tournee = tourneeBusiness.findTourneeById(id);
        if (tournee != null) {
            return Response.ok(tournee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/byDate/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTourneesByDate(@PathParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Tournee> tournees = tourneeBusiness.findTourneesByDate(localDate);
        if (!tournees.isEmpty()) {
            return Response.ok(tournees).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/byVille/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findTourneesByVille(@PathParam("cityName") String cityName) {
        Ville ville = new Ville();
        ville.setCityName(cityName);
        List<Tournee> tournees = tourneeBusiness.findTourneesByVille(ville);
        if (!tournees.isEmpty()) {
            return Response.ok(tournees).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewTournee(Tournee tournee) {
        tourneeBusiness.addTournee(tournee);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTournee(@PathParam("id") int id, Tournee tournee) {
        tournee.setTourneeId(id);
        tourneeBusiness.updateTournee(tournee);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTournee(@PathParam("id") int id) {
        tourneeBusiness.deleteTournee(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    // 这个方法根据配送员ID查找对应的行程，并返回JSON格式的行程列表
    @GET
    @Path("byDeliveryManID/{deliveryManId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTourneesByDeliveryManId(@PathParam("deliveryManId") int deliveryManId) {

        System.out.println("test controller1");
        List<Tournee> tournees = tourneeBusiness.getTourneesByDeliveryManId(deliveryManId);
        System.out.println("test controller2");
        if (!tournees.isEmpty()) {
            return Response.ok(tournees).build();
        } else {
            return Response.ok("rien").build();
        }
    }

    @GET
    @Path("/getTownByTournee/{tourneeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTownByTournee(@PathParam("tourneeId") int tourneeId ) {
        List<Integer> TownIds = tourneeBusiness.getTownByTournee(tourneeId);
        if (!TownIds.isEmpty()) {
            return Response.ok(TownIds).build();
        } else {
            return Response.ok("tournée sans ville associée").build();
        }
    }

}
