package com.koi151.QTDL.controllerAdvice;

import com.koi151.QTDL.customExceptions.*;
import com.koi151.QTDL.model.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ControllerAdvisor {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getBindingResult().getFieldErrors()
//            .stream()
//            .map(FieldError::getDefaultMessage)
//            .collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(ErrorResponse.builder()
//                .error("Yêu cầu không hợp lệ")
//                .details(errors)
//                .build());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorDetails = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.builder()
                .error("Yêu cầu không hợp lệ, kiểm tra lại dữ liệu đầu vào")
                .details(errorDetails)
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> constraintViolations = ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());

        return ResponseEntity.badRequest()
            .body(ErrorResponse.builder()
                .error("Request khong hop le")
                .details(constraintViolations)
                .build());
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(PasswordMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.builder()
                .error(ex.getMessage())
                .details(Collections.singletonList("Mat khau xac nhan khong hop le, hay nhap lai"))
                .build());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleRequestValidateException(InvalidRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.builder()
                .error(ex.getMessage())
                .details(Collections.singletonList("Yêu cầu không hợp lệ"))
                .build());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse.builder()
                .error("Thuc the da ton tai, du lieu khong the trung lap")
                .details(Collections.singletonList(ex.getMessage()))
                .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse.builder()
                .error("Yêu cầu không hợp lệ, kiểm tra lại Request Body")
                .details(Collections.singletonList(ex.getMessage()))
                .build());
    }
    @ExceptionHandler(EntityNotExistedException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotExistedException(EntityNotExistedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse.builder()
                .error("Thực thể không tồn tại")
                .details(Collections.singletonList(ex.getMessage()))
                .build());
    }

    @ExceptionHandler(InvalidEnumValueException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnumValueException(InvalidEnumValueException ex) {

        List<String> details = new ArrayList<>();
        details.add("Kiểm tra lại giá trị enum");

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getMessage());
        errorResponse.setDetails(details);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EntityDeletionFailed.class)
    public ResponseEntity<ErrorResponse> handleEntityDeletionFailed(EntityDeletionFailed ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(ErrorResponse.builder()
                .error("Không thể xóa thực thể")
                .details(Collections.singletonList(ex.getMessage()))
                .build());
    }
}