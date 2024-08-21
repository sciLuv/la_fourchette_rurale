package fr.eql.lyra.controller;


import fr.eql.lyra.business.AuthenticationException;
import fr.eql.lyra.business.SecurityABusiness;
import fr.eql.lyra.entity.dto.CredentialsADto;
import fr.eql.lyra.entity.dto.DeliveryManADto;
import fr.eql.lyra.entity.dto.MemberADto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/connection")
public class ConnectionController {

    @EJB
    SecurityABusiness securityABusiness;

    @POST
    @Path("/authenticate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(CredentialsADto credentialsADto) {
        try {
            MemberADto memberADto = securityABusiness.authenticate(credentialsADto.getUsername(), credentialsADto.getPassword());
            return Response.ok(memberADto).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/authenticateDeliveryMan")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateDeliveryMan(CredentialsADto credentialsADto) {
        try {
            DeliveryManADto deliveryManADto = securityABusiness.authenticateDeliveryMan(credentialsADto.getUsername(), credentialsADto.getPassword());
            return Response.ok(deliveryManADto).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/authenticate/admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateAdmin(CredentialsADto credentialsADto) {
        try {
           boolean isadmin = securityABusiness.authorizeAdmin(credentialsADto.getUsername(), credentialsADto.getPassword());
            System.out.println(isadmin);
            return Response.ok(isadmin).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
