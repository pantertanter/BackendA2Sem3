package dtos;

import java.util.List;

public class BookDTO {
    private String key;
    private String title;
    private int first_publish_year;
    private int number_of_pages_median;
    private List<String> thumbnail_urls;
    private List<AuthorDTO> authors;
    private List<SubjectDTO> subjects;

    public BookDTO(String key, String title, int first_publish_year, int number_of_pages_median, List<String> thumbnail_urls, List<AuthorDTO> authors, List<SubjectDTO> subjects) {
        this.key = key;
        this.title = title;
        this.first_publish_year = first_publish_year;
        this.number_of_pages_median = number_of_pages_median;
        this.thumbnail_urls = thumbnail_urls;
        this.authors = authors;
        this.subjects = subjects;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public int getFirst_publish_year() {
        return first_publish_year;
    }

    public int getNumber_of_pages_median() {
        return number_of_pages_median;
    }

    public List<String> getThumbnail_urls() {
        return thumbnail_urls;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }
}
