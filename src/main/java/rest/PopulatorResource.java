package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.User;
import utils.EMF_Creator;
import utils.SetupTestUsersWithResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("pop")
public class PopulatorResource {

        private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
        private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("testusers")
    public String testUsers() {
        SetupTestUsersWithResponse stu = new SetupTestUsersWithResponse();
        return stu.populate();
    }
}
