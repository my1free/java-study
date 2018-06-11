package com.michealyang.base.jar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println("App's ClassLoader is: " + App.class.getClassLoader());
        ClassLoader parent = App.class.getClassLoader().getParent();
        System.out.println("AppClassLoader's parent is:" + parent);
        parent = parent.getParent();
        System.out.println("ExtClassLoader's parent is:" + parent);

        String test = "test";
        System.out.println("String's ClassLoader is:" + test.getClass().getClassLoader());

        InnerClass innerClass = new InnerClass();
        System.out.println("InnerClass's ClassLoader is:" + innerClass.getClass().getClassLoader());
    }

    static class InnerClass{
        //
    }
}
