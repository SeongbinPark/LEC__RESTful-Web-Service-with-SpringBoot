package com.example.restfulwebservice.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.SortedMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController { // 그냥 userController보다 좀 더 중요한 기능을 쓸 수 있다.

    private final UserDaoService service;
    //스프링 컨테이너의 빈은 개발자가 프로그램 실행 도중 변경 불가


    //사용자 전체 목록 조회
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    //사용자 개별 조회
    @GetMapping("/users/{id}")//이때 String인 id 가 @pathVariable의 int인 id로 자동변환
    public MappingJacksonValue retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", " name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }
}

/**
 * UserController에서 카피, 조회메서드 두 개만 남김.
 * @RequestMapping("/admin")
 */