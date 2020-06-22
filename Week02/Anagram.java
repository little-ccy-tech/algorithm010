package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 异位词：包含相同的字母，但是顺序不同
 * 判断两个字符串是否是有效的字母异位词
 * 特殊情况：完全相同的字符串也是异位词
 *
 * @author chenchunyu
 * @date 2020/6/22
 */
public class Anagram {

    public static void main(String[] args) {
        String s = "qwe";
        String t = "wqe";

        boolean res = isAnagram_1(s, t);
        boolean res1 = isAnagram_2(s, t);

        System.out.println(res);
        System.out.println(res1);
    }

    /**
     * 暴力求解法
     * 1、对两个字符串的字符数组做排序
     * 2、比较排序后的数组是否完全相等，相等则是有效的异位词
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram_1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChar = s.toCharArray();
        Arrays.sort(sChar);
        char[] tChar = t.toCharArray();
        Arrays.sort(tChar);

        for (int i=0;i<s.length();i++){
            if (sChar[i] != tChar[i]){
                return false;
            }
        }
        return true;
    }


    /**
     * 利用一个hash表结构来记录字符串中每个字母出现的次数
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram_2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] scharArray = s.toCharArray();
        char[] tcharArray = t.toCharArray();
        Map<Character,Integer> charNum = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            //s字符串中字符出现的次数加1
            char schar = scharArray[i];
            charNum.put(schar,charNum.get(schar)==null?1:charNum.get(schar)+1);


            //t字符串中字符出现的次数减1
            char tchar = tcharArray[i];
            charNum.put(tchar,charNum.get(tchar)==null?-1:charNum.get(tchar)-1);

        }

        //最后对hash表遍历，判断是否每个key对应的value都是0
        //任何一个key的值value不是0，都表明两个字符串不是异位词
        for (Character character : charNum.keySet()) {
            if (charNum.get(character) != 0){
                return false;
            }
        }
        return true;
    }
}
