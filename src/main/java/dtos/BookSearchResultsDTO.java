package dtos;

import java.util.List;

public class BookSearchResultsDTO {
    private int numFound;
    private int limit;
    private int start;
    private int offset;
    private String query;
    // this can be refactored into a SearchResultDTO (singular) if we want.
    private List<BookDTO> results;

    public BookSearchResultsDTO() {
    }

    public BookSearchResultsDTO(int numFound, int start, int offset, String query, List<BookDTO> results) {
        this.numFound = numFound;
        this.start = start;
        this.offset = offset;
        this.query = query;
        this.results = results;
    }

    public List<BookDTO> getResults() {
        return results;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
