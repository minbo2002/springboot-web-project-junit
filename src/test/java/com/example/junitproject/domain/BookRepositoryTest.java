package com.example.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    /*
    1.책 등록  2.책 목록  3.책 한건조회  4.책 수정  5.책 삭제
     */

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void 책등록_test() {
        System.out.println("책등록_test 실행");
    }


}