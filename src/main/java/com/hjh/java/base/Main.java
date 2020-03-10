package com.hjh.java.base;

import java.util.HashMap;
import java.util.Map;

/** Created with IntelliJ IDEA. @Author: 黄俊辉 @Create: 2018-01-25 @Description: */
public class Main {
    public static void main(String[] args) {
        String a = "xiaomeng2";
        final String b = "xiaomeng";
        String d = "xiaomeng";
        String c = b + 2;
        String e = d + 2;
        System.out.println((b == d));
        System.out.println((a == c));
        System.out.println((a == e));
        System.out.println((c == e));

        System.out.println(Math.round(-1.5));

        StringBuffer buffer = new StringBuffer();
        buffer.append(a);

        StringBuilder builder = new StringBuilder();
        builder.append(b);

    }
}