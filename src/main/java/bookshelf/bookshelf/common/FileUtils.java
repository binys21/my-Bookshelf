package bookshelf.bookshelf.common;

import bookshelf.bookshelf.dto.ReviewFileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;  // IOException import 추가
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {
    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    public List<ReviewFileDto> parseFileInfo(int bookId, MultipartHttpServletRequest request) {
        if (ObjectUtils.isEmpty(request)) {
            return null;
        }
        List<ReviewFileDto> fileInfoList = new ArrayList<>();

        // 파일 저장할 디렉터리 설정
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime now = ZonedDateTime.now();
        String storedDir = uploadDir + "reviews/" + now.format(dft);
        File fileDir = new File(storedDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();  // 디렉터리 생성
        }

        // 요청에서 업로드된 파일 처리
        Iterator<String> fileTagNames = request.getFileNames();
        while (fileTagNames.hasNext()) {
            String fileTagName = fileTagNames.next();
            List<MultipartFile> files = request.getFiles(fileTagName);


            if (files.isEmpty()) {
                System.out.println("파일이 업로드되지 않았습니다.");
            }


            for (MultipartFile file : files) {
                String storedFileName = Long.toString(System.nanoTime());
                String storedFilePath = storedDir + "/" + storedFileName;

                // DTO에 파일 정보 저장
                ReviewFileDto dto = new ReviewFileDto();
                dto.setBookId(bookId);
                dto.setFileSize(Long.toString(file.getSize()));
                dto.setOriginalFileName(file.getOriginalFilename());
                dto.setStoredFilePath(storedFilePath);
                fileInfoList.add(dto);

                fileDir = new File(storedFilePath);

                try {
                    file.transferTo(fileDir);  // 파일을 실제 디스크에 저장
                } catch (IOException | IllegalStateException e) {
                    e.printStackTrace();
                    throw new RuntimeException("파일 저장에 실패했습니다.", e);


                }
            }
        }

        return fileInfoList;
    }
}
