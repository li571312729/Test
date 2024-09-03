package com.image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageTrajectoryTest {

    public static void main(String[] args) {
        /**使用   大地坐标  start **/
        //初始化地图的两个点（经纬度，像素）
//        TransUtil transUtil = new TransUtil(new Point(116.35926279918812,39.958363943655044),new Point(116.42478799513962,39.87619972326687),
//                new Point(227, 81),new Point(479, 484));
//          
//        //需要绘制的经纬度点
//        List<Point> list = new ArrayList<>();
//        list.add(new Point(116.35926279918812,39.958363943655044));
//        list.add(new Point(116.42478799513962,39.87619972326687));
//        list.add(new Point(116.43745013803841,39.912433318440314));
//        list.add(new Point(116.31540167126462,39.89179020827215));
//        //获取大地坐标像素比例
//        transUtil.getBLPixelScale();
//        //获取大地的像素坐标
//        List<Point> pixelCoordinates = transUtil.getBLCoordinate(list);
        /**使用   大地坐标  end **/
        
        /**使用   经纬度  start **/
        //初始化地图的两个点（经纬度，像素）
        TransUtil transUtil = new TransUtil(new Point(116.308721, 39.694145),new Point(116.306493, 39.692625),
                new Point(655, 61),new Point(383, 304));

        //需要绘制的经纬度点
        List<Point> list = new ArrayList<>();
        list.add(new Point(116.308721, 39.694145));
        list.add(new Point(116.306493, 39.692625));
        list.add(new Point(116.306115,39.69368));
        list.add(new Point(116.305208,39.693832));
        list.add(new Point(116.304867,39.693152));
        list.add(new Point(116.308974,39.691421));
        list.add(new Point(116.310726,39.691948));
        list.add(new Point(116.312945,39.691691));
        list.add(new Point(116.310439,39.693308));
        list.add(new Point(116.30866,39.693579));

        //获取经纬度获取像素比例
        transUtil.getLLPixelScale();
        //获取经纬度像素坐标
        List<Point> pixelCoordinates = transUtil.getLLCoordinate(list);
        /**使用   经纬度  end **/

        ImageTrajectory imageTrajectory = new ImageTrajectory(new File("D://image.png"));
        int[] xPointArrs = new int[pixelCoordinates.size()];
        int[] yPointArrs = new int[pixelCoordinates.size()];
        
        for(int i = 0; i < pixelCoordinates.size(); i ++){
            xPointArrs[i] = (int) pixelCoordinates.get(i).getX();
            yPointArrs[i] = (int) pixelCoordinates.get(i).getY();
            System.out.println("X:" + xPointArrs[i] + ",Y:" + yPointArrs[i]);
        }

        imageTrajectory.trajectoryDrawing(xPointArrs, yPointArrs, "");
    }

}
