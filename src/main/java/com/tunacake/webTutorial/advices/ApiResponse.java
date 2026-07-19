package com.tunacake.webTutorial.advices;

// this provies a format for the response, no matter whether the response is success or error

// if it a success response, the data field will contain the data,
// if it is an error response, the error field will contain the error

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<t> {


    private LocalDateTime timestamp;
    private t data;
    private ApiResponseErrorFormat error;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(t data) {
        this();
//        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public ApiResponse(ApiResponseErrorFormat error) {
        this();
//        this.timestamp = LocalDateTime.now();
        this.error = error;
    }
}
