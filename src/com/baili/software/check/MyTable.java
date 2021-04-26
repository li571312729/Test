package com.baili.software.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.swing.JTable;

//列表
public class MyTable{
    private JTable jTable;
    private Object[][] data=new Object[100][4];
    private Object[] colNames= { "软件名称","版本号","出版商","卸载路径"};
    private int p=-1;
    
    public MyTable(){
        
    }
    
    public void addRow(Object[] data){
        p++;
        if(p>=100) return ;
        this.data[p]=data;
    }
    
    
    public JTable getTable(){
        jTable=new JTable(data,colNames);
        return jTable;
    }
    
    public static void main(String[] args) throws IOException, URISyntaxException { 
		Runtime runtime = Runtime.getRuntime();
		File file = new File("src/com/baili/software/check/a.txt");
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
		
        String path = file.getCanonicalPath().replace("\\", "\\\\");
        String path1 = "D:\\eclipse source\\NewTest\\src\\com\\baili\\software\\check\\vlc-2.2.4-win32.exe";
        String cmd = "cmd /c Start /WAIT \"" + path1 + "\"";
		Process process = runtime.exec(cmd);
        BufferedReader in = new BufferedReader(new InputStreamReader(process
                .getInputStream(),"GBK"));
        String string = null;
        while ((string = in.readLine()) != null) {
           System.out.println(string);
        }
        in.close();
        process.destroy();
    }

}
