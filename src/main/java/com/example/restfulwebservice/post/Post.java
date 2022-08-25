package com.example.restfulwebservice.post;


import com.example.restfulwebservice.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)//지연로딩 : 실제 값 쓸 때 로딩한다. ( 영속상태여아함 -> 해결책 OSIV)
    @JoinColumn(name = "user_id")
    @JsonIgnore//외부에 데이터 노출X
    private User user;
}
