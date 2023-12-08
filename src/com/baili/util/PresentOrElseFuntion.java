package com.baili.util;

import java.util.function.Consumer;

/**
 * @author 86158
 */
@FunctionalInterface
public interface PresentOrElseFuntion<T extends Object> {

    public void presentOrElseHandle(Consumer<? extends T> consumer, Runnable runnable);
}
