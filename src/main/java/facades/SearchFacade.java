package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deserializer.BookSearchResultsDTODeserializer;
import dtos.BookSearchResultsDTO;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFacade {
    private static SearchFacade instance;

    private SearchFacade() {
    }

    public static SearchFacade getSearchFacade() {
        if (instance == null) {
            instance = new SearchFacade();
        }
        return instance;
    }

    public BookSearchResultsDTO getBookSearchResult(String search) throws IOException {
        // we could have a singleton Gson with all deserializers registered.
        Gson bookSearchResultGson = new GsonBuilder()
                .registerTypeAdapter(BookSearchResultsDTO.class, new BookSearchResultsDTODeserializer())
                .create();

        search = search.replace(' ',  '+');
        String baseUrl = "https://openlibrary.org";
        String url = String.format("%s/search.json?q=%s", baseUrl, search);
        String json = HttpUtils.fetch(url);
        return bookSearchResultGson.fromJson(json, BookSearchResultsDTO.class);
    }

    // when we need author covers, this can be refactored to be more generic, with other methods that call this for book/author specifically
    public List<String> getCoverUrlsById(int id) {
        List<String> urls = new ArrayList<>();
        String baseUrl = String.format("https://covers.openlibrary.org/b/id/%d-", id);
        urls.add(baseUrl + "S.jpg");
        urls.add(baseUrl + "M.jpg");
        urls.add(baseUrl + "L.jpg");
        return urls;
    }
}
