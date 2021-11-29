package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AuthorDTO;
import dtos.BookSearchResultsDTO;
import facades.SearchFacade;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/search")
public class SearchResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final SearchFacade searchFacade = SearchFacade.getSearchFacade();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Welcome to the search engine.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{search}")
    public String searchBasic(@PathParam("search") String search) throws IOException {
        /* TODO: Maybe add pagination parameters to the endpoint. Limit is currently in SearchFacade*/
        BookSearchResultsDTO result = searchFacade.getBookSearchResult(search);
        return GSON.toJson(result);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("author/{id}")
    public String getAuthor(@PathParam("id") String id) throws IOException {
        AuthorDTO result = searchFacade.getAuthor(id);
        return GSON.toJson(result);
    }
}