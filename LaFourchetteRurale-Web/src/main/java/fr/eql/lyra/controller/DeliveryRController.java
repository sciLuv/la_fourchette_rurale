package fr.eql.lyra.controller;


import fr.eql.lyra.business.DeliveryRBusiness;
import fr.eql.lyra.entity.Diet;
import fr.eql.lyra.entity.Member;
import fr.eql.lyra.entity.dto.DeliveryinformationDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Stateless
@Path("/deliveryR")
public class DeliveryRController {
    @EJB
    DeliveryRBusiness deliveryRBusiness;

    @POST
    @Path("/getPackage/{id}/{idVille}/{dateOfDelivery}")
    @Produces(MediaType.APPLICATION_JSON)
     public Response test4(@PathParam("id")int id, @PathParam("idVille") int idVille,@PathParam("dateOfDelivery") String dateOfDelivery){
         LocalDate localDate = LocalDate.parse(dateOfDelivery);
         List<DeliveryinformationDto> deliveryinformationDtos = deliveryRBusiness.findAndUpdatePackage(id, idVille, localDate);
         return Response.ok(deliveryinformationDtos).build();
     }

    @POST
    @Path("/beginTournee/{tourneeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test5(@PathParam("tourneeId")int idTournee){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Affichez le LocalDateTime
        System.out.println(idTournee);
        String resp = deliveryRBusiness.addBeginTournee(idTournee, currentDateTime);
        return Response.ok(resp).build();
    }

    @GET
    @Path("/memberInfo/{memberId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test6(@PathParam("memberId")int idMember){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Affichez le LocalDateTime
        System.out.println(idMember);
        Member resp = deliveryRBusiness.searchMember(idMember);
        return Response.ok(resp).build();
    }

    @POST
    @Path("/endDelivery/{nolivraisonId}/{packageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test7(@PathParam("nolivraisonId")int nolivraisonId, @PathParam("packageId")int packageId){

        System.out.println(nolivraisonId);
        System.out.println(packageId);
        String resp = deliveryRBusiness.setEndOfDelivery(nolivraisonId, packageId);
        return Response.ok(resp).build();
    }

    @POST
    @Path("/towns")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test8(int[] ids){
        List<String>  resp = deliveryRBusiness.getTownName(ids);
        return Response.ok(resp).build();
    }

    @PUT
    @Path("/endOfTournee/{id}")
    public Response test9(@PathParam("id")int id){
        deliveryRBusiness.addEndOfTournee(id);
        return Response.ok("et oui").build();
    }

}