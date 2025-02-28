package bookshelf.bookshelf.service;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.dto.BookImgDto;
import bookshelf.bookshelf.dto.ReviewFileDto;

import java.util.List;

public interface BookService {
    List<BookDto> selectBookList();
    void insertBook(BookDto bookDto, BookImgDto bookImgDto);
    BookDto selectBookDetail(int bookId);

    void deleteBook(int bookId);
    void updateBook(BookDto bookDto,BookImgDto bookImgDto);


    ReviewFileDto getBookFile(int idx);

    boolean deleteBookFile(int idx, int bookId);
}
