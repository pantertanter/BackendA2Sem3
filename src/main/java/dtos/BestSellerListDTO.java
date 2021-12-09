package dtos;

import java.util.List;

public class BestSellerListDTO {

    private String list_name_encoded;
    private String display_name;

    private String title;
    private String author;
    private String description;
    private String publisher;
    private String primary_isbn10;
    private String primary_isbn13;
    private String book_image;
    private int book_image_width;
    private int book_image_height;

    public BestSellerListDTO(String list_name_encoded,
                             String display_name,
                             String title, String author,
                             String description,
                             String publisher,
                             String primary_isbn10,
                             String primary_isbn13,
                             String book_image,
                             int book_image_width,
                             int book_image_height) {

        this.list_name_encoded = list_name_encoded;
        this.display_name = display_name;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.primary_isbn10 = primary_isbn10;
        this.primary_isbn13 = primary_isbn13;
        this.book_image = book_image;
        this.book_image_width = book_image_width;
        this.book_image_height = book_image_height;

    }

    public String getList_name_encoded() {
        return list_name_encoded;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPrimary_isbn10() {
        return primary_isbn10;
    }

    public String getPrimary_isbn13() {
        return primary_isbn13;
    }

    public String getBook_image() {
        return book_image;
    }

    public int getBook_image_width() {
        return book_image_width;
    }

    public int getBook_image_height() {
        return book_image_height;
    }
}
