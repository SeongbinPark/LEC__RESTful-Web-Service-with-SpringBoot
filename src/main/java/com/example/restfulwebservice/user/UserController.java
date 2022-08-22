package com.example.restfulwebservice.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @GetMapping("/users/{id}")//이때 String인 id 가 @pathVariable의 int인 id로 자동변환
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundExcetiion(String.format("ID[%s] not found", id));
        }
        return user;
    }

    //사용자 추가
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {//form형태가 아닌 JSON, XML같은 Object형태의 데이터를 받기 위해선 @RequestBody
        User savedUser = service.save(user);

        //응답코드를 제어 ->SUCB 사용. -> 서버에서 반환하려는 데이터를 ResponseEntity에 담아 전달
        //SUCB를 통해서 '사용자추가'라는 요청된 작업을 완료 후 어떤 URI로 추가된 리소스를 확인가능한지 반환
        //(상세정보보기가 가능한 URI 반환)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")//이 {id}에 방금 만든 유저의 Id값.
                .buildAndExpand(savedUser.getId())
                .toUri();//결과를 Uri 형태로 변환 (반환타입 : URI)

        return ResponseEntity.created(location).build();
        //응답의 Location 헤더에 URI 반환.
    }

}
