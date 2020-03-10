package com.hjh.java.Thread;

/**
 * @author hjh
 * @date 2019/12/23
 */
public class StaticInit {

    private static int x = 100;

    private static int y = 200;

    static {
        System.out.println(Thread.currentThread().getName());
        System.out.println(x);
        System.out.println(y);
    }

    public static void main(String[] args) {

    }

}
class Book {

    public static void main(String[] args) {
        System.out.println("Hello ShuYi.");
    }

    Book() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price +",amount=" + amount);
    }

    {
        System.out.println("书的普通代码块");
    }
    int price = 110;
    static {
        System.out.println("书的静态代码块");
    }
    static int amount = 112;
}
