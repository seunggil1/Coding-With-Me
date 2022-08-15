package com.ssafy.common.exception.handler;

import com.ssafy.common.model.response.ErrorCode;
import com.ssafy.common.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    protected  ResponseEntity<ErrorResponse> handleException(final Exception e){
//        log.error("handleException : {}", e.getMessage());
//        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
//                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
//    }

    @ExceptionHandler(Exception.class)
    protected  ResponseEntity<Map<String,Object>> handleException(final Exception e){
        log.error("handleException : {}", e.getMessage());
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus().value())
                .body(map);
    }
}
