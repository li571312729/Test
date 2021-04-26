package com.image;

import java.util.ArrayList;
import java.util.List;

public class TransUtil{

    private Point p1;
    private Point p2;
    private Point b1;
    private Point b2;
    private double dx;
    private double dy;

    public TransUtil(Point p1, Point p2, Point b1, Point b2) {
        this.p1 = p1;
        this.p2 = p2;
        this.b1 = b1;
        this.b2 = b2;
    }

    /**
     * 大地坐标按照转像素坐标，获取相应的坐标比例
     * 
     */
    public void  getBLPixelScale() {
        GaussConverter gaussConverter = new GaussConverter(2);
        double[] double1= gaussConverter.gaussBLtoXY(p1.getX(), p1.getY(), 6);
        double[] double2= gaussConverter.gaussBLtoXY(p2.getX(), p2.getY(), 6);
        dx = ( double2[0] - double1[0] ) / ( b2.getX() - b1.getX());
        dy = ( double2[1] - double1[1] ) / ( b2.getY() - b1.getY());
    }
    
    /**
     * 大地坐标获取对应像素坐标
     * @param pointList
     * @return
     */
    public List<Point> getBLCoordinate(List<Point> pointList){
        GaussConverter gaussConverter = new GaussConverter(2);
        List<Point> resultList = new ArrayList<>();
        double[] gaussBLtoXY = gaussConverter.gaussBLtoXY(p1.getX(), p1.getY(), 6);
        pointList.forEach(p -> {
            //传进来的P经纬度转换成大地
            double[] doubles= gaussConverter.gaussBLtoXY(p.getX() , p.getY(), 6);

            double x = (doubles[0] - gaussBLtoXY[0]) / dx + b1.getX();
            double y = (doubles[1] - gaussBLtoXY[1]) / dy + b1.getY();
            resultList.add(new Point(x, y));
        });
        return resultList;
    }

    /**
     * 经纬度按照转像素坐标，获取相应的坐标比例
     */
    public void getLLPixelScale() {
        dx = (p2.getX() - p1.getX() ) / ( b2.getX() - b1.getX());
        dy = (p2.getY() - p1.getY() ) / ( b2.getY() - b1.getY());
    }

    /**
     * 经纬度坐标获取对应像素坐标
     * @param pointList
     * @return
     */
    public List<Point> getLLCoordinate(List<Point> pointList){
        List<Point> resultList = new ArrayList<>();
        pointList.forEach(p -> {
            double x = (p.getX() - p1.getX()) / dx + b1.getX();
            double y = (p.getY() - p1.getY()) / dy + b1.getY();
            resultList.add(new Point(x, y));
        });
        return resultList;
    }

    public static void main(String[] args) {
        TransUtil transUtil = new TransUtil(new Point(116.610089, 39.8931),new Point(116.607025, 39.894241),
                                            new Point(400, 509),new Point(59, 345));
        Point p3 = new Point();
        p3.setX(116.607645);
        p3.setY(39.894089);
        Point p4 = new Point();
        p4.setX(116.611544);
        p4.setY(39.89604);
        List<Point> list = new ArrayList<>();
        list.add(p3);
        list.add(p4);

        transUtil.getLLPixelScale();
        List<Point> all2 = transUtil.getLLCoordinate(list);
        all2.forEach(p -> {
            System.out.println(p);
        });
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getB1() {
        return b1;
    }

    public Point getB2() {
        return b2;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public void setB1(Point b1) {
        this.b1 = b1;
    }

    public void setB2(Point b2) {
        this.b2 = b2;
    }
    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}

