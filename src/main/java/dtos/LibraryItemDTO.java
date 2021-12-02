package dtos;

import entities.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class LibraryItemDTO {

    private String bookKey;
    private String status;
    private int rating;

    public LibraryItemDTO() {
    }

    public LibraryItemDTO(String bookKey) {
        this.bookKey = bookKey;
        this.status = "TO-READ";
        this.rating = 0;
    }

    public LibraryItemDTO(String bookKey, String status, int rating) {
        this.bookKey = bookKey;
        this.status = status;
        this.rating = rating;
    }

    public LibraryItemDTO(LibraryItem entity) {
        this.bookKey = entity.getBookKey();
        this.status = entity.getStatus();
        this.rating = entity.getRating();
    }

    public static List<LibraryItemDTO> getDTOs(List<LibraryItem> entities) {
        List<LibraryItemDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(new LibraryItemDTO(e)));
        return dtos;
    }

    public String getBookKey() {
        return bookKey;
    }

    public String getStatus() {
        return status;
    }

    public int getRating() {
        return rating;
    }
}
