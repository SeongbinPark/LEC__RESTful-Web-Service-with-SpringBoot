package com.example.restfulwebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServiceApplication.class, args);
    }

    @Bean//@SpringBootApplication 이 있는 클래스에 @Bean 등록하면 스프링부트가 초기화 될 때 같이 빈으로 등록됨.
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();//세션을 통해 로케일값을 얻어온다.
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }
}
