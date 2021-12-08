package dtos;

public class LibraryItemWithBookDTO {
    private String status;
    private int rating;
    private BookDTO book;

    // change this when status and ratings exist.
    public LibraryItemWithBookDTO(LibraryItemDTO libraryItem, BookDTO book) {
        this.status = libraryItem.getStatus();
        this.rating = libraryItem.getRating();
        this.book = book;
    }
}
