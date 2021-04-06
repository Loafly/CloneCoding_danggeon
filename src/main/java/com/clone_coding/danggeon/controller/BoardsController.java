package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.handler.CustomMessageResponse;
import com.clone_coding.danggeon.dto.BoardsRequestDto;
import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import com.clone_coding.danggeon.service.BoardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
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

//    Get요청 시 /api/boards/search?text=내용
    @GetMapping("/api/boards/search")
    public Object getBoardSearch(@RequestParam String text){
        try{
            List<Boards> boardsList = boardsService.getBoardsSearch(text);
            System.out.println("getBoardSearch");
            return boardsList;
        }
        catch (Exception ignore)
        {
            String errorMessage = "test";
            HttpStatus status = HttpStatus.BAD_REQUEST;
            CustomMessageResponse errors = new CustomMessageResponse(errorMessage,status.value());

            return ResponseEntity
                    .status(status)
                    .body(errors);
        }
    }

    @PostMapping("/api/boards")
    public Boards createBoards(HttpServletRequest req,
                               @RequestParam(value = "files", required = false) List<MultipartFile> files,
                               @RequestParam("title") String title,
                               @RequestParam("contents") String contents)
    {
        try
        {
            System.out.println(req.getRequestURL());
            BoardsRequestDto boardsRequestDto = new BoardsRequestDto();
            boardsRequestDto.setTitle(title);
            boardsRequestDto.setContents(contents);

            if (files == null)
            {
                boardsRequestDto.setImgFilePath("");
                Boards boards = new Boards(boardsRequestDto);
                return boardsRepository.save(boards);
            }
            else{
                MultipartFile multipartFile = files.get(0);
                String fileName = multipartFile.getOriginalFilename();
                String rootPath = this.getClass().getResource("/").getPath();
                String fullPath = boardsService.getFullPath(rootPath, fileName);

//                multipartFile.transferTo(new File(fullPath + fileName));

                for (int i = 0; i < files.size(); i++)
                {
                    System.out.println(files.get(i).getOriginalFilename());
                }

                boardsRequestDto.setImgFilePath(fileName);
                Boards boards = new Boards(boardsRequestDto);
                System.out.println(boardsRequestDto);
                return boardsRepository.save(boards);
            }

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}