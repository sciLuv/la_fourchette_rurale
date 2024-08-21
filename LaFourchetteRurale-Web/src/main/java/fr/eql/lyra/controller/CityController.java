package fr.eql.lyra.controller;

import fr.eql.lyra.business.CityBusiness;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import fr.eql.lyra.entity.Ville;

import java.util.List;

@Stateless
@Path("/villes")
public class CityController {

    @EJB
    CityBusiness cityBusiness;

    @GET
    @Path("/allCities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCities() {
        List<Ville> cities = cityBusiness.getAllCities();
        if (cities != null && !cities.isEmpty()) {
            return Response.ok(cities).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/byCityName/{cityName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCityByCityName(@PathParam("cityName") String cityName) {
        Ville ville = cityBusiness.findCityByCityName(cityName);
        if (ville != null) {
            return Response.ok(ville).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/byCityCP/{cityCP}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCityByCityCP(@PathParam("cityCP") int cityCP) {
        List<Ville> villes = cityBusiness.findCityByCityCP(cityCP);
        if (villes != null) {
            return Response.ok(villes).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewCity(Ville ville) {
        cityBusiness.addNewCity(ville);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCity(@PathParam("id") int id) {
        cityBusiness.disableCity(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
