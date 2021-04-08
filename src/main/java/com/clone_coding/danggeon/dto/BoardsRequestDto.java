package com.clone_coding.danggeon.dto;

import com.clone_coding.danggeon.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BoardsRequestDto {
    private String title;
    private String contents;
    private String imgFilePath;
    private String username;
    private String price;
}
