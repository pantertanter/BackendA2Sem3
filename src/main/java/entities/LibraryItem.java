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

    public LibraryItem() {
    }

    public LibraryItem(LibraryItemDTO dto) {
        this.bookKey = dto.getBookKey();
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
}
