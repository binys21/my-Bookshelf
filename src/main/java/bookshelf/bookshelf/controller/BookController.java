package bookshelf.bookshelf.controller;

import bookshelf.bookshelf.common.FileUtils;
import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.dto.BookImgDto;
import bookshelf.bookshelf.dto.ReviewFileDto;
import bookshelf.bookshelf.mapper.BookMapper;
import bookshelf.bookshelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private BookMapper bookMapper;

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
    public String registerBook(@ModelAttribute BookDto bookDto,@ModelAttribute BookImgDto bookImgDto) throws Exception {
        bookService.insertBook(bookDto,bookImgDto);
        return "redirect:/book/openBookList"; //도서 목록 페이지로 리다리엑트
    }

    //상세 조회 요청
    @GetMapping("/book/openBookDetail")
    public ModelAndView openBookDetail(@RequestParam(value = "bookId", required = true)
                                           int bookId) throws Exception {
        BookDto bookDto = bookService.selectBookDetail(bookId);
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
    public String updateBook(@ModelAttribute BookDto bookDto,@ModelAttribute BookImgDto bookImgDto) throws Exception {
        bookService.updateBook(bookDto,bookImgDto);
        return "redirect:/book/openBookList";
    }

    //삭제 요청 처리
    @PostMapping("/book/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") int bookId) throws Exception {
        bookService.deleteBook(bookId);
        return "redirect:/book/openBookList";
    }

    //파일 업로드
    @PostMapping("/book/uploadReview")
    public String uploadReview(@RequestParam("bookId") int bookId,
                               MultipartHttpServletRequest request,
                               Model model)  {
        try{
            List<ReviewFileDto> fileInfoList = fileUtils.parseFileInfo(bookId,request);

            if(!CollectionUtils.isEmpty(fileInfoList)){
                bookMapper.insertReviewFileList(fileInfoList);
            }
            return "redirect:/book/openBookDetail?bookId=" + bookId;

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error","파일 업로드 실패");
            return "redirect:/book/openBookDetail?bookId=" + bookId;
        }

    }





}
