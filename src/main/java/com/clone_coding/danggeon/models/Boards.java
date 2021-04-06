package com.clone_coding.danggeon.models;

import com.clone_coding.danggeon.dto.BoardsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Boards {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;

    @Transient
    private Object imgFile;
//    public Boards(BoardsRequestDto boardsRequestDto){
//        this.title = boardsRequestDto.getTitle();
//        this.contents = boardsRequestDto.getContents();
//    }
    public Boards(BoardsRequestDto boardsRequestDto){
        this.title = boardsRequestDto.getTitle();
        this.contents = boardsRequestDto.getContents();
        this.imgFile = boardsRequestDto.getImgFile();
    }

//    public Boards(BoardsRequestDto boardsRequestDto, User user){
//        this.title = boardsRequestDto.getTitle();
//        this.contents = boardsRequestDto.getContents();
//        this.user = user;
//    }

}
