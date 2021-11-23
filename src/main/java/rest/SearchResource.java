package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        /* TODO: make DTOs for books and this search result.
            Include urls for thumbnails.
            Dont know what to do with authors yet, if they should be a nested object or not.
              It would be a lot repeated objects if the same author wrote a lot of books in the search result
            Maybe add pagination parameters to the endpoint.
         */
        BookSearchResultsDTO result = searchFacade.getBookSearchResult(search);
        return GSON.toJson(result);
    }
}