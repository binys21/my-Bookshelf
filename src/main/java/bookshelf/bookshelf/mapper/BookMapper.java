package bookshelf.bookshelf.mapper;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.dto.ReviewFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<BookDto> selectBookList();
    void insertBook(BookDto bookDto);
    BookDto selectBookDetail(int bookId);
    void deleteBook(int bookId);
    void updateBook(BookDto bookDto);
    List<ReviewFileDto> selectBookFileList(int bookId);
    void insertReviewFileList(List<ReviewFileDto> fileInfoList);
}
