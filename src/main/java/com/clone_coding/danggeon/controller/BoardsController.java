package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.BoardsRequestDto;
import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import com.clone_coding.danggeon.service.BoardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://clonefront.me.s3-website.ap-northeast-2.amazonaws.com/
//@CrossOrigin(origins = "http://localhost:3000")
//http://clonefront.me.s3-website.ap-northeast-2.amazonaws.com/
//@CrossOrigin(origins = "http://clonefront.me.s3-website.ap-northeast-2.amazonaws.com:3000")
@CrossOrigin(origins = "*")
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
        System.out.println("getBoards");
        return boardsRepository.findAll();
    }

    @GetMapping("/api/boards/{id}")
    public Boards getBoard(@PathVariable Long id){
        System.out.println("getBoard");
        return boardsService.getBoard(id);
    }

    @GetMapping("/api/boards/search/{text}")
    public List<Boards> getBoardSearch(@PathVariable String text){
        System.out.println("getBoardSearch");
        return boardsService.getBoardsSearch("%" + text + "%");
    }

    @PostMapping("/api/boards")
    public Boards createBoards(@RequestBody BoardsRequestDto boardsRequestDto){
        System.out.println("createBoards");
        Boards boards = new Boards(boardsRequestDto);
        return boardsRepository.save(boards);
    }
}