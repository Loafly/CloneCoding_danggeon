package com.clone_coding.danggeon.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomMessageResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime time;
    private int status;
    private String errorMessage;

    public CustomMessageResponse(String errorMessage, int status)
    {
        this.time = LocalDateTime.now();
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public CustomMessageResponse(int status)
    {
        this.time = LocalDateTime.now();
        this.status = status;
    }


}
