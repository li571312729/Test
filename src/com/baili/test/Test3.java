package com.baili.test;

import java.io.IOException;
import java.lang.reflect.Field;

public class Test3 {

    public static void main(String[] args) throws IOException {
        addLibraryDir();
    }
    
    private static void addLibraryDir() {
        String dllPath = "C:\\Dependence\\Dll\\".replace("\\\\", "\\");
        dllPath = dllPath.substring(0, dllPath.length() - 1) + ";";
        try {
            Field userPathsField = ClassLoader.class.getDeclaredField("usr_paths");
            userPathsField.setAccessible(true);
            String[] paths = (String[]) userPathsField.get(null);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.length; i++) {
                if (dllPath.equals(paths[i])) {
                    continue;
                }
                sb.append(paths[i]).append(';');
            }
            sb.append(dllPath);
            System.setProperty("java.library.path", sb.toString());
            final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
            System.out.println("添加的动态库路径为:" + dllPath);
            System.out.println("添加动态库路径成功---System LibraryPath :" + System.getProperty("java.library.path"));
        } catch (Exception e) {
            System.out.println("SocketServer 动态添加library 失败！");
        }
        System.loadLibrary("ThermoGroupSDKLib");
        System.loadLibrary("ThermoGroup4java");
        System.out.println("加载 ThermoGroup4java 完毕");
    }

}
