package com.walsupring.board.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;



class BoardTest {
    @Test
    void 보드_생성_성공() {
      Board board = new Board("title", "content", "originalFileName", "savedFileName");

        Assertions.assertThat(board.getId()).isNull();
        Assertions.assertThat(board.getTitle()).isEqualTo("title");
        Assertions.assertThat(board.getContent()).isEqualTo("content");
        Assertions.assertThat(board.getOriginalFileName()).isEqualTo("originalFileName");
        Assertions.assertThat(board.getSavedFileName()).isEqualTo("savedFileName");
        Assertions.assertThat(board.getCreatedAt()).isNotNull();
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 보드_생성_실패__title이_Null_혹은_빈값(String title){
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() ->new Board(title, "content", "originalFileName", "savedFileName"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 보드_생성_실패__contents가_Null_혹은_빈값(String content){
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() ->new Board("title", content, "originalFileName", "savedFileName"));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void 보드_생성_실패__originalFileName이_Null_혹은_빈값(String originalFileName){
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() ->new Board("title", "content", originalFileName, "savedFileName"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 보드_생성_실패__savedFileName이_Null_혹은_빈값(String savedFileName){
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() ->new Board("title", "content", "originalFileName", savedFileName));
    }


}