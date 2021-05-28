package com.baili.test;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author lxq
 * @date 2021年04月27日 14:24
 */
public class OptionalTest {
    public static void main(String[] args) throws Exception {


        User user = new User();
        address address = new address();
        address.setCity("hello");
        user.setAddress(address);
//        getCity(user);
//        doSomething(user);
        city(user);
    }

    private static void city(User user) {
        Optional.ofNullable(user).filter(o -> "hello".equals(o.getAddress().getCity())).orElseGet(() -> new User());
    }

    private static void doSomething(User user) {
        Optional.ofNullable(user).map(u -> u.getAddress().getCity()).ifPresent(System.out::println);
    }

    public static String getCity(User user) throws Exception{
        return Optional.ofNullable(user)
                .map(u-> u.getAddress())
                .map(a->a.getCity())
                .orElseThrow(()->new Exception("取值错误"));
    }
}


class address {
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

class User {
    com.baili.test.address address;

    public com.baili.test.address getAddress() {
        return address;
    }

    public void setAddress(com.baili.test.address address) {
        this.address = address;
    }
}
