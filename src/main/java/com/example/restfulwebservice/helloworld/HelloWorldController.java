package com.example.restfulwebservice.helloworld;


import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    //    @RequestMapping(method = RequestMethod.GET, path = "/hello-word")
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
        //String.format(표현할 문자열, 들어갈 데이터);
    }

}
