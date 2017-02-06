package example.api;

import example.persistence.entity.Book;
import example.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity(bookRepository.getBooks(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        return createResponse(bookRepository.getBook(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", path = "{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        return createResponse(bookRepository.deleteBook(id));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", path = "{id}")
    public ResponseEntity<Book> createBook(@Validated @RequestBody Book book) {
        return createResponse(bookRepository.addBook(book));
    }

    private ResponseEntity<Book> createResponse(Book book) {
        return new ResponseEntity(book, (book != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}

