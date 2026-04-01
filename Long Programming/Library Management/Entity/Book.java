package t.com.libary.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(name = "bookName")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;
    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}