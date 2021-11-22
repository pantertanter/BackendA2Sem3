package rest;

import utils.HttpUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/search")
public class SearchResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "Welcome to the search engine.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{search}")
    public String searchBasic(@PathParam("search") String search) throws IOException {
        search = search.replace(' ',  '+');
        String baseUrl = "https://openlibrary.org";
        String url = String.format("%s/search.json?q=%s", baseUrl, search);
        /* TODO: make DTOs for books and this search result.
            Include urls for thumbnails.
            Dont know what to do with authors yet, if they should be a nested object or not.
              It would be a lot repeated objects if the same author wrote a lot of books in the search result
            Maybe add pagination parameters to the endpoint.
         */
        return HttpUtils.fetch(url);
    }
}