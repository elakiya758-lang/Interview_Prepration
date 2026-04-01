
package t.com.libary.Controller;

import t.com.libary.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import t.com.libary.BookService.ClassService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class ClassController {

    @Autowired
    private ClassService service;

    @GetMapping("/getbooks")
    public List<Book> getBooks() {
        return service.getbook();
    }

    @PostMapping("/postbooks")
    public Book addBook(@RequestBody Book b) {
        return service.addBook(b);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book b) {
        return service.updateall(id, b);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return service.deleteall(id);
    }
}
