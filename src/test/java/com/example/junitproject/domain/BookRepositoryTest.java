package com.example.junitproject.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class BookRepositoryTest {

    /*
    1.책 등록  2.책 목록  3.책 한건조회  4.책 수정  5.책 삭제
     */

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {

        String title = "junit4";
        String author = "kmb4";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        bookRepository.save(book);

    }  // transaction 종료가 안됨됨

   @Test
    public void 책등록_test() {

        // given (데이터 준비)
        String title = "junit5";
        String author = "kmb5";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        // when (테스트 실행)
        Book bookPersist = bookRepository.save(book);

        // then (검증)
        assertThat(title).isEqualTo(bookPersist.getTitle());
        assertThat(author).isEqualTo(bookPersist.getAuthor());

    }  // transaction 종료 (1차캐시의 데이터가 초기화됨)

    @Test
    public void 책목록_test() {
        // given
        String title = "junit4";
        String author = "kmb4";

        // when
        List<Book> booksPersist = bookRepository.findAll();

        System.out.println("booksPersist size : " + booksPersist.size());

        // then
        assertThat(title).isEqualTo(booksPersist.get(0).getTitle());
        assertThat(author).isEqualTo(booksPersist.get(0).getAuthor());

    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책한권보기_test() {
        // given
        String title = "junit4";
        String author = "kmb4";

        // when
        Book bookPersist = bookRepository.findById(1L).get();

        // then
        assertThat(title).isEqualTo(bookPersist.getTitle());
        assertThat(author).isEqualTo(bookPersist.getAuthor());
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책삭제_test() {
        // given
        Long id = 1L;

        // when
        bookRepository.deleteById(id);

        // then
        Optional<Book> bookPersist = bookRepository.findById(id);

        assertThat(bookPersist).isEmpty();
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책수정_test() {
        // given
        Long id = 1L;
        String title = "junit5";
        String author = "kmb5";

        Book newBook = new Book(id, title, author);

        // when
        Book mergeBook = bookRepository.save(newBook);

        /*
        bookRepository.findAll().stream().forEach(book -> {
            System.out.println(book.getId());
            System.out.println(book.getTitle());
            System.out.println(book.getAuthor());
            System.out.println("==========================");
        });
        */

        // then
        assertThat(mergeBook.getId()).isEqualTo(id);
        assertThat(mergeBook.getTitle()).isEqualTo(title);
        assertThat(mergeBook.getAuthor()).isEqualTo(author);
    }

}