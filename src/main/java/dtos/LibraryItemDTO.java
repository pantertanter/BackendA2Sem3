package dtos;

import entities.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class LibraryItemDTO {

    private String bookKey;

    public LibraryItemDTO(String bookKey) {
        this.bookKey = bookKey;
    }

    public LibraryItemDTO(LibraryItem entity) {
        this.bookKey = entity.getBookKey();
    }

    public static List<LibraryItemDTO> getDTOs(List<LibraryItem> entities) {
        List<LibraryItemDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(new LibraryItemDTO(e)));
        return dtos;
    }

    public String getBookKey() {
        return bookKey;
    }
}
