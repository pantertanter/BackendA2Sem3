package dtos;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsEditionDTO {
    private String subtitle;
    private String description;
    private List<String> series;
    private String edition_name;
    private String physical_format;

    public BookDetailsEditionDTO(String subtitle, String description, List<String> series, String edition_name, String physical_format) {
        this.subtitle = subtitle;
        this.description = description;
        this.series = series;
        this.edition_name = edition_name;
        this.physical_format = physical_format;
    }

    public BookDetailsEditionDTO() {
        this.subtitle = "";
        this.description = "";
        this.series = new ArrayList<>();
        this.edition_name = "";
        this.physical_format = "";
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSeries() {
        return series;
    }

    public String getEdition_name() {
        return edition_name;
    }

    public String getPhysical_format() {
        return physical_format;
    }
}
