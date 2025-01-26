package bookshelf.bookshelf.dto;

import lombok.Data;

@Data
public class BookDto {
    private int bookId;
    private String title;
    private String author;
    private String publisher; //출판사
    private String publishedDate; //출판일
    private String isbn; //ISBN번호
    private String description;
    private String createdDt; //생성시각
    private String updatedDt; //수정시각
}
