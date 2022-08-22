package com.example.restfulwebservice.user;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {//DB연결은 Section 5에서
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {  //users가 static이므로 static블록에서 사용가능.
        users.add(new User(1, "Park", new Date()));
        users.add(new User(2, "Kim", new Date()));
        users.add(new User(3, "Choi", new Date()));
    }

    public List<User> findAll() { //사용자 전체 목록
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {//id없으면
            user.setId(++usersCount);//id추가해주고
        }
        user.setJoinDate(new Date());
        users.add(user);//지금 DB역할을하는 List에 추가 후
        return user;    //id가 세팅된 user 리턴
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user; //찾는 user가 있는 경우
            }
        }
        return null;//같은 id값이 없을 경우(빈 화면 출력)
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();// 컬렉션을 열거형 타입으로 변환.(이런것도있다~)
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) { // id같은 데이터 있으면
                iterator.remove();    //삭제
                return user;// 이 때 user 반환
            }
        }
        return null;//데이터를 찾지 못한 경우
    }

    //
    public User changeNameById(int id, User newUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(newUser.getName());
                user.setJoinDate(new Date());
                return user;
            }
        }
        return null;
    }
}
