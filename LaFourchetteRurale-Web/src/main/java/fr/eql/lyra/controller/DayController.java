package fr.eql.lyra.controller;

import fr.eql.lyra.business.DayBusiness;
import fr.eql.lyra.entity.Day;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/day")
public class DayController {
    @EJB
    DayBusiness dayBusiness;
    @GET
    @Path("/all/days")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test5(){
        List<Day> days = dayBusiness.getAllDays();
        return Response.ok(days).build();
    }
}
