package fr.eql.lyra.controller;

import fr.eql.lyra.business.FoodSpecificityBusiness;
import fr.eql.lyra.entity.FoodSpecificity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/food")
public class FoodSpecificityController {

        @EJB
        FoodSpecificityBusiness foodSpecificityBusiness;

        @GET
        @Path("/foods/valid")
        @Produces(MediaType.APPLICATION_JSON)
        public Response test4(){
            List<FoodSpecificity> foodSpecificities = foodSpecificityBusiness.getAllFoodSpecificity();
            return Response.ok(foodSpecificities).build();
        }
}
