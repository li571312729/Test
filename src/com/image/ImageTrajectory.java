package com.image;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageTrajectory {
    
    private File picFile;
    
    public ImageTrajectory() {}

    public ImageTrajectory(File picFile) {
        this.picFile = picFile;
    }

    /**
     * 指定图片上绘制指定点轨迹
     * @param xPointArrs x坐标点集合
     * @param yPointArrs y坐标点集合
     * @param i 
     */
    public void trajectoryDrawing(int[] xPointArrs, int[] yPointArrs, String i){
     
        BufferedImage bufImage = null;
        try {
            bufImage = ImageIO.read(picFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("image read error");
        }
        
//        int width = bufImage.getWidth();
//        int height = bufImage.getHeight();
//        System.out.println("width:" + width + ",   height:" + height); 
        
        //绘制路径点
        drawCircular(bufImage, xPointArrs, yPointArrs, 4, new Color(255, 0, 0));
        
        //绘制线段/路径
        drawLine(bufImage, xPointArrs, yPointArrs, 3, new Color(255, 0, 0));
        
        FileOutputStream outImgStream = null;;
        try {
            outImgStream = new FileOutputStream("D://imageTest" + i + ".png");  
            ImageIO.write(bufImage, "png", outImgStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("image write error");
        }finally {
            try {
                if(outImgStream != null){
                    outImgStream.flush();
                    outImgStream.close();
                }
            } catch (IOException e) {}  
        }
    }
    
    /**
     * 绘制圆
     * @param bufImage   图片缓冲区
     * @param xPointArrs x坐标集合
     * @param yPointArrs y坐标集合
     * @param radius 半径
     * @param color 颜色
     */
    private void drawCircular(BufferedImage bufImage, int[] xPointArrs, int[] yPointArrs, int radius, Color color) {
        Graphics2D g2d = bufImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        
        //绘制一个圆: 圆的外切矩形 左上角坐标, 宽高
        for(int i = 0; i < xPointArrs.length; i ++){
            //如果点恰好在图片左上角，防止出错，即以当前坐标为坐上外切坐标
            if(xPointArrs[i] - radius < 0 || yPointArrs[i] - radius < 0){
                g2d.drawOval(xPointArrs[i], yPointArrs[i], radius * 2, radius * 2);
            }else{
                g2d.drawOval(xPointArrs[i] - radius, yPointArrs[i] - radius, radius * 2, radius * 2);
            }
        }

        g2d.dispose();
    }
    
    /**
     * 绘制线段 
     * @param bufImage 图片缓冲区
     * @param xPointArrs    x坐标集合
     * @param yPointArrs    y坐标集合
     * @param color 颜色
     */
    private void drawLine(BufferedImage bufImage, int[] xPointArrs, int[] yPointArrs, int thickness, Color color) {
        Graphics2D g2d = bufImage.createGraphics();
        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 设置画笔颜色
        g2d.setColor(color);

        // 绘制虚线: 将虚线分为若干段（ 实线段 和 空白段 都认为是一段）, 实线段 和 空白段 交替绘制,
        //             绘制的每一段（包括 实线段 和 空白段）的 长度 从 dash 虚线模式数组中取值（从首
        //             元素开始循环取值）, 下面数组即表示每段长度分别为: 5px, 10px, 5px, 10px, ...
        float[] dash = new float[] { 5, 10 };
        BasicStroke bs = new BasicStroke(
                thickness,                      // 画笔宽度/线宽 px
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER,
                10.0f,
                dash,                   // 虚线模式数组
                0.0f                    // 虚线偏移量
        );
        g2d.setStroke(bs);
        
        //多点绘制折线: 
        g2d.drawPolyline(xPointArrs, yPointArrs, xPointArrs.length);
        // 自己创建的副本用完要销毁掉
        g2d.dispose();
    }
    
    public static void main(String[] args) {
        ImageTrajectory imageTrajectory = new ImageTrajectory(new File("D://image.png"));
        int[] xPointArrs = new int[]{100, 200, 300, 400, 500, 600, 700, 500, 600};
        int[] yPointArrs = new int[]{20, 40, 60, 80, 100, 120, 140, 180, 200};
        imageTrajectory.trajectoryDrawing(xPointArrs, yPointArrs, "");
    }

    public File getPicFile() {
        return picFile;
    }

    public void setPicFile(File picFile) {
        this.picFile = picFile;
    }
}
