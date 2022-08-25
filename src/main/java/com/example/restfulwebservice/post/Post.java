package com.example.restfulwebservice.post;


import com.example.restfulwebservice.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;



    @JsonIgnore//외부에 데이터 노출X
    @ManyToOne(fetch = FetchType.LAZY)//지연로딩 : 실제 값 쓸 때 로딩한다. ( 영속상태여아함 -> 해결책 OSIV)
    @JoinColumn(name = "user_id")
    private User user;


    //연관관계 편의 메서드
    public void setUser2(User user) {
        this.user=user;
        user.getPosts().add(this);
    }
}
