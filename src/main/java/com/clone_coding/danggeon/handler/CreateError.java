package com.clone_coding.danggeon.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateError {
    public ResponseEntity error(String errorMessage) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomErrorResponse errors = new CustomErrorResponse(errorMessage,status.value());
        return ResponseEntity
                .status(status)
                .body(errors);
    }

}
