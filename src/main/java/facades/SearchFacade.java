package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deserializer.BookSearchResultsDTODeserializer;
import dtos.AuthorDTO;
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
        int limit = 25;
        String url = baseUrl + "/search.json?q=" + search + "&limit=" + limit;
        String json = HttpUtils.fetch(url);
        return bookSearchResultGson.fromJson(json, BookSearchResultsDTO.class);
    }

    public AuthorDTO getAuthor(String id) throws IOException {
        Gson gson = new Gson();
        String url = "https://openlibrary.org/authors/" + id + ".json";
        String json = HttpUtils.fetch(url);
        return gson.fromJson(json, AuthorDTO.class);
    }

    // when we need author covers, this can be refactored to be more generic, with other methods that call this for book/author specifically
    public List<String> getCoverUrlsById(int id) {
        List<String> urls = new ArrayList<>();
        if (id == 0) {
            urls.add("https://openlibrary.org/images/icons/avatar_book-sm.png");
            urls.add("https://openlibrary.org/images/icons/avatar_book.png");
            urls.add("https://openlibrary.org/images/icons/avatar_book-lg.png");
        }
        else {
            String baseUrl = "https://covers.openlibrary.org/b/id/" + id;
            urls.add(baseUrl + "-S.jpg");
            urls.add(baseUrl + "-M.jpg");
            urls.add(baseUrl + "-L.jpg");
        }
        return urls;
    }
}
