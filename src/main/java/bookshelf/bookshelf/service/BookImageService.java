package bookshelf.bookshelf.service;

import bookshelf.bookshelf.dto.BookImgDto;

public interface BookImageService {
    void saveBookImage(BookImgDto bookImgDto);
    void updateBookImage(BookImgDto bookImgDto);
    BookImgDto getBookImageById(int imageId);

}
