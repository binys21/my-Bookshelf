package bookshelf.bookshelf.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class BookSearchService {
    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    private final String NAVER_BOOK_API_URL="https://openapi.naver.com/v1/search/book.json?query=";

    public String searchBooks(String keyword) {
        RestTemplate restTemplate =new RestTemplate();
        HttpHeaders headers =new HttpHeaders();
        headers.set("X-Naver-Client-Id",clientId);
        headers.set("X-Naver-Client-Secret",clientSecret);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity=new HttpEntity<>(headers);
        ResponseEntity<String> response=restTemplate.exchange(
                NAVER_BOOK_API_URL+keyword,
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();

    }
}
