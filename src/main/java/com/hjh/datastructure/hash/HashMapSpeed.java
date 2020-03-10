package com.hjh.datastructure.hash;

/**
 * @author hjh
 * @date 2020/1/12
 * hashmap的 hash方法效率
 */
public class HashMapSpeed {

    public static void hashtable(int num,int length) {
        long st = System.currentTimeMillis();
        long sum = 0;
        for(int i = 0 ; i < num ; i++) {
            int hash = Integer.hashCode(i);
            sum +=  (hash & 0x7FFFFFFF) % length;
        }
        long et = System.currentTimeMillis();
        System.out.println("sum:"+sum+",time:"+(et-st));
    }

    public static void hashMap16(Integer num,int length) {
        long st = System.currentTimeMillis();
        long sum = 0;
        for(int i = 0 ; i < num ; i++) {
            int h = Integer.hashCode(i);
            h ^= (h >>> 20) ^ (h >>> 12);
            h ^= (h >>> 7) ^ (h >>> 4);
            sum += h & (length - 1);
        }
        long et = System.currentTimeMillis();
        System.out.println("sum:"+sum+",time:"+(et-st));
    }

    public static void hashMap18(Integer num,int length) {
        long st = System.currentTimeMillis();
        long sum = 0;
        for(int i = 0 ; i < num ; i++) {
            int h = Integer.hashCode(i);
            h ^= (h >>> 16);
            sum += h & (length - 1);
        }
        long et = System.currentTimeMillis();
        System.out.println("sum:"+sum+",time:"+(et-st));
    }

    public static void hashSpeed () {
        int num = 100000000;
        hashtable(num,256);
        hashMap16(num,256);
        hashMap18(num,256);
    }

    public static void query() {

    }

    public static void main(String[] args) {
        //

    }
}
