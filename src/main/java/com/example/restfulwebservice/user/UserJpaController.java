package com.example.restfulwebservice.user;


import com.example.restfulwebservice.post.Post;
import com.example.restfulwebservice.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jpa")
public class UserJpaController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User retrieveUser(@PathVariable int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new UserNotFoundException(String.format("Id[%s] not founded", userId));
        }

        return user.get();
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userRepository.deleteById(userId);
    }

    @PostMapping("/users")//상태코드도 반환하려고 ResponseEntity사용
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userRepository.save(user);

        //id값을 지정해보자
        //응답코드를 제어 ->SUCB 사용. -> 서버에서 반환하려는 데이터를 ResponseEntity에 담아 전달
        //SUCB를 통해서 '사용자추가'라는 요청된 작업을 완료 후 어떤 URI로 추가된 리소스를 확인가능한지 반환
        //(상세정보보기가 가능한 URI 반환)
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")//이 {userId}에 방금 만든 유저의 Id값.
                .buildAndExpand(newUser)
                .toUri();//결과를 Uri 형태로 변환 (반환타입 : URI)

        return ResponseEntity.created(location).build();

    }


    //
    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id) {
        Optional<User> findUser = userRepository.findById(id);
        if (!findUser.isPresent()) {
            throw new UserNotFoundException("no user");
        }

        return findUser.get().getPosts();


    }
}
