
package t.com.libary.BookService;

import t.com.libary.Entity.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t.com.libary.BookRepository.RepositoryClass;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private RepositoryClass repo;

    public List<Book> getbook() {
        return repo.findAll();
    }

    public Book addBook(Book b) {
        return repo.save(b);
    }

    public Book updateall(int bookId, Book b) {
        Book exists = repo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        exists.setBookName(b.getBookName());
        exists.setAuthor(b.getAuthor());
        exists.setPublisher(b.getPublisher());

        return repo.save(exists);
    }

    public String deleteall(int bookId) {
        repo.deleteById(bookId);
        return "Deleted";
    }
}
