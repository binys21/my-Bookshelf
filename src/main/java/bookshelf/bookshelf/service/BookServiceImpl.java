package bookshelf.bookshelf.service;

import bookshelf.bookshelf.dto.BookDto;
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

    @Override
    public List<BookDto> selectBookList(){
        return bookMapper.selectBookList();
    }
    @Override
    public void insertBook(BookDto bookDto) {
        bookMapper.insertBook(bookDto);
    }
    @Override
    public BookDto selectBookDetail(int bookId){
        System.out.println("Fetching book details for bookId: " + bookId);
        return bookMapper.selectBookDetail(bookId);
    }
}
