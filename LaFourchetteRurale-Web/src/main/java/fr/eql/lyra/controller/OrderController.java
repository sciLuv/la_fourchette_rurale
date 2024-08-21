package fr.eql.lyra.controller;

import fr.eql.lyra.business.OrderMakingBusiness;
import fr.eql.lyra.entity.*;
import fr.eql.lyra.entity.dto.OrderAndPackageDto;
import fr.eql.lyra.entity.dto.OrderDetailDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/order")
public class OrderController {
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

    @POST
    @Path("/send/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response test5(@PathParam("id") long memberId, OrderAndPackageDto orderAndPackageDto){
        System.out.println("test1");
        String response = orderMakingBusiness.postOrder(memberId, orderAndPackageDto);
        System.out.println("memberID " + memberId);
        return Response.ok(response).build();
    }
}
