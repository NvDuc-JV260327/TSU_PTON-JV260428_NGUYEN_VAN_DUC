package com.example.tsu_ptonjv250101_nguyen_van_duc.exception;

import com.example.tsu_ptonjv250101_nguyen_van_duc.model.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandleException {
    // lỗi valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ApiResponse.<Map<String, String>>builder()
                .status(400)
                .message("Dữ liệu nhập vào không hợp lệ")
                .data(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // lỗi trùng tên sp
    @ExceptionHandler(DuplicateResourceException.class)
    public ApiResponse<Object> handleDuplicateResourceException(DuplicateResourceException ex) {
        return ApiResponse.<Object>builder()
                .status(400)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // lỗi id không tồn tại
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ApiResponse.<Object>builder()
                .status(404)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // lỗi thay đổi status
    @ExceptionHandler(StatusChangeErrorException.class)
    public ApiResponse<Object> handleStatusChangeErrorException(StatusChangeErrorException ex) {
        return ApiResponse.<Object>builder()
                .status(400)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
