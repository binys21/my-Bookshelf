package bookshelf.bookshelf.mapper;

import bookshelf.bookshelf.dto.BookDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<BookDto> selectBookList();
}
