package com.clone_coding.danggeon.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String errorMessage;

    public CustomErrorResponse(String errorMessage, int status)
    {
        this.timestamp = LocalDateTime.now();
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
