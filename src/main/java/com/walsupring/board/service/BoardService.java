package com.walsupring.board.service;

import com.walsupring.board.domain.Board;

public interface BoardService {
    Board join(String title, String content, String originalFileName, String savedFileName);
    Board getBoard(Long id);
    Board changeBoard(Long id, String title, String content, String originalFileName, String savedFileName);
}
