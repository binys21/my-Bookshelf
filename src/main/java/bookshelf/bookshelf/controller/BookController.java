package bookshelf.bookshelf.controller;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    //도서 목록 조회
    @GetMapping("/book/openBookList")
    public ModelAndView openBookList() throws Exception {
        ModelAndView mv =new ModelAndView("book/bookList");

        List<BookDto> list = bookService.selectBookList();
        mv.addObject("list",list);

        return mv;
    }
    //도서 등록 페이지
    @GetMapping("/book/register")
    public String showRegisterPage() throws Exception {
        return "book/register";
    }
    //도서 등록 처리
    @PostMapping("/book/register")
    public String registerBook(@ModelAttribute BookDto bookDto) throws Exception {
        bookService.insertBook(bookDto);
        return "redirect:/book/openBookList"; //도서 목록 페이지로 리다리엑트
    }

    //상세 조회 요청
    @GetMapping("/book/openBookDetail")
    public ModelAndView openBookDetail(@RequestParam("bookId") int bookId) throws Exception {
        System.out.println("Received bookId: " + bookId);
        BookDto bookDto = bookService.selectBookDetail(bookId);
        if (bookDto == null) {
            System.out.println("bookDto is null for bookId: " + bookId);
        }
        ModelAndView mv =new ModelAndView("book/bookDetail");
        mv.addObject("book",bookDto);
        return mv;
    }


}
