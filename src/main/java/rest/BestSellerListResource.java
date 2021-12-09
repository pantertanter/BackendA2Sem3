package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BestSellerListDTO;
import dtos.BookWithDetailsDTO;
import facades.SearchFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/list")
public class BestSellerListResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final SearchFacade searchFacade = SearchFacade.getSearchFacade();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Welcome to the best seller list resource.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{genre}")
    public String getList(@PathParam("genre") String genre) throws IOException {
        BestSellerListDTO List = searchFacade.getBestSellerList(genre);
        return GSON.toJson(List);
    }
}