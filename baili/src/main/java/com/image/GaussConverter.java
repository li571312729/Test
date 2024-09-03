package com.image;

import java.math.BigDecimal;

public class GaussConverter {

    double a;
    double f;
    double b;

    public GaussConverter(int type) {
        if (type == 0)//北京五四
        {
            a = 6378245;
            b = 6356863.0188;
        } else if (type == 1)// '西安八零
        {
            a = 6378140;
            b = 6356755.2882;
        }
        if (type == 2)//'WGS84
        {
            a = 6378137;
            b = 6356752.3142;
        }
        if (type == 3)//'CGC2000坐标
        {
            a = 6378137;
            b = 6356752.31414;
        }
        this.f = (a - b) / a;//扁率

    }

    /**
     * @param longitude 经度
     * @param latitude  纬度
     * @param zoneWide  带宽
     * @return
     */
    public double[] gaussBLtoXY(double longitude, double latitude, int zoneWide) {
        int projNo = 0;
        double longitude1, latitude1, longitude0 = 0, X0, Y0, xval, yval;
        double e2, ee, NN, T, C, A, M, iPI;
        // π/180  (Math.PI / 180)
        iPI = 0.0174532925199433;
        if (zoneWide == 6) {
            projNo = (int) longitude / zoneWide; // 查找带号
            longitude0 = projNo * zoneWide + (zoneWide >> 1);
            longitude0 = longitude0 * iPI;// 中央经线
        } else if (zoneWide == 3) {
            projNo = (int) Math.floor(((longitude + 1.5) / zoneWide));
            // 中央经线（弧度制）
            // 三度带计算公式： 中央经线(弧度制) = 带号 * 带宽 * (π/180)
            longitude0 = (projNo * zoneWide) * iPI;
        }
        // 经度转换为弧度
        longitude1 = longitude * iPI;
        // 纬度转换为弧度
        latitude1 = latitude * iPI;

        e2 = 2 * f - f * f;
        ee = e2 * (1.0 - e2);
        NN = a / Math.sqrt(1.0 - e2 * Math.sin(latitude1) * Math.sin(latitude1));
        T = Math.tan(latitude1) * Math.tan(latitude1);
        C = ee * Math.cos(latitude1) * Math.cos(latitude1);
        A = (longitude1 - longitude0) * Math.cos(latitude1);
        M = a * ((1 - e2 / 4 - 3 * e2 * e2 / 64 - 5 * e2 * e2 * e2 / 256) * latitude1 - (3 * e2 / 8 + 3 * e2 * e2 / 32 + 45 * e2 * e2 * e2 / 1024) * Math.sin(2 * latitude1) + (15 * e2 * e2 / 256 + 45 * e2 * e2 * e2 / 1024) * Math.sin(4 * latitude1) - (35 * e2 * e2 * e2 / 3072) * Math.sin(6 * latitude1));
        xval = NN * (A + (1 - T + C) * A * A * A / 6 + (5 - 18 * T + T * T + 14 * C - 58 * ee) * A * A * A * A * A / 120);
        yval = M + NN * Math.tan(latitude1) * (A * A / 2 + (5 - T + 9 * C + 4 * C * C) * A * A * A * A / 24 + (61 - 58 * T + T * T + 270 * C - 330 * ee) * A * A * A * A * A * A / 720);
        X0 = 1000000 * (projNo + 1) + 500000;
        Y0 = 0;
        xval = xval + X0;
        yval = yval + Y0;
        return new double[]{yval, xval};
    }

    @SuppressWarnings("unused")
    private static double retain6(double num) {
        String result = String.format("%.6f", num);
        return Double.parseDouble(result);
    }

    public static void main(String[] args) {
        GaussConverter gaussConverter = new GaussConverter(2);
        double[] doubles = gaussConverter.gaussBLtoXY(117.79024927777778, 31.07824533611111, 6);
//        double[] doubles = gaussConvertor.GeoToGauss(113.554688, 22.25256, 6,19);
        for (double d : doubles) {
            BigDecimal bd = new BigDecimal(d);
            System.out.println(bd.toPlainString());
        }
    }
}
