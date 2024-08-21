package fr.eql.lyra.controller;


import fr.eql.lyra.business.OrderDetailBusiness;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.MenuOpenSelection;
import fr.eql.lyra.entity.OrderDetail;
import fr.eql.lyra.entity.dto.OrderDetailIdsAndDateDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/orderDetail")
public class OrderDetailController {

    @EJB
    OrderDetailBusiness orderDetailBusiness;

    @GET
    @Path("/member/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(@PathParam("id") int memberId){
        List<OrderDetail> orderDetails = orderDetailBusiness.getAllOrderDetailForAMember(memberId);
        return Response.ok(orderDetails).build();
    }

    @POST
    @Path("/payment/date")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test2(int[] orderDetailIds){
        for (int orderDetailId : orderDetailIds) {
            System.out.println(orderDetailId);
        }
        String menus = orderDetailBusiness.setPaymentDateForOrderDetails(orderDetailIds);
        return Response.ok(menus).build();
    }
}
