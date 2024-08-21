package fr.eql.lyra.controller;

import fr.eql.lyra.business.OrderMakingBusiness;
import fr.eql.lyra.entity.Dish;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.MenuOpenSelection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Path("/order")
public class Controller {
    @EJB
    OrderMakingBusiness orderMakingBusiness;

    @GET
    @Path("/MenuOpenSelection/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(@PathParam("date") String date){
        List<MenuOpenSelection> menus = orderMakingBusiness.getMenuOpenToSelection(date);
        return Response.ok(menus).build();
    }

    @POST
    @Path("/display/menu")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test2(int[] menusId){
        List<Menu> menus = orderMakingBusiness.getMenu(menusId);
        return Response.ok(menus).build();
    }

    @POST
    @Path("/display/dishes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test3(int[] menusId){
        List<Dish> dishes = orderMakingBusiness.getDishesFromMenuId(menusId);
        return Response.ok(dishes).build();
    }
}
