package com.example.restfulwebservice.user;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService service;
    //스프링 컨테이너의 빈은 개발자가 프로그램 실행 도중 변경 불가


    //사용자 전체 목록 조회
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    //사용자 개별 조회
    @GetMapping("/user/{id}")//이때 String인 id 가 @pathVariable의 int인 id로 자동변환
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    //사용자 추가
    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {//form형태가 아닌 JSON, XML같은 Object형태의 데이터를 받기 위해선 @RequestBody
        return service.save(user);
    }

}
