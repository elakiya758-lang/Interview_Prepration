package t.com.libary.BookRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import t.com.libary.Entity.Book;

public interface RepositoryClass extends JpaRepository<Book, Integer> {
}