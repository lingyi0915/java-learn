package com.hjh.leetcode;

import java.util.*;

/**
 * @author hjh
 * @date 2020/2/10
 */
public class P49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        for(int i = 0 ; i < strs.length ; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String s = new String(c);
            if(map.containsKey(s)) {
                map.get(s).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(s,list);
                res.add(list);
//                list.remove();
            }
        }
        return res;
    }
}
