package com.clone_coding.danggeon.controller;

import com.clone_coding.danggeon.dto.BoardsRequestDto;
import com.clone_coding.danggeon.handler.CustomMessageResponse;
import com.clone_coding.danggeon.models.Boards;
import com.clone_coding.danggeon.repository.BoardsRepository;
import com.clone_coding.danggeon.service.BoardsService;
import com.clone_coding.danggeon.service.S3Service;
import com.clone_coding.danggeon.utils.GetBoards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    S3Service s3Service;

    @Autowired
    BoardsController(BoardsService boardsService, BoardsRepository boardsRepository, S3Service s3Service){
        this.boardsRepository = boardsRepository;
        this.boardsService = boardsService;
        this.s3Service = s3Service;
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
    public Object getBoardSearch(@RequestParam(required = false) String text){
        try{
            List<Boards> boardsList = boardsService.getBoardsSearch(text);
            System.out.println("getBoardSearch");
            return boardsList;
        }
        catch (Exception ignore)
        {
            String errorMessage = "text를 입력해주세요.";
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
                               @RequestParam("contents") String contents,
                               @RequestParam("price") String price)
    {
        try
        {
            System.out.println(req.getRequestURL());

            String username = (String)req.getAttribute("username");

            BoardsRequestDto boardsRequestDto = new BoardsRequestDto();
            boardsRequestDto.setTitle(title);
            boardsRequestDto.setContents(contents);
            boardsRequestDto.setPrice(price);
            boardsRequestDto.setUsername(username);
            System.out.println("username = " + username);

            String IMAGEPATH = "src/main/resources/static/images/boards/";

            if (files == null)
            {
                boardsRequestDto.setImgFilePath("");
                Boards boards = new Boards(boardsRequestDto);
                return boardsRepository.save(boards);
            }
            else{
                MultipartFile multipartFile = files.get(0);
                String fileName = multipartFile.getOriginalFilename();

//                String dateTimeFileName = boardsService.getFullPath(fileName, IMAGEPATH);
//
//                File targetFile = new File(IMAGEPATH, dateTimeFileName);
//
//                InputStream fileStream = multipartFile.getInputStream();
//                FileUtils.copyInputStreamToFile(fileStream, targetFile);
//
//                String pre_Path = "static/images/boards/";

                String url = s3Service.upload(multipartFile);

                System.out.println("url = " + url);
                boardsRequestDto.setImgFilePath(url);
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

    @GetMapping("/api/crawlingBoards")
    public String crawlingBoards(){
        GetBoards getBoards = new GetBoards();
        getBoards.crawlingBoards(this.boardsRepository);

        return "success";
    }
}