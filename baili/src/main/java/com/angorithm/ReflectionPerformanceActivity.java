package com.angorithm;//package com.algorithm;
//
//import lombok.SneakyThrows;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
///**
// * 反射性能测试
// *
// * @author Administrator
// */
//public class ReflectionPerformanceActivity {
//
//    private Executor mPerformanceExecutor = Executors.newFixedThreadPool(4);
//
//    public static void main(String[] args) {
//        ReflectionPerformanceActivity reflectionPerformanceActivity = new ReflectionPerformanceActivity();
//        reflectionPerformanceActivity.execute(1000000000);
//    }
//
//    private void execute(int count) {
//        mPerformanceExecutor.execute(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                long reflectMethodCostTime = getTime();
//                for (int index = 0; index < count; index++) {
//                    Car.runS();
//                }
//                System.out.println("执行直接调用方法平均耗时：" + (getTime() - reflectMethodCostTime) / count + " 毫秒");
//            }
//        });
//
//        mPerformanceExecutor.execute(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                long normalMethodCostTime = getTime();
//                for (int index = 0; index < count; index++) {
//                    Class<Car> car = Car.class;
//                    Method method = car.getMethod("runS", null);
//                    method.invoke(null, null);
//                }
//                System.out.println("执行反射直接调用方法耗时：" + (getTime() - normalMethodCostTime) / count + " 毫秒");
//            }
//        });
//
//        mPerformanceExecutor.execute(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                long normalMethodCostTime = getTime();
//                for (int index = 0; index < count; index++) {
//                    Car car = new Car();
//                    car.run();
//                }
//                System.out.println("执行普通调用实例平均耗时：" + (getTime() - normalMethodCostTime) / count + " 毫秒");
//            }
//        });
//
//        mPerformanceExecutor.execute(new Runnable() {
//            @SneakyThrows
//            @Override
//            public void run() {
//                long normalMethodCostTime = getTime();
//                for (int index = 0; index < count; index++) {
//                    Class carClass = Class.forName("com.algorithm.Car");
//                    Car car = (Car) carClass.newInstance();
//                    car.run();
//                }
//                System.out.println("执行反射调用实例平均耗时：" + (getTime() - normalMethodCostTime) / count + " 毫秒");
//            }
//        });
//    }
//
//    private long getTime() {
//        return System.currentTimeMillis();
//    }
//
//
//}
