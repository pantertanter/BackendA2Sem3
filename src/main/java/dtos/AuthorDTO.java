package dtos;

public class AuthorDTO {
    private String key;
    private String name;
    private String birth_date;
    private Bio bio;

    public AuthorDTO() {
    }

    public AuthorDTO(String key, String name) {
        this.key = key;
        this.name = name;
    }

    private class Bio {
        private String value;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public String getBio() {
        return bio != null
                ? bio.value
                : null;
    }
}
