package fr.eql.lyra.controller;

import fr.eql.lyra.business.DietBusiness;
import fr.eql.lyra.entity.Diet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Stateless
@Path("/diet")
public class DietController {
    @EJB
    DietBusiness dietBusiness;
    @GET
    @Path("/diets/valid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test4(){
        List<Diet> diets = dietBusiness.getAllActiveDiet();
        return Response.ok(diets).build();
    }
}
