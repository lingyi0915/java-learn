package com.mooc.os;

/**
 * @author hjh
 * @date 2020/1/5
 */
public class Q2_Clock {
    public static void main(String[] args) {

        java.util.Scanner in = new java.util.Scanner(System.in);

        Clock clock = new Clock(in.nextInt(), in.nextInt(), in.nextInt());

        clock.tick();

        System.out.println(clock);

        in.close();

    }
}

class Clock{

    private Display hour;
    private Display min;
    private Display sec;

    public Clock(int hour, int minute, int second) {
        this.hour = new Display(hour,24);
        min = new Display(minute,60);
        sec = new Display(second,60);
    }

    void tick(){
        hour.increase(min.increase(sec.increase(1)));
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d",hour.value,
                min.value,
                sec.value);
    }
}

class Display {
    int value;
    int limit;

    public Display(int value, int limit) {
        this.value = value;
        this.limit = limit;
    }

    int increase (int i) {
        if((value+=i)>=limit) {
            int res = value/limit;
            value = value%limit;
            return res;
        }
        return 0;
    }
}