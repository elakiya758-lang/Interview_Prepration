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
    public int bookid;
    @Column(name="Booktitle")
    private String Booktitle;
    @Column(name="Author")
    private String Author;
    @Column(name="Publiser")
    private String Publiser;
    @ManyToOne
    @JoinColumn(name="userid",nullable = false)
    private User userid;
}
