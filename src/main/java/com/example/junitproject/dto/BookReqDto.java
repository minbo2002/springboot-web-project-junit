package com.example.junitproject.dto;

import com.example.junitproject.domain.Book;
import lombok.Setter;

@Setter  // Controller에서 Setter가 호출되면서 Dto에 값이 채워짐
public class BookReqDto {

    private String title;

    private String author;

    public Book mapToEntity() {

        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }
}
