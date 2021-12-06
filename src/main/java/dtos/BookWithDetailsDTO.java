package dtos;

import java.util.Arrays;
import java.util.List;

public class BookWithDetailsDTO {
    private String key;
    private String title;
    private int first_publish_year;
    private int number_of_pages_median;
    private List<String> thumbnail_urls;
    private List<AuthorDTO> authors;
    private List<SubjectDTO> subjects;

    private List<LinkDTO> links;

    private String subtitle;
    private List<String> series;
    private String edition_name;
    private String physical_format;

    private List<String> descriptions;

    public BookWithDetailsDTO(BookDTO book, BookDetailsWorkDTO work, BookDetailsEditionDTO edition) {
        this.key = book.getKey();
        this.title = book.getTitle();
        this.first_publish_year = book.getFirst_publish_year();
        this.number_of_pages_median = book.getNumber_of_pages_median();
        this.thumbnail_urls = book.getThumbnail_urls();
        this.authors = book.getAuthors();
        this.subjects = book.getSubjects();

        this.links = work.getLinks();

        this.subtitle = edition.getSubtitle();
        this.series = edition.getSeries();
        this.edition_name = edition.getEdition_name();
        this.physical_format = edition.getPhysical_format();

        this.descriptions = Arrays.asList(work.getDescription(), edition.getDescription());
    }
}
