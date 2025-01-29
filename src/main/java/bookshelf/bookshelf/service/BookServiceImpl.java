package bookshelf.bookshelf.service;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.dto.BookImgDto;
import bookshelf.bookshelf.mapper.BookImageMapper;
import bookshelf.bookshelf.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookImageMapper bookImageMapper;

    @Override
    public List<BookDto> selectBookList(){
        return bookMapper.selectBookList();
    }
    @Transactional
    @Override
    public void insertBook(BookDto bookDto, BookImgDto bookImgDto) {
        bookMapper.insertBook(bookDto);
        bookImgDto.setBookId(bookDto.getBookId());
        bookImageMapper.insertBookImage(bookImgDto);
    }
    @Override
    public BookDto selectBookDetail(int bookId){
        return bookMapper.selectBookDetail(bookId);
    }
    @Override
    public void deleteBook(int bookId){
        bookMapper.deleteBook(bookId);
    }
    @Transactional
    @Override
    public void updateBook(BookDto bookDto,BookImgDto bookImgDto) {
        bookMapper.updateBook(bookDto);
        bookImgDto.setBookId(bookDto.getBookId());
        bookImageMapper.updateBookImage(bookImgDto);
    }
}
