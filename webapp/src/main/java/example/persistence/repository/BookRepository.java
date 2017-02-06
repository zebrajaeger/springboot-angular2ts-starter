package example.persistence.repository;

import example.persistence.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookRepository {
    private Map<Long, Book> books = new TreeMap<>();
    private final AtomicLong counter = new AtomicLong();

    public BookRepository() {
        addBook("Markus Heitz", "Die Zwerge");
        addBook("Nassim Nicholas Taleb", "Der Schwarze Schwan");
        addBook("Stefan Merath", "Der Weg zum erfolgreichen Unternehmer");
        addBook("Stephen R. Covey und Angela Roethe", "Die 7 Wege zur Effektivität");
        addBook("Jens-Uwe Meyer", "Das Edison-Prinzip");
        addBook("Joe Abercrombie ", "The first Law");
        addBook("Daniel Kahneman", "Schnelles Denken Langsames Denken");
        addBook("Napoleon Hill", "Denke nach und werde reich");
    }

    public Book addBook(String author, String title) {
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        return addBook(book);
    }

    public Book addBook(Book book) {
        book.setId(counter.incrementAndGet());
        books.put(book.getId(), book);
        return book;
    }

    public List<Book> getBooks() {
        return new ArrayList(books.values());
    }

    public Book getBook(Long id) {
        return books.get(id);
    }

    public Book deleteBook(Long id) {
        return books.remove(id);
    }
}
