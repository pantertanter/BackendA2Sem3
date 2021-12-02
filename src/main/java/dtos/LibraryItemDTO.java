package dtos;

import entities.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class LibraryItemDTO {

    private String bookKey;
    private String status;

    public LibraryItemDTO() {
    }

    public LibraryItemDTO(String bookKey) {
        this.bookKey = bookKey;
        this.status = "TO-READ";
    }

    public LibraryItemDTO(String bookKey, String status) {
        this.bookKey = bookKey;
        this.status = status;
    }

    public LibraryItemDTO(LibraryItem entity) {
        this.bookKey = entity.getBookKey();
        this.status = entity.getStatus();
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
}
