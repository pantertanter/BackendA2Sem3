package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.LibraryItemDTO;
import facades.UserFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("library")
public class LibraryResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final UserFacade userFacade = UserFacade.getUserFacade(EMF);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Welcome to the user library.";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("add")
    @RolesAllowed("user")
    public String addBook(String jsonString) {
        String username = securityContext.getUserPrincipal().getName();
        LibraryItemDTO itemDTO = GSON.fromJson(jsonString, LibraryItemDTO.class);
        List<LibraryItemDTO> newLibrary = userFacade.addBook(username, itemDTO);
        return GSON.toJson(newLibrary);
    }
}