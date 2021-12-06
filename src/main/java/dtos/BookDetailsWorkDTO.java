package dtos;

import java.util.List;

public class BookDetailsWorkDTO {
    private String description;
    private List<LinkDTO> links;

    public BookDetailsWorkDTO(String description, List<LinkDTO> links) {
        this.description = description;
        this.links = links;
    }

    public String getDescription() {
        return description;
    }

    public List<LinkDTO> getLinks() {
        return links;
    }
}
