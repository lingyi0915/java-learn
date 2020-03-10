package com.mooc.os;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author hjh
 * @date 2020/1/5
 */
public class Q3_Main {
    public static void main(String[] args) {
        CityDist q3 = new CityDist();
        q3.citys = new HashMap<String,Integer>();
        Scanner in = new Scanner(System.in);

        String city = in.next();
        while(!"###".equals(city)) {
            q3.citys.put(city,q3.len++);
            city = in.next();
        }
        q3.distinct = new int[q3.len][q3.len];
        for(int i = 0 ; i < q3.len ; i++) {
            for(int j = 0 ; j < q3.len ; j++) {
                int dis = in.nextInt();
                q3.distinct[i][j] = dis;
            }
        }

        String start = in.next();
        String end = in.next();

        int sp =  q3.citys.get(start);
        int ep = q3.citys.get(end);

        System.out.println(q3.distinct[sp][ep]);
    }
}

class CityDist{
    int len = 0;
    Map<String,Integer> citys;
    int[][] distinct;

}