package com.walsupring.board.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.NoSuchElementException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void 아이디로_보드_검색() {
        Board board = boardRepository.save(new Board("title","content", "originalFileName","savedFileName"));
        Board foundBoard = boardRepository.findById(board.getId()).orElseThrow();

        Assertions.assertThat(foundBoard).isSameAs(board);
    }

    @Test
    void 아이디로_유저_검색_실패__검색_결과_없음() {
        Board board = boardRepository.save(new Board("title","content", "originalFileName","savedFileName"));

        Assertions.assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> boardRepository.findById(-1L).orElseThrow());

//        Assertions.assertThatIllegalArgumentException(NoSuchElementException.class)
    }

}