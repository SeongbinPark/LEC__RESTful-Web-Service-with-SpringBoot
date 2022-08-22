package com.example.restfulwebservice.exception;


import com.example.restfulwebservice.user.UserNotFoundExcetiion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * 1. 어떤 컨트롤러클래스가 실행된다하더라도 CustomizedResponseEntityExceptionHandler가 실행됨.(@ControllerAdvice에 의해)
 * 2. 이 클래스 안에서 Exception이 발생하면 해당 Exception의 메서드가 실행됨.
 */


@ControllerAdvice// 모든 컨트롤러가 실행 될 때 반드시 @ControllerAdvice을 가진 빈이 실행됨. -> 에러 발생시 바로 ExceptionHandler 실행됨.
//@ControllerAdvice : AOP를 적용해 컨트롤러 단에 적용하기 위해 사용되는 어노테이션
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {// ResponseEntityExceptionHandler를 상속해야함.

    @ExceptionHandler(Exception.class)//이 메서드가 exceptionHandler로 사용될 수 있음(파라미터 : 어떤 예외를 받을건지지)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // 파라미터 : (발생한 에러 객체, 어디서 발생 했는지 request에 대한 정보)
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription((false)));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);//바디, 상태코드 반환.
        //Object라 제네릭타입 생략가능, 모든 에러를 받는(핸들링하는) 메서드이기에 일반적인 500번 에러 지정.
    }

    // handleAllExceptions 는 @ExceptionHandler(Exception.class) 때문에 모든 예외를 받는 클래스 이므로
    // controller에서 UserNotFoundExcetiion 라는 예외만 받는 핸들러를 만들자

    @ExceptionHandler(UserNotFoundExcetiion.class)//이 메서드가 exceptionHandler로 사용될 수 있음
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription((false)));

        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
        //Object라 제네릭타입 생략가능, 일반적인 500번 에러 지정.
    }
}
//어떤 컨트롤러 클래스가 실행 되더라도 이 클래스가 실행 될 것이고 이 클래스에서 예외 발생 시 우리가 선언시킨 handleAllExceptions 메서드가 실행된다.

//이렇게 하면 trace가 X -> 정확히 의도한 에러메시지만 전달가능.
