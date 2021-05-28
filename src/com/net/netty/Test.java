package com.net.netty;

import java.io.File;
import java.util.Optional;

/**
 * @author lxq
 * @date 2021年04月27日 14:24
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String a = "/a";
        System.out.println(a.substring(0, a.lastIndexOf('/')));

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
    address address;

    public com.net.netty.address getAddress() {
        return address;
    }

    public void setAddress(com.net.netty.address address) {
        this.address = address;
    }
}
