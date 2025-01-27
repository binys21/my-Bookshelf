package bookshelf.bookshelf.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDto {
    private int bookId;
    private String title;
    private String author;
    private String publisher; // 출판사
    private String publishedDate; // 출판일 (String으로 변경)
    private String isbn; // ISBN 번호
    private String description;
    private String createdDt; // 생성 시각 (String으로 변경)
    private String updatedDt; // 수정 시각

    private String imageUrl; // 이미지 주소
}
