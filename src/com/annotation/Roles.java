package com.annotation;

import java.lang.annotation.*;

/**
 * @author xiaoqiangli
 * @Date
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Roles {
    Role[] value();
}
