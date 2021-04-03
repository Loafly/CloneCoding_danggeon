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

    public Boards(BoardsRequestDto boardsRequestDto){
        this.title = boardsRequestDto.getTitle();
        this.contents = boardsRequestDto.getContents();
    }

}
