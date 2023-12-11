package com.walsupring.board.service;

import com.walsupring.board.domain.Board;
import com.walsupring.board.domain.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board join(String title, String content, String originalFileName, String savedFileName) {
        Board board = boardRepository.save(new Board(title, content, originalFileName,savedFileName));
        return board;
    }

    @Override
    public Board getBoard(Long id) {

        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id is not null"));

    }

    @Override
    public Board changeBoard(Long id, String title, String content, String originalFileName, String savedFileName) {
        Board board =  boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id is not null"));
        board.changeBoard(title, content, originalFileName,savedFileName);
        return board;
    }
}
