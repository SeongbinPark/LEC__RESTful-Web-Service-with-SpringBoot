package com.example.restfulwebservice.user;

import com.example.restfulwebservice.user.User;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"password", "ssn"})
@JsonFilter("UserInfoV2")//필터이름.(컨트롤러, 서비스클래스에서 사용가능)
public class UserV2 extends User { // Super 클래스에 기본생성자 없어서 요류 -> 만들어줌

    private String grade;
}
