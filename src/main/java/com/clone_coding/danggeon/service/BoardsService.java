package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardsService {
    private final BoardsRepository boardsRepository;

    @Autowired
    BoardsService(BoardsRepository boardsRepository){
        this.boardsRepository = boardsRepository;
    }

    public Boards getBoard(Long id){
        return boardsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }


}
