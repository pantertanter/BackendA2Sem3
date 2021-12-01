package dtos;

import java.util.List;

public class LibraryDTO {
    private String username;
    private int size;
    private List<LibraryItemWithBookDTO> library;

    public LibraryDTO(String username, List<LibraryItemWithBookDTO> library) {
        this.username = username;
        this.size = library.size();
        this.library = library;
    }

    public String getUsername() {
        return username;
    }

    public int getSize() {
        return size;
    }

    public List<LibraryItemWithBookDTO> getLibrary() {
        return library;
    }
}
