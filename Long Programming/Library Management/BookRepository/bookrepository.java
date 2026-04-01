package t.com.libary.BookRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import t.com.libary.Entity.Book;

@Repository
public interface bookrepository extends JpaRepository<Book, Integer> {

}