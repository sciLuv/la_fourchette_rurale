package fr.eql.lyra.controller;

import fr.eql.lyra.business.DietBusiness;
import fr.eql.lyra.business.DishTypeBusiness;
import fr.eql.lyra.entity.Diet;
import fr.eql.lyra.entity.DishType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/dishType")
public class DishTypeController {
    @EJB
    DishTypeBusiness dishTypeBusiness;
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test6(){
        List<DishType> dishsTypes = dishTypeBusiness.getAllActiveDishType();
        return Response.ok(dishsTypes).build();
    }
}
