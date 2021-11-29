package entities;

import dtos.LibraryItemDTO;

import javax.persistence.*;

@Table(name = "library_item")
@Entity
public class LibraryItem {
    @Id
    @Column(name = "id", nullable = false, length = 16)
    private String id;

    @JoinColumn(name = "user")
    @ManyToOne
    private User user;

    public LibraryItem() {
    }

    public LibraryItem(String id) {
        this.id = id;
    }

    public LibraryItem(LibraryItemDTO dto) {
        this.id = dto.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
