package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.BoardsRequestDto;
import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import com.clone_coding.danggeon.service.BoardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BoardsController {

    BoardsRepository boardsRepository;
    BoardsService boardsService;

    @Autowired
    BoardsController(BoardsService boardsService, BoardsRepository boardsRepository){
        this.boardsRepository = boardsRepository;
        this.boardsService = boardsService;
    }

    @GetMapping("/api/boards")
    public List<Boards> getBoards(){
        return boardsRepository.findAll();
    }

    @GetMapping("/api/boards/{id}")
    public Boards getBoard(@PathVariable Long id){
        return boardsService.getBoard(id);
    }

    @PostMapping("/api/boards")
    public Boards createBoards(@RequestBody BoardsRequestDto boardsRequestDto){
        Boards boards = new Boards(boardsRequestDto);
        return boardsRepository.save(boards);
    }
}
