package com.example.restfulwebservice.user;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @Past//현재 회원의 가입시점은 미래X 과거만올수있다.
    private Date joinDate;
}
