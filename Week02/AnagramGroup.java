package leetcode;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字母异位词分组
 * 把一个字符串数组中，属于字母异位词的字符串分为一个组，以便生产多个字符串数组
 *
 * @author chenchunyu
 * @date 2020/6/22
 */
public class AnagramGroup {

    public static void main(String[] args) {
        String[] stringArray = {"people", "apple", "ppeelo", "ppale"};
        List<List<String>> lists = groupAnagrams_1(stringArray);
        System.out.println(JSON.toJSONString(lists));
    }


    /**
     * 暴力法
     * 1、先对字符串数组中的字符串做排序
     * 2、额外的一个hash表，key为字符串 值为List<String>
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams_1(String[] strs) {

        Map<String, List<String>> resMap = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String oneStr = strs[i];
            char[] chars = oneStr.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            List<String> strings = resMap.get(s);
            if (null == strings || strings.size() == 0) {
                strings = new ArrayList<>();
            }
            strings.add(oneStr);
            resMap.put(s, strings);
        }
        return resMap.values().stream().collect(Collectors.toList());
    }
}
