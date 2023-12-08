package com.baili.util;

/**
 * @author 86158
 */
public class Utils {

    public static ThrowExceptionFuntion isTrueException(boolean data){
        return (errMessage) -> {
            if(!data){
                throw new RuntimeException(errMessage);
            }
        };
    }

    public static LogPrintMessageFuntion isTruePrintLog(boolean data){
        return (errMessage) -> {
            if(!data){
                System.out.println(errMessage);
            }
        };
    }

    public static TrueOrFalseFuntion isTrueOrFalse(boolean data){
        return (trueHandle, falseHandle) -> {
            if(data){
                trueHandle.run();
            }else {
                falseHandle.run();
            }
        };
    }

    public static PresentOrElseFuntion isPresentOrElse(Object data){
        return (consumer, runnable) -> {
            if(data!= null){
                consumer.accept(data);
            }else {
                runnable.run();
            }
        };
    }
}
