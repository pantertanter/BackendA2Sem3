package dtos;

import entities.LibraryItem;
import entities.Role;

import java.util.ArrayList;
import java.util.List;

public class LibraryItemDTO {

    private String id;

    public LibraryItemDTO(String id) {
        this.id = id;
    }

    public LibraryItemDTO(LibraryItem entity) {
        this.id = entity.getId();
    }

    public static List<LibraryItemDTO> getDTOs(List<LibraryItem> entities) {
        List<LibraryItemDTO> dtos = new ArrayList<>();
        entities.forEach(e -> dtos.add(new LibraryItemDTO(e)));
        return dtos;
    }

    public String getId() {
        return id;
    }
}
