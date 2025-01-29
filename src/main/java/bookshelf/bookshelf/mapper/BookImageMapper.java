package bookshelf.bookshelf.mapper;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.dto.BookImgDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookImageMapper {
    void insertBookImage(BookImgDto bookImgDto);
    void updateBookImage(BookImgDto bookImgDto);

    BookImgDto getBookImageById(int imageId);
}
