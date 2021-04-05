package com.clone_coding.danggeon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    //api 통신 시간
    private LocalDateTime transactionTime;

    //api 응답 코드
    private String resultCode;

    //api 부가 설명
    private String[] description;


    private T data;


    // OK
    public static <T> Header<T> OK() {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .build();
    }

    //ERROR
    public static <T> Header<T> ERROR(String[] description) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}

