package com.image;

import java.math.BigDecimal;

public class Test {
    
    /**
     * 获取旋转角度
     * 
     * @param p1 源点1
     * @param b1 目标点1
     * @param p2 源点2
     * @param b2 目标点2
     * @return 旋转参数
     */
    public  double getAngle(Point Point1, Point newPoint1, Point Point2, Point newPoint2) {
        double x = Point2.getX() - Point1.getX();
        double y = Point2.getY() - Point1.getY();
        double X = newPoint2.getX() - newPoint1.getX();
        double Y = newPoint2.getY() - newPoint1.getY();
        
        double A = Math.atan(Y / X) * (180 / Math.PI);
        double a = Math.atan(y / x) * (180 / Math.PI);
        
        return A - a;
    }
 
    /**
     * 获取缩放比例
     *
     * @param Point1 源点1
     * @param newPoint1 目标点1
     * @param Point2 源点2
     * @param newPoint2 目标点2
     * @return 缩放比例
     */
    public double getScale(Point Point1, Point newPoint1, Point Point2, Point newPoint2) {
        double sx1 = Point2.getX() - Point1.getX();
        double sy1 = Point2.getY() - Point1.getY();
        double newSx1 = newPoint2.getX() - newPoint1.getX();
        double newSy1 = newPoint2.getY() - newPoint1.getY();
        
        // 原线段的向量长度
        double s =  Math.sqrt(Math.pow(sx1, 2) + Math.pow(sy1, 2));
        
        // 新线段的向量长度
        double S =  Math.sqrt(Math.pow(newSx1, 2) + Math.pow(newSy1, 2));
        
        // 尺度因子
        double m = (S - s) / S;
        return m;
    }
 
    /**
     * X方向偏移距离参数
     *
     * @param Point1       源点1
     * @param newPoint1    目标点1
     * @return X方向偏移
     */
    public double getXTranslation(Point Point1, Point newPoint1) {
        return newPoint1.getX() - Point1.getX();
    }
 
    /**
     * Y方向偏移距离参数
     *
     * @param Point1       源点1
     * @param newPoint1       目标点1
     * @return Y方向偏移
     */
    public double getYTranslation(Point Point1, Point newPoint1) {
        return newPoint1.getY() - Point1.getY();
    }
 
    /**
     * 转换操作
     *
     * @param gp       源点
     * @param rotation 旋转角度
     * @param scale    缩放比例
     * @param dx       X方向偏移
     * @param dy       Y方向偏移
     * @return 目标点
     */
    public Point transformBoePoint(Point gp, double rotation, double scale, double dx, double dy) {
        double x = dx + (1 + scale ) * (Math.cos(rotation) * gp.getX() + Math.sin(rotation) * gp.getY());
        double y = dy + (1 + scale ) * (-Math.sin(rotation) * gp.getX() +  Math.cos(rotation)* gp.getY());
        return new Point(retain10(x), retain10(y), 0.0);
    }
 
    /**
     * 保留小数点后10位
     *
     * @param num
     * @return
     */
    public static double retain10(double num) {
        String result = String.format("%.6f", num);
        return Double.valueOf(result);
    }
    
    public static void main(String[] args) {
        double a = 4425102.610382132;
        double b = 2.044525155952721E7;
        System.out.println(new BigDecimal(a).toPlainString());
        System.out.println(new BigDecimal(b).toPlainString());
        System.out.println(new BigDecimal(a + b).toPlainString());
        System.out.println(new BigDecimal(a).add(new BigDecimal(b)).toPlainString());
//        Test transUtil = new Test();
//        Point Point1 = new Point(0, 1, 1);
//        Point Point2 = new Point(1, 0, 1);
//        
//        //newPoint1 newPoint2（新坐标系对应的两个点） 分别对应Point1 -> newPoint1  point2 -> newPoint2 
//        Point newPoint1 = new Point(3, 4, 1);
//        Point newPoint2 = new Point(2, 3, 1);
//        
//        //平移参数dx，dy
//        double dx = transUtil.getXTranslation(Point1, newPoint1);
//        double dy = transUtil.getYTranslation(Point1, newPoint1);
//        
//        //伸缩的尺度因子
//        double scale = transUtil.getScale(Point1, newPoint1, Point2, newPoint2);
//        
//        //旋转角参数
//        double rotation = transUtil.getAngle(Point1, newPoint1, Point2, newPoint2);
//        
//        //需要转换的坐标 x,y,z
//        Point transPoint = new Point(13, 4, 1);
//        Point resultPoint = transUtil.transformBoePoint(transPoint, rotation, scale, dx, dy);
//        System.out.println("x: " + new BigDecimal(resultPoint.getX()).toPlainString());
//        System.out.println("y: " + new BigDecimal(resultPoint.getY()).toPlainString());
    }
}
