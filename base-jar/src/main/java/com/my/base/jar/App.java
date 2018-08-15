package com.my.base.jar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        int CODE_SHIFT = 1;
        int CODE_SUCCESS = CODE_SHIFT >> 1;
        int CODE_FORBBIDEN = CODE_SHIFT << 1;
        int CODE_LOCAL = CODE_SHIFT << 2;
        System.out.println(CODE_SUCCESS);
        System.out.println(CODE_FORBBIDEN);
        System.out.println(CODE_LOCAL);
        System.out.println((double)600 / 161156458);
    }

    static class InnerClass{
        //
    }
}
