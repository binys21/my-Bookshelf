
# 📚 도서 데이터 활용 실습 - 독서기록장📝 
<p align=center><img width="694" alt="Image" src="https://github.com/user-attachments/assets/079791b3-c181-4644-a899-94ea6a001236" /></p>


 ## 📌 도서 목록 조회 
 등록된 도서 목록을 조회할 수 있습니다. 각 도서는 제목, 저자, 출판일, 등록일 정보와 함께 표시됩니다.
<img width="1240" alt="Image" src="https://github.com/user-attachments/assets/aa4dfa15-5ce7-4b29-90b4-44146e570734" /> 

<br/>

## 📌 도서 상세 조회 

<img width="1225" alt="Image" src="https://github.com/user-attachments/assets/82b4c616-df51-427b-91aa-1434fda2bf7b" /> 

- **도서 삭제 🗑️**
- **서평 파일 업로드 및 삭제 📝**
- **정보 수정 ✍️**


 <br/>

 ## 📌 파일 업로드 및 삭제 

<p align=center>
<img width="762" alt="Image" src="https://github.com/user-attachments/assets/a3fcb9b5-85c0-478d-8ee2-09ad8688fc24" />
</p>

- **파일 선택 후 업로드**
- **첨부파일 목록 출력**
- **❌ 버튼으로 파일 삭제**
 

  <br/>

## 📌 도서 검색 및 등록 
<img width="1235" alt="Image" src="https://github.com/user-attachments/assets/ed0fb1cc-5b53-4f5e-9955-58ed5d725565" />

- **도서 검색🔍** 버튼을 누르면 검색 모달 창이 열립니다.

<p align=center>
<img width="651" alt="Image" src="https://github.com/user-attachments/assets/c150ec96-2194-44ce-8b8b-354b4e7c9485" />
</p>

- **책 제목을 입력한 후 검색**

<p align=center>
<img width="672" alt="Image" src="https://github.com/user-attachments/assets/a2c6e2b6-0778-4525-b1b0-b0454cab281f" />
</p>
  
- **검색 결과 리스트**

<img width="932" alt="Image" src="https://github.com/user-attachments/assets/014ad893-1f32-4e30-bf19-fcb77462ddf7" />

- **원하는 도서를 선택하면 정보가 입력됩니다 &nbsp;  ➡️ &nbsp; 등록하기! 🤓 ✅**

 <br/>


## 📌 도서 정보 수정 
상세 조회 페이지에서 수정 버튼을 눌러 내용 수정 가능 

<img width="1026" alt="Image" src="https://github.com/user-attachments/assets/192d71e1-bfba-4527-9781-b92c62f2b3de" />

<br/>
<br/>

# MySQL 데이터베이스 스키마

### Books Table
```sql
CREATE TABLE Books (
    book_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 고유 식별자
    title VARCHAR(255) NOT NULL,                -- 도서 제목
    author VARCHAR(255) NOT NULL,               -- 저자
    publisher VARCHAR(255),                     -- 출판사
    published_date DATE,                        -- 출판일
    isbn VARCHAR(20) UNIQUE,                    -- ISBN 번호
    description TEXT,                           -- 도서 설명
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 생성 시각
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 시각
);
```

### BookImages Table

```sql
CREATE TABLE BookImages (
    image_id BIGINT AUTO_INCREMENT PRIMARY KEY,     -- 고유 식별자
    book_id BIGINT NOT NULL,                        -- 도서와의 외래 키 관계
    image_url VARCHAR(500) NOT NULL,                -- 이미지 URL
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
    FOREIGN KEY (book_id) REFERENCES Books(book_id) ON DELETE CASCADE -- 도서 삭제 시 이미지도 삭제
);
```

### BookFile Table

```sql
CREATE TABLE book_file (
    idx INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '일련번호', 
    book_id INT(10) UNSIGNED NOT NULL COMMENT '도서 ID', 
    original_file_name VARCHAR(255) NOT NULL COMMENT '원본 파일 이름', 
    stored_file_path VARCHAR(500) NOT NULL COMMENT '파일 저장 경로',
    file_size INT(15) UNSIGNED NOT NULL COMMENT '파일 크기', 
    created_at DATETIME NOT NULL COMMENT '작성 일시', 
    deleted_yn CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제 여부', 
    PRIMARY KEY (idx)
);
```


<br/>
<br/>

# `application.properties` 설정
```properties
# MySQL 데이터베이스 연결 설정
spring.datasource.hikari.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.hikari.jdbc-url=jdbc:mysql://localhost:3306/springbootdb?useUnicode=true&characterEncoding=utf-8&serverTimeZone=Asia/Seoul
spring.datasource.hikari.username={MySQL사용자명}
spring.datasource.hikari.password={MySQL비밀번호}

#기본 에러 페이지 비활성화
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration

#파일 업로드 설정
spring.servlet.multipart.enabled=true
spring.servlet.multipart.location={파일 업로드할 경로}
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=30MB

#네이버 도서 검색 오픈 API
naver.api.client-id=${NAVER_CLIENT_ID}
naver.api.client-secret=${NAVER_CLIENT_SECRET}
```

