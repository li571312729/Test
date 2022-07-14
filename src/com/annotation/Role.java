package com.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoqiangli
 * @Date
 */
@Target(ElementType.METHOD)
@Repeatable(Roles.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {
    String roleName();
}
