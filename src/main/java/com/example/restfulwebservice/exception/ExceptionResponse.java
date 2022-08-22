package com.example.restfulwebservice.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//예외처리를 하기 위한 클래스

@Data
@AllArgsConstructor
@NoArgsConstructor
//모든 컨트롤러에서 사용할 수 있는 일반화 된 예외 클래스 ( 예외 정보 담아서 응답으로 던지는 클래스 )
//
public class ExceptionResponse {

    private Date timeStamp;//예외 발생 시각
    private String message;//예외 발생 메시지
    private String details;//예외 상세 정보

}
