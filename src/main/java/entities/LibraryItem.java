package entities;

import dtos.LibraryItemDTO;

import javax.persistence.*;

@Table(name = "library_item")
@Entity
public class LibraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_item_id", nullable = false)
    private Integer id;

    @Column(name = "book_key", nullable = false, length = 16)
    private String bookKey;

    @Column(name = "status", nullable = false, length = 16)
    private String status;

    @Column(name = "rating", nullable = false)
    private int rating;

    public LibraryItem() {
    }

    public LibraryItem(String bookKey) {
        this.bookKey = bookKey;
        this.status = "TO-READ";
        this.rating = 0;
    }

    public LibraryItem(String bookKey, String status, int rating) {
        this.bookKey = bookKey;
        this.status = status;
        this.rating = rating;
    }

    public LibraryItem(LibraryItemDTO dto) {
        this.bookKey = dto.getBookKey();
        this.status = dto.getStatus();
        this.rating = dto.getRating();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookKey() {
        return bookKey;
    }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

    public String getStatus() {
        return status;
    }

    public int getRating() {
        return rating;
    }
}
