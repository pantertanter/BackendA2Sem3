package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import deserializer.BookDTODeserializerFromSearchResult;
import deserializer.BookSearchResultsDTODeserializer;
import dtos.*;
import utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public BookSearchResultsDTO getBookSearchResult(String search, int limit) throws IOException {
        // we could have a singleton Gson with all deserializers registered.
        Gson bookSearchResultGson = new GsonBuilder()
                .registerTypeAdapter(BookSearchResultsDTO.class, new BookSearchResultsDTODeserializer())
                .create();

        search = search.replace(' ',  '+');
        String baseUrl = "https://openlibrary.org";
        String fields = "&fields=title,first_publish_year,number_of_pages_median,cover_i,subject_key,subject_facet,author_key,author_name,key";
        String url = baseUrl + "/search.json?q=" + search + fields + "&limit=" + limit;
        String json = HttpUtils.fetch(url);
        return bookSearchResultGson.fromJson(json, BookSearchResultsDTO.class);
    }

    // This method is big and does multiple things.
    // However, I'm concerned that a refactor would impact performance a little bit. Computers are fast, so probably not a lot, though.
    // A refactor to separate concerns and having it return a List<BookDTO> would result in 2 more lists having to be iterated.
    // Alternatively, instead of using HttpUtils.runParallel(), we could make a thread pool and run getBookSearchResult more times.
    // I spent a long time deliberating it and decided against it, but it might be better still.
    public List<LibraryItemWithBookDTO> getLibraryItemsWithBooks(List<LibraryItemDTO> items) throws IOException, ExecutionException, InterruptedException {
        Gson bookSearchResultGson = new GsonBuilder()
                .registerTypeAdapter(BookSearchResultsDTO.class, new BookSearchResultsDTODeserializer())
                .create();

        String baseUrl = "https://openlibrary.org/search.json?";
        String baseQuery = "q=key:/works/";
        String fields = "&fields=title,first_publish_year,cover_i,author_key,author_name,key";
        String limit = "&limit=1";

        // creates urls based on Strings above and the key of the book which is added to the query
        List<String> urls = new ArrayList<>();
        items.forEach(i -> urls.add(baseUrl + baseQuery + i.getBookKey() + fields + limit));

        // fetches search results in parallel
        List<String> jsonBooks = HttpUtils.runParallel(urls);

        // iterates through original list of items, deserialises the corresponding search result,
        // and creates a new LibraryItemWithBook from the original item and the book from the search
        List<LibraryItemWithBookDTO> itemsWithBooks = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            BookSearchResultsDTO searchResult = bookSearchResultGson.fromJson(jsonBooks.get(i), BookSearchResultsDTO.class);
            BookDTO book = searchResult.getResults().size() > 0 ? searchResult.getResults().get(0) : null;
            itemsWithBooks.add(new LibraryItemWithBookDTO(
                    items.get(i),
                    book)
            );
        }
        return itemsWithBooks;
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

    public static void main(String[] args) throws IOException {
        SearchFacade sf = getSearchFacade();
        long start = System.currentTimeMillis();
        sf.getBookSearchResult("Dan Brown", 25);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
