package bookshelf.bookshelf.service;

import bookshelf.bookshelf.dto.BookImgDto;
import bookshelf.bookshelf.mapper.BookImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookImageServiceImpl implements BookImageService {
    @Autowired
    private BookImageMapper bookImageMapper;

    @Override
    public void saveBookImage(BookImgDto bookImgDto) {
        bookImageMapper.insertBookImage(bookImgDto);
    }
    @Override
    public BookImgDto getBookImageById(int imageId) {
        return bookImageMapper.getBookImageById(imageId);
    }

}
