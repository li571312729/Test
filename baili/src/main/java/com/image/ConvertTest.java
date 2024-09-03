package com.image;

import java.math.BigDecimal;

public class ConvertTest {
    
    /* 此函数用于求取平面四参数 未用到坐标重心化,实际上是舍去了正形变换的高阶项
     * 输入：po是源坐标,pt是目标坐标,可选参数n 取前n(>=2)点平差
     * 输出：resultMat是结果矩阵数组
     *     第一个矩阵 X      是所求的四参数
     *     第二个矩阵 V      是改正数,可以算内符合精度
     *     第三个矩阵 Sigma2 是验后单位权中误差的平方
     */
   public static Matrix[] Get_Four1(Point[] po, Point[] pt, int n){
       
       if(n==0) n = po.length;
       int r = 2 * n - 4;//多余观测数
       Matrix[] resultMat = new Matrix[3];
       Matrix B = new Matrix(2 * n, 4);
       Matrix P = new Matrix(2 * n);
       P.MakeUnitMatrix(2 * n);//默认权矩阵为单位阵
       Matrix L = new Matrix(2 * n, 1);
       Matrix X = new Matrix(4, 1);//参数
       for (int i = 0; i < n; i++)
       {
           double x = po[i].getX(), y = po[i].getY();
           double x1 = pt[i].getX(), y1 = pt[i].getY();
           B.setRC(2 * i, 0, 1);
           B.setRC(2 * i, 1, 0);
           B.setRC(2 * i, 2, x);
           B.setRC(2 * i, 3, -y);
           B.setRC(2 * i + 1, 0, 0);
           B.setRC(2 * i + 1, 1, 1);
           B.setRC(2 * i + 1, 2, y);
           B.setRC(2 * i + 1, 3, x);
           B.setRC(2 * i, 0, x1);
           B.setRC(2 * i + 1, 0, y1);
       }
       
        Matrix V = null;
        Matrix Sigma2 = null;
        try {
            Matrix Nbb = Matrix.operatorMu(Matrix.operatorMu(B.Transpose(), P), B);
               Nbb.InvertGaussJordan();
               X = Matrix.operatorMu(Matrix.operatorMu(Matrix.operatorMu(Nbb, B.Transpose()), P), L);
               V = Matrix.operatorDe(Matrix.operatorMu(B, X), L);
               Sigma2 = Matrix.operatorMu(Matrix.operatorMu(V.Transpose(), P), V);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       if (r != 0) 
           Sigma2.setRC(0, 0, Sigma2.getRC(0, 0) / r);
       else 
           Sigma2.setRC(0, 0, 0);

       resultMat[0] = X;
       resultMat[1] = V;
       resultMat[2] = Sigma2;
       return resultMat;
   }
   
   /* 此函数用于四参数坐标转换，对应函数为 Get_Four1
    * 输入：po是源坐标; M 是用四参数求取函数得到的矩阵数组
    * 输出：pt是目标坐标
    */
  public static Point[] zbTrans4(Point[] po, Matrix[] M)
  {
      int n=po.length;
      Point[] pt = new Point[n];
      Matrix X = M[0];
      Matrix B = new Matrix(2, 4);
      Matrix Pt = null;
      for (int i = 0; i < n; i++)
      {
          double x = po[i].getX(), y = po[i].getY();
          B.setRC(0, 0, 1);
          B.setRC(0, 1, 0);
          B.setRC(0, 2, x);
          B.setRC(0, 3, -y);
          B.setRC(1, 0, 0);
          B.setRC(1, 1, 1);
          B.setRC(1, 2, y);
          B.setRC(1, 3, x);
          try {
            Pt = Matrix.operatorMu(B, X);
        } catch (Exception e) {
        }
          pt[i] = new Point(Pt.getRC(0, 0), Pt.getRC(1, 0), 0);
      }
      return pt;
  }
  
  
  /*此函数用于求象限角
   */ 
  public static double arctan(double dx, double dy){
      double angle = 0;
      if(dx==0){
          if(dy>0) 
              angle=Math.PI*0.5;
          else     
              angle=Math.PI*1.5;
          return angle;
      }
      
      angle = Math.atan2(Math.abs(dy), Math.abs(dx));
      
      if (dx < 0){
          if (dy < 0) 
              angle = Math.PI + angle;
          else        
              angle = Math.PI - angle;
      }else if (dy < 0) 
          angle = 2 * Math.PI - angle;
      return angle;
  }
  
  public static void Canshu4(Point[] p1, Point[] p2, int PointCount){
            double u = 1.0, v = 0, Dx = 0.0, Dy = 0.0;
            int intCount = PointCount;
            //Matrix1 dx1 ;//误差方程改正数
            Matrix1 B;//误差方程系数矩阵
            // Matrix1 W ;//误差方程常数项
            double[][] dx1 = new double[4][ 1];
            double[][] B1 = new double[2 * intCount][ 4];
            double[][] W1 = new double[2 * intCount][ 1];
            // Matrix1 BT, N, InvN, BTW;
            double[][] BT = new double[4][ 2 * intCount];
            double[][] N = new double[4][ 4];
            double[][] InvN = new double[4][ 4];
            double[][] BTW = new double[4][ 1];
            for (int i = 0; i < intCount; i++){
            //计算误差方程系数
            B1[2 * i][ 0] = 1;
            B1[2 * i][ 1] = 0;
            B1[2 * i][ 2] = p1[i].getX();
            B1[2 * i][ 3] = -p1[i].getY();
            B1[2 * i + 1][ 0] = 0;
            B1[2 * i + 1][ 1] = 1;
            B1[2 * i + 1][ 2] = p1[i].getY();
            B1[2 * i + 1][ 3] = p1[i].getX();
            }
            B = new Matrix1(B1);
            for (int i = 0; i < intCount; i++){
            //计算误差方程系常数
            W1[2 * i][ 0] = p2[i].getX() - u * p1[i].getX() + v * p1[i].getY() - Dx;
            W1[2 * i + 1][ 0] = p2[i].getY() - u * p1[i].getY() - v * p1[i].getX() - Dy;
            }
            //最小二乘求解
            B.MatrixInver(B1, BT);//转置
            B.MatrixMultiply(BT, B1, N);
            InvN = B.MatrixOpp(N);
            B.MatrixMultiply(BT, W1, BTW);
            B.MatrixMultiply(InvN, BTW, dx1);
            Dx = Dx + dx1[0][ 0];
            Dy = Dy + dx1[1][ 0];
            u = u + dx1[2][ 0];
            v = v + dx1[3][ 0];
            double rota, scale, dx, dy;
            dx = Dx;
            dy = Dy;
            rota = Math.atan(v / u);
            scale = u / Math.cos(rota);
            
            Point transPoint = new Point(4, 1, 1);
            double x = Dx + (1 + scale ) * (Math.cos(rota) * transPoint.getX() + Math.sin(rota) * transPoint.getY());
            double y = Dy + (1 + scale ) * (-Math.sin(rota) * transPoint.getX() +  Math.cos(rota)* transPoint.getY());
            System.out.println("x: " + new BigDecimal(x).toPlainString());
            System.out.println("y: " + new BigDecimal(y).toPlainString());
            
 }
  
  /*此函数用于求四参数的近似值,
   * 输入：po=源坐标,pt=目标坐标
   * 输出：
   * 0 尺度因子
   * 1 旋转角度
   * 2 x平移参数
   * 3 y平移参数
   */ 
  public static double[] Get_Four_JS(Point po1, Point po2, Point pt1, Point pt2){
      double[] JS = new double[4];
      double dx = po1.getX() - po2.getX(), dy = po1.getY() - po2.getY();
      double dX = pt1.getX() - pt2.getX(), dY = pt1.getY() - pt2.getY();
      double s = Math.sqrt(dx * dx + dy * dy);
      double S = Math.sqrt(dX * dX + dY * dY);
      double A = arctan(dX, dY) * (180 / Math.PI);
      double a = arctan(dx, dy) * (180 / Math.PI);
      JS[0] = s / S;
      JS[1] = A - a;
      JS[2] = pt1.getX() - po1.getX();
      JS[3] = pt1.getY() - po1.getY();
      
      Point transPoint = new Point(4, 1, 1);
      double x = JS[2] + (1 + JS[0] ) * (Math.cos(JS[1]) * transPoint.getX() + Math.sin(JS[1]) * transPoint.getY());
      double y = JS[3] + (1 + JS[0] ) * (-Math.sin(JS[1]) * transPoint.getX() +  Math.cos(JS[1])* transPoint.getY());
      System.out.println("x: " + new BigDecimal(x).toPlainString());
      System.out.println("y: " + new BigDecimal(y).toPlainString());
      return JS;
  }


  
  public static void main(String[] args) {
      Point[] po = new Point[4];
      //x1 0,1  y1 1,0  源点
      Point x1 = new Point(3, 1, 1);
      Point y1 = new Point(4, 1, 1);
      Point y3 = new Point(5, 1, 1);
      Point y4 = new Point(6, 1, 1);
      po[0] = x1;
      po[1] = y1;
      po[2] = y3;
      po[3] = y4;
      
      //目标点 X1 Y1
      Point[] pt = new Point[4];
      Point X1 = new Point(2, 1, 1);
      Point Y1 = new Point(3, 1, 1);
      Point Y3 = new Point(4, 1, 1);
      Point Y4 = new Point(5, 1, 1);
      pt[0] = X1;
      pt[1] = Y1;
      pt[2] = Y3;
      pt[3] = Y4;
      
      Point[] pTest = new Point[1];
      Point pointTest = new Point(0, 1, 1);
      pTest[0] = pointTest;
      
      Canshu4(po, pt, 4);
      Get_Four_JS(x1, y1, X1, Y1);
      
//      Point[] result = zbTrans4(pTest, Get_Four1(po, pt, 2));
//      for(Point point : result){
//          System.out.println(point);
//      }
  }
  
}
