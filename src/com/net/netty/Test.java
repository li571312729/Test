package com.net.netty;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author lxq
 * @date 2021年04月27日 14:24
 */
public class Test {
    public static void main(String[] args) {

        Date from = Date.from(LocalDateTime.now().plusDays(3).atZone(ZoneId.systemDefault()).toInstant());

    }
}

