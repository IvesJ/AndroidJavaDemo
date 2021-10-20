package com.acecoder.javalib.classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {
    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("E:\\JAVA_Dev\\TestClass");

        try {
            Class c = diskClassLoader.loadClass("com.example.Jobs");
            if (c != null) {
                Object obj = c.newInstance();
                System.out.println(obj.getClass().getClassLoader());
                Method method = c.getDeclaredMethod("say");
                method.invoke(obj);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
