package dtos;

import java.util.List;

public class BestSellerListDTO {

        private string list_name_encoded ;
        private string display_name;

         private string title;
         private string author;
         private string description;
         private string publisher;
         private string primary_isbn10;
         private string primary_isbn13 ;
         private string book_image;
         private int book_image_width;
         private int book_image_height;

    public BestSellerListDTO(string list_name_encoded, string display_name, string title, string author, string description, string publisher, string primary_isbn10, string primary_isbn13, string book_image, book_image_width, book_image_height) {
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

    public string getList_name_encoded() {
        return list_name_encoded;
    }

    public string getDisplay_name() {
        return display_name;
    }

    public string getTitle() {
        return title;
    }

    public string getAuthor() {
        return author;
    }

    public string getDescription() {
        return description;
    }

    public string getPublisher() {
        return publisher;
    }

    public string getPrimary_isbn10() {
        return primary_isbn10;
    }

    public string getPrimary_isbn13() {
        return primary_isbn13;
    }

    public string getBook_image() {
        return book_image;
    }

    public int getBook_image_width() {
        return book_image_width;
    }

    public int getBook_image_height() {
        return book_image_height;
    }
}
