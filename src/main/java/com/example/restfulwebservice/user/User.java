package com.example.restfulwebservice.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@JsonIgnoreProperties({"password", "ssn"})
//@JsonFilter("UserInfo")//필터이름.(컨트롤러, 서비스클래스에서 사용가능)
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "이름은 2글자 이상 입력해주세요.")
    private String name;

    @Past//현재 회원의 가입시점은 미래X 과거만올수있다.
    private Date joinDate;

    //    @JsonIgnore
    private String password;

    //    @JsonIgnore
    private String ssn;
}
