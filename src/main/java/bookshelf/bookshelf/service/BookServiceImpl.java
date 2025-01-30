package bookshelf.bookshelf.service;

import bookshelf.bookshelf.dto.BookDto;
import bookshelf.bookshelf.dto.BookImgDto;
import bookshelf.bookshelf.dto.ReviewFileDto;
import bookshelf.bookshelf.mapper.BookImageMapper;
import bookshelf.bookshelf.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        BookDto bookDto=bookMapper.selectBookDetail(bookId);
        List<ReviewFileDto> fileInfoList= bookMapper.selectBookFileList(bookId);

        bookDto.setFileInfoList(fileInfoList);

        return bookDto;
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

    @Override
    public ReviewFileDto getBookFile(int idx){
        return bookMapper.selectBookFile(idx);
    }

    @Override
    public boolean deleteBookFile(int idx, int bookId) {
        try {
            // 파일 정보를 먼저 조회하여 삭제할 파일 경로 확인
            ReviewFileDto file = bookMapper.selectBookFile(idx);
            if (file != null && file.getBookId() == bookId) {
                // 파일 삭제
                Path path = Paths.get(file.getStoredFilePath());
                Files.deleteIfExists(path);  // 파일 삭제

                // DB에서 파일 정보 삭제
                bookMapper.deleteBookFile(idx);
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
