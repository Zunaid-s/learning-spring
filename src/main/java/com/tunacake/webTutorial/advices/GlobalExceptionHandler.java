package com.tunacake.webTutorial.advices;

import com.tunacake.webTutorial.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler // This will handle the ResourceNotFoundException
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception) {

        ApiResponseErrorFormat apiResponseErrorFormat = ApiResponseErrorFormat.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class) // this will handle all the exceptions
    public ResponseEntity<ApiResponse<?>> handleAllExceptions(Exception e) {
        ApiResponseErrorFormat apiResponseErrorFormat = ApiResponseErrorFormat.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiResponseErrorFormat), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // this will handle the input validation errors
    public ResponseEntity<ApiResponse<?>> handleInputValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiResponseErrorFormat apiResponseErrorFormat = ApiResponseErrorFormat.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validation failed")
                .subErrors(errors)
                .build();
        return new ResponseEntity<>(new ApiResponse<>(apiResponseErrorFormat), HttpStatus.BAD_REQUEST);
    }



}
