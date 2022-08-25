//package com.example.restfulwebservice.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration // 이거 붙여놓으면 스프링부트 기동 시 메모리에 설정정보 로딩 ( 빈으로 등록 )
//public class SercurityConfig  {
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)//주입이 안됨. 버전이 바뀜.
//        throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("username")
//                .password("{noop}password")// 인코딩없이 사용할 수 있도록.
//                .roles("USER");//로그인 하면 USER 권한
//        }
//}
