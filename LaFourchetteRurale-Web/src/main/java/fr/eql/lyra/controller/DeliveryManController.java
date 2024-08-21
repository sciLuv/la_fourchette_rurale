package fr.eql.lyra.controller;

import fr.eql.lyra.business.AuthenticationException;
import fr.eql.lyra.business.DeliveryManBusiness;
import fr.eql.lyra.business.TourneeBusiness;
import fr.eql.lyra.entity.DeliveryMan;
import fr.eql.lyra.entity.Tournee;
import fr.eql.lyra.entity.dto.DeliveryManDto;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/deliveryMan")
public class DeliveryManController {

    @EJB
    DeliveryManBusiness deliveryManBusiness;


    @GET
    @Path("/allDeliveryMans")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDeliveryMans() {
        List<DeliveryMan> livreurs = deliveryManBusiness.getAllDeliveryMan();
        if (livreurs != null && !livreurs.isEmpty()) {
            return Response.ok(livreurs).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @GET
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@QueryParam("mail") String email, @QueryParam("password") String password){
        try {
            // 调用 DeliveryManBusiness 中的 authenticate 方法来验证配送员
            DeliveryManDto authenticatedDeliveryMan = deliveryManBusiness.authenticate(email, password);
            // 如果验证成功，返回配送员信息
            return Response.ok(authenticatedDeliveryMan).build();
        } catch (AuthenticationException e) {
            // 如果验证失败，返回错误信息
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }

    }

    @POST
    @Path("/resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword(@QueryParam("email") String email, @QueryParam("newPassword") String newPassword){
        try {
            // 调用 DeliveryManBusiness 中的 resetPassword 方法来重置配送员的密码
            deliveryManBusiness.resetPassword(email, newPassword);
            // 返回成功消息
            return Response.ok("Password reset successfully.").build();
        } catch (AuthenticationException e) {
            // 如果重置密码失败，返回错误信息
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
