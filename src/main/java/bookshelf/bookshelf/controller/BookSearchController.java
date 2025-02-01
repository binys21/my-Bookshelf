package bookshelf.bookshelf.controller;

import bookshelf.bookshelf.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookSearchController {
    private final BookSearchService bookSearchService;
    @Autowired
    public BookSearchController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String query) {
        return bookSearchService.searchBooks(query);
    }

}
