package com.example.restfulwebservice.post;

import com.example.restfulwebservice.user.User;
import com.example.restfulwebservice.user.UserNotFoundException;
import com.example.restfulwebservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable int id) {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            throw new PostNotFoundException(String.format("No id[%s]", id));
        }

        return post.get();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        postRepository.deleteById(id);
    }

    @PostMapping("{id}")
    public ResponseEntity<Post> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> findUser = userRepository.findById(id);
        if (!findUser.isPresent()) {
            throw new UserNotFoundException("no user");
        }
        post.setUser2(findUser.get()); // 연관관계 편의 메서드

        Post savedPost = postRepository.save(post);

        // URI반환하려고                                            //현재 요청으로 부터
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                // /{id} 라는 URI를 추가해서 반환하겠다.
                .path("/{id}")//이 {id}에 방금 만든 Id값.
                .buildAndExpand(savedPost)
                .toUri();//결과를 Uri 형태로 변환 (반환타입 : URI)

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post) {
        Post post1 = postRepository.findById(post.getId()).get();

        BeanUtils.copyProperties(post, post1);

        return ResponseEntity.ok().build();


    }

}
