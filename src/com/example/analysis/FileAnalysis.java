package com.example.analysis;

import com.example.entity.CR6Series_2_Flux_AmeriFluxFormat;
import com.example.entity.CR6Series_2_Flux_CSFormat;
import com.example.entity.CR6Series_2_Time_Series;
import com.example.entity.HDC3_2_Mini30;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lxq
 * @date 2021年09月06日 14:36
 */
@Slf4j
public class FileAnalysis {

    private static final String HDC3_2_MINI30_FILE = "HDC3_2_Min30.dat";
    private static final String CR6SERIES_2_TIME_SERIES = "CR6Series_2_Time_Series.dat";
    private static final String CR6SERIES_2_FLUX_CSFORMAT = "CR6Series_2_Flux_CSFormat.dat";
    private static final String CR6SERIES_2_FLUX_AMERIFLUXFORMAT = "CR6Series_2_Flux_AmeriFluxFormat.dat";

    public static void main(String[] args) {
        List<Object> hdc3_2_mini30s = fileDeal(HDC3_2_Mini30.class, HDC3_2_MINI30_FILE);

        List<Object> cR6Series_2_Time_Series = fileDeal(CR6Series_2_Time_Series.class, CR6SERIES_2_TIME_SERIES);

        List<Object> cR6Series_2_Flux_CSFormat = fileDeal(CR6Series_2_Flux_CSFormat.class, CR6SERIES_2_FLUX_CSFORMAT);

        List<Object> cR6Series_2_Flux_AmeriFluxFormat = fileDeal(CR6Series_2_Flux_AmeriFluxFormat.class, CR6SERIES_2_FLUX_AMERIFLUXFORMAT);
        log.info("HDC3_2_Min30.dat: " + hdc3_2_mini30s.size());
        log.info("CR6Series_2_Time_Series.dat: " + cR6Series_2_Time_Series.size());
        log.info("cR6Series_2_Flux_CSFormat.dat: " + cR6Series_2_Flux_CSFormat.size());
        log.info("cR6Series_2_Flux_AmeriFluxFormat.dat: " + cR6Series_2_Flux_AmeriFluxFormat.size());
    }

    public static List<Object> fileDeal(Class fileClass, String filePath){
        List<Object> results = new ArrayList<>(10);
        try(
                FileInputStream is = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
        ){
            String data = null;
            int count = 0;
            log.info("{}文件头信息如下：", fileClass.getSimpleName());
            while((data = br.readLine()) != null) {
                if(++count <= 4){
                    log.info(data);
                    continue;
                }
                String[] split = data.split(",|，");
                Object o = fileClass.newInstance();
                Field[] fields = o.getClass().getDeclaredFields();

                if (split.length != fields.length) {
                    log.error("文件{},第{}行数据,【{}】格式有误!跳过改行数据解析。", fileClass.getSimpleName(), count, data);
                    continue;
                }

                for (int i = 0; i < split.length; i++) {
                    if(split[i].contains("\"")){
                        split[i] = split[i].replaceAll("\"", "");
                    }
                    fields[i].setAccessible(true);
                    fields[i].set(o, split[i]);
                }
                results.add(o);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return results;
    }
}
