package com.clone_coding.danggeon.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CreateError {
    public ResponseEntity error(String errorMessage) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomMessageResponse errors = new CustomMessageResponse(errorMessage,status.value());
        return ResponseEntity
                .status(status)
                .body(errors);
    }

    public ResponseEntity ok(String okMessage) {
        HttpStatus status = HttpStatus.OK;
        CustomMessageResponse errors = new CustomMessageResponse(okMessage,status.value());
        return ResponseEntity
                .status(status)
                .body(errors);
    }

}
