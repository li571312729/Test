package com.image;

public class Matrix1 {

    int row, column;            //矩阵的行列数 
    double [][] data;            //矩阵的数据 
    //构造函数1
    public Matrix1(int rowNum,int columnNum)
    {
        row = rowNum; 
        column = columnNum; 
        data = new double[row][ column];
    }     
    
    public Matrix1(double[][] members) 
   { 
        row = members.length; 
        column = members[1].length; 
        data = new double[row][ column]; 
        
        for(int i = 0; i < members.length; i ++){
            for(int j = 0; j < members[i].length; j++){
                data[i][j] = members[i][j];
            }
            
        }
 
   }
    
    //矩阵乘法
    public boolean MatrixMultiply(double[][] a, double[][] b, double[][] c)
    {
        if (a[1].length != b[0].length)
            return false;
        if ((a[0].length != c[0].length) || (b[1].length != c[1].length))
            return false;
        for (int i = 0; i < a[0].length; i++)
        {
            for (int j = 0; j < b[1].length; j++)
            {
                c[i][ j] = 0;
                for (int k = 0; k < b[0].length; k++)
                {
                    c[i][ j] += a[i][ k] * b[k][ j];
                }
            }
        }

        return true;
    }

    //  矩阵相加
    public boolean MatrixAdd(double[][] a, double[][] b, double[][] c)
    {
        if (a[0].length != b[0].length || a[1].length != b[1].length
            || a[0].length != c[0].length || a[1].length != c[1].length)
            return false;
        for (int i = 0; i < a[0].length; i++)
        {
            for (int j = 0; j < a[1].length; j++)
            {
                c[i][ j] = a[i][ j] + b[i][ j];
            }
        }

        return true;
    }

    //矩阵相减
    public boolean MatrixSubtration(double[][] a, double[][] b, double[][] c)
    {
        if (a[0].length != b[0].length || a[1].length != b[1].length
            || a[0].length != c[0].length || a[1].length != c[1].length)
            return false;
        for (int i = 0; i < a[0].length; i++)
        {
            for (int j = 0; j < a[1].length; j++)
            {
                c[i][ j] = a[i][ j] - b[i][ j];
            }
        }

        return true;
    }

    //// 矩阵的行列式的值 
    public double MatrixSurplus(double[][] a)
    {
        int i, j, k, p, r, m, n;
        m = a[0].length;
        n = a[1].length;
        double X, temp = 1, temp1 = 1, s = 0, s1 = 0;

        if (n == 2)
        {
            for (i = 0; i < m; i++)
                for (j = 0; j < n; j++)
                    if ((i + j) % 2 > 0) temp1 *= a[i][ j];
                    else temp *= a[i][ j];
            X = temp - temp1;
        }
        else
        {
            for (k = 0; k < n; k++)
            {
                for (i = 0, j = k; i < m && j < n; i++, j++)
                    temp *= a[i][ j];
                if (m - i > 0)
                {
                    for (p = m - i, r = m - 1; p > 0; p--, r--)
                        temp *= a[r][ p - 1];
                }
                s += temp;
                temp = 1;
            }

            for (k = n - 1; k >= 0; k--)
            {
                for (i = 0, j = k; i < m && j >= 0; i++, j--)
                    temp1 *= a[i][ j];
                if (m - i > 0)
                {
                    for (p = m - 1, r = i; r < m; p--, r++)
                        temp1 *= a[r][ p];
                }
                s1 += temp1;
                temp1 = 1;
            }

            X = s - s1;
        }
        return X;
    }

    //   矩阵转置
    public boolean MatrixInver(double[][] a, double[][] b)
    {
        if (a[0].length != b[1].length || a[1].length != b[0].length)
            return false;
        for (int i = 0; i < a[1].length; i++)
            for (int j = 0; j < a[0].length; j++)
                b[i][ j] = a[j][ i];

        return true;
    }

    //矩阵求逆
    public boolean MatrixOpp(double[][] a, double[][] b)
    {
        double X = MatrixSurplus(a);
        if (X == 0) return false;
        X = 1 / X;

        double[][] B = new double[a[0].length][ a[1].length];
        double[][] SP = new double[a[0].length][ a[1].length];
        double[][] AB = new double[a[0].length][ a[1].length];

        for (int i = 0; i < a[0].length; i++)
            for (int j = 0; j < a[1].length; j++)
            {
                for (int m = 0; m < a[0].length; m++)
                    for (int n = 0; n < a[1].length; n++)
                        B[m][ n] = a[m][ n];
                {
                    for (int x = 0; x < a[1].length; x++)
                        B[i][ x] = 0;
                    for (int y = 0; y < a[0].length; y++)
                        B[y][ j] = 0;
                    B[i][ j] = 1;
                    SP[i][ j] = MatrixSurplus(B);
                    AB[i][ j] = X * SP[i][ j];
                }
            }
        MatrixInver(AB, b);

        return true;
    }
    //矩阵求逆的重载，精度比较高
    public  double[][] MatrixOpp(double[][] Array)
    {
        int m = 0;
        int n = 0;
        m = Array[0].length;
        n = Array[1].length;
        double[][] array = new double[2 * m + 1][ 2 * n + 1];
        for (int k = 0; k < 2 * m + 1; k++)  //初始化数组
        {
            for (int t = 0; t < 2 * n + 1; t++)
            {
                array[k][ t] = 0.00000000;
            }
        }
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                array[i][ j] = Array[i][ j];
            }
        }

        for (int k = 0; k < m; k++)
        {
            for (int t = n; t <= 2 * n; t++)
            {
                if ((t - k) == m)
                {
                    array[k][ t] = 1.0;
                }
                else
                {
                    array[k][ t] = 0;
                }
            }
        }
        //得到逆矩阵
        for (int k = 0; k < m; k++)
        {
            if (array[k][ k] != 1)
            {
                double bs = array[k][ k];
                array[k][ k] = 1;
                for (int p = k + 1; p < 2 * n; p++)
                {
                    array[k][ p] /= bs;
                }
            }
            for (int q = 0; q < m; q++)
            {
                if (q != k)
                {
                    double bs = array[q][ k];
                    for (int p = 0; p < 2 * n; p++)
                    {
                        array[q][ p] -= bs * array[k][ p];
                    }
                }
                else
                {
                    continue;
                }
            }
        }
        double[][] NI = new double[m][ n];
        for (int x = 0; x < m; x++)
        {
            for (int y = n; y < 2 * n; y++)
            {
                NI[x][ y - n] = array[x][ y];
            }
        }
        return NI;
    }
}
