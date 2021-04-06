package com.clone_coding.danggeon.service;

import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Boards> getBoardsSearch(String text){
        if (text.isEmpty()){
            throw new IllegalArgumentException("텍스트를 입력해 주세요.");
        }

        text = "%" + text + "%";
        return boardsRepository.findByTitleLikeOrContentsLike(text,text);
    }
}
