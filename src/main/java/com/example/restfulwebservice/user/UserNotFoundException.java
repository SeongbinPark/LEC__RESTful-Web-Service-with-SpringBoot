package com.example.restfulwebservice.user;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 2XX -> OK
// 4XX -> Client Error
// 5XX -> Server Error
// 사용자 없는 예외 -> 404 Not Found로 주자.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
