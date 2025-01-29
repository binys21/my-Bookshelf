package bookshelf.bookshelf.dto;

import lombok.Data;

@Data
public class ReviewFileDto {
    private int idx;
    private int bookId;
    private String originalFileName;
    private String storedFilePath;
    private String fileSize;
}
