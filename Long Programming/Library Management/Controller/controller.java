package t.com.libary.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import t.com.libary.BookService.bookservice;
import t.com.libary.Entity.Book;

import java.util.List;

@RestController
@RequestMapping("/api")
public class controller {

    @Autowired
    private bookservice book;

    @GetMapping("/book")
    public List<Book> getbook() {
        return book.getbook();
    }

    @PostMapping("/addbook")
    public Book addbook(@RequestBody Book b) {
        return book.saved(b);
    }

    @PutMapping("/{id}")
    public Book updateall(@PathVariable("id") int bookid, @RequestBody Book b) {
        return book.updateall(bookid,b);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int bookid) {
        return book.remove(bookid);
    }
}
