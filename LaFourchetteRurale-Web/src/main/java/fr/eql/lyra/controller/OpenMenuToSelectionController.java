package fr.eql.lyra.controller;

import fr.eql.lyra.business.MenuOpeningBusiness;
import fr.eql.lyra.entity.Menu;
import fr.eql.lyra.entity.dto.MenuOpenSelectionDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;


@Stateless
@Path("/admi/publication")
public class OpenMenuToSelectionController {

    @EJB
    MenuOpeningBusiness menuOpeningBusiness;
    @GET
    @Path("/menus/{dietParam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableMenusByDiet(@PathParam("dietParam") String diet) {
        List<Menu> availableMenus = menuOpeningBusiness.findAvailableMenusByDiet(diet);
        return Response.ok(availableMenus).build();
    }

    @GET
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastEndingDate() {
        LocalDate localDate = menuOpeningBusiness.getLastEndSelectionDate();
        return Response.ok(localDate).build();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMenuToSelectionOpening (MenuOpenSelectionDto menuOpenSelectionDto) {
        menuOpeningBusiness.insertMenuOpenSelection(menuOpenSelectionDto);
        return Response.ok().build();
    }
}
