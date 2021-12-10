package dtos;

import java.util.List;

public class BestSellerListDTO {

    private String list_name_encoded;
    private String display_name;

    private List<Book> books;

    private class Book {
        private String title;
        private String author;
        private String description;
        private String publisher;
        private String primary_isbn10;
        private String primary_isbn13;
        private String book_image;
        private int book_image_width;
        private int book_image_height;
    }

    public BestSellerListDTO() {

    }

}
