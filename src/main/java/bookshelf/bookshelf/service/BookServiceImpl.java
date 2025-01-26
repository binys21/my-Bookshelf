package bookshelf.bookshelf.service;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookDto> selectBookList(){
        return bookMapper.selectBookList();
    }
}
