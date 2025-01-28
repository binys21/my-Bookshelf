package bookshelf.bookshelf.controller;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.dto.BookImgDto;
import bookshelf.bookshelf.service.BookImageService;
import bookshelf.bookshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookImageService bookImageService;
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
    public ModelAndView openBookDetail(@RequestParam(value = "bookId", required = true)
                                           int bookId) throws Exception {
        BookDto bookDto = bookService.selectBookDetail(bookId);
        System.out.println("Received bookId: " + bookId);

        ModelAndView mv =new ModelAndView("book/bookDetail");
        mv.addObject("book",bookDto);
        return mv;
    }
    //수정페이지로 이동
    @GetMapping("/book/update/{bookId}")
    public String openUpdateBookPage(@PathVariable("bookId") int bookId, Model model) throws Exception {
        BookDto bookDto = bookService.selectBookDetail(bookId);
        model.addAttribute("book", bookDto);
        return "book/update"; // 수정 페이지 뷰 파일을 반환
    }
    //수정 요청 처리
    @PostMapping("/book/update")
    public String updateBook(@ModelAttribute BookDto bookDto) throws Exception {
        bookService.updateBook(bookDto);
        return "redirect:/book/openBookList";
    }

    //삭제 요청 처리
    @PostMapping("/book/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId) throws Exception {
        bookService.deleteBook(bookId);
        return "redirect:/book/openBookList";
    }

    //파일 업로드
    @PostMapping("/book/uloadImage/{bookId}")
    public String uploadImage(@PathVariable("bookId")int bookId,
                              @RequestParam("image") MultipartFile imageFile)  throws Exception {
        //업로드된 파일 저장할 경로 설정
        String uploadDirectory="/Users/binys/Desktop/upload";
        //파일 명으로 파일 경로 설정
        String fileName=imageFile.getOriginalFilename();
        String filePath=uploadDirectory+"/"+fileName;

        File destinationFile=new File(filePath);
        imageFile.transferTo(destinationFile);

        BookImgDto bookImgDto = new BookImgDto();
        bookImgDto.setImageId(bookId);
        bookImgDto.setImageUrl(filePath);  // 이미지 URL 저장

        bookImageService.saveBookImage(bookImgDto);
        return "redirect:/book/openBookDetail/" + bookId;


    }



}
