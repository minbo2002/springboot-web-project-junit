package com.example.junitproject.dto.response;

import com.example.junitproject.domain.Book;
import com.example.junitproject.dto.request.BookReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRespDto {

    private Long id;
    private String title;
    private String author;

    public BookRespDto toDto(Book book) {

        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();

        return this;
    }

    /*
    public static BookRespDto toDto(Book book) {
    BookRespDto bookRespDto = new BookRespDto();
    bookRespDto.id = book.getId();
    bookRespDto.title = book.getTitle();
    bookRespDto.author = book.getAuthor();

    return bookRespDto;
    }*/
}
