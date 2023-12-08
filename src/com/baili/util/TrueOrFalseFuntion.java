package com.baili.util;

@FunctionalInterface
public interface TrueOrFalseFuntion {

    public void trueOrFalse(Runnable trueHandler, Runnable falseHandler);
}
