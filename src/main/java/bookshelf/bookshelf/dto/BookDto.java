package bookshelf.bookshelf.dto;

import lombok.Data;

import java.util.List;


@Data
public class BookDto {
    private int bookId;
    private String title;
    private String author;
    private String publisher; // 출판사
    private String publishedDate; // 출판일 (String으로 변경)
    private String isbn; // ISBN 번호
    private String description;
    private String createdAt; // 생성 시각 (String으로 변경)
    private String updatedAt; // 수정 시각
    private String imageUrl; // 이미지 주소
    //첨부 파일 정보 저장할 필드
    private List<ReviewFileDto> fileInfoList;
}
