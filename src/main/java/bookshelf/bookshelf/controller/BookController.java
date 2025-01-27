package bookshelf.bookshelf.controller;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book/openBookList")
    public ModelAndView openBookList() throws Exception {
        ModelAndView mv =new ModelAndView("book/bookList");

        List<BookDto> list = bookService.selectBookList();
        mv.addObject("list",list);

        return mv;
    }
}
