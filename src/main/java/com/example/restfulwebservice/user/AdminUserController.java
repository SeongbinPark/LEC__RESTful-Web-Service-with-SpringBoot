package com.example.restfulwebservice.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        //===Filter===//
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
        //===Filter===//
    }

    //사용자 개별 조회 V1
    @GetMapping("/v1/users/{id}")//이때 String인 id 가 @pathVariable의 int인 id로 자동변환
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        //===Filter===//
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
        //===Filter===//

    }

    //사용자 개별 조회 V2
    @GetMapping("/v2/users/{id}")//이때 String인 id 가 @pathVariable의 int인 id로 자동변환
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {
        User user = service.findOne(id);//이거 고치긴 귀찮아서 User -> UserV2로 필드 다 옮길거다.

        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP"); //이건 user에 없어서 따로 추가.


        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        //===Filter===//
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
        //===Filter===//

    }
}

/**
 * UserController에서 카피, 조회메서드 두 개만 남김.
 * @RequestMapping("/admin")
 */