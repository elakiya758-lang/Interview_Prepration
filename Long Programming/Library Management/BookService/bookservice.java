package t.com.libary.BookService;
import t.com.libary.BookRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t.com.libary.BookRepository.bookrepository;
import t.com.libary.Entity.Book;
import t.com.libary.Entity.User;

import java.util.List;

@Service
public class bookservice {

    @Autowired
    private bookrepository repo;
    @Autowired
    private UserRepository userRepo;

    public List<Book> getbook() {
        return repo.findAll();
    }

    public Book saved(Book b) {
        int id = b.getUserid().getUserid();
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        b.setUserid(user);
        return repo.save(b);
    }

    public  Book updateall(int bookid, Book b) {
        Book existing = repo.findById(bookid)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookid));

        existing.setBooktitle(b.getBooktitle());
        existing.setAuthor(b.getAuthor());
        existing.setPubliser(b.getPubliser());

        return repo.save(existing);
    }

    public String remove(int bookid) {
        if (repo.existsById(bookid)) {
            repo.deleteById(bookid);
            return "Book Removed Successfully";
        } else {
            throw new RuntimeException("Book Not Found with id: " + bookid);
        }
    }
}