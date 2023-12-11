package com.walsupring.board.service;

import com.walsupring.board.domain.Board;
import com.walsupring.board.domain.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;


        @Test
    void 보드생성_성공() {
        Board board = boardService.join("title", "content", "originalFileName", "savedFileName");

        Assertions.assertThat(board.getId()).isNotNull();
    }

    @Test
    void 보드조회_성공() {
        Board board = boardRepository.save(new Board("title", "content", "originalFileName", "savedFileName"));

        Board foundBoard = boardService.getBoard(board.getId());

        Assertions.assertThat(foundBoard.getId()).isEqualTo(board.getId());
    }

    @Test
    void 보드조회_실패__존재하지_않는_ID() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> boardService.getBoard(-1L));
    }

    @Test
    void 보드변경_성공_() {
        Board board = boardRepository.save(new Board("title", "content", "originalFileName", "savedFileName"));

        Board changeBoard = boardService.changeBoard(board.getId(),"title1","content1", "originalFileName1,", "savedFileName1");

        Assertions.assertThat(changeBoard.getId()).isEqualTo(board.getId());
    }




}