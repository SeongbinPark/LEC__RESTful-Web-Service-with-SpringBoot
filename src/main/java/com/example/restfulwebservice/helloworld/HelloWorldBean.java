package com.example.restfulwebservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data//@Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message;


}
