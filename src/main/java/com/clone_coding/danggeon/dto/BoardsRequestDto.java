package com.clone_coding.danggeon.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BoardsRequestDto {
    private String title;
    private String contents;
}
