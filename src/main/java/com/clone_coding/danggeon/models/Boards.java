package com.clone_coding.danggeon.models;

import com.clone_coding.danggeon.dto.BoardsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String price;

    @Column
    private String imgFilePath;

    public Boards(BoardsRequestDto boardsRequestDto){
        this.title = boardsRequestDto.getTitle();
        this.contents = boardsRequestDto.getContents();
        this.imgFilePath = boardsRequestDto.getImgFilePath();
        this.price = boardsRequestDto.getPrice();
        this.username = boardsRequestDto.getUsername();
    }


//    public Boards(BoardsRequestDto boardsRequestDto){
//        this.title = boardsRequestDto.getTitle();
//        this.contents = boardsRequestDto.getContents();
//    }
//    public Boards(BoardsRequestDto boardsRequestDto, User user){
//        this.title = boardsRequestDto.getTitle();
//        this.contents = boardsRequestDto.getContents();
//        this.imgFilePath = boardsRequestDto.getImgFilePath();
//        this.price = boardsRequestDto.getPrice();
//        this.user = user;
//    }

//    public Boards(BoardsRequestDto boardsRequestDto, User user){
//        this.title = boardsRequestDto.getTitle();
//        this.contents = boardsRequestDto.getContents();
//        this.user = user;
//    }

}
