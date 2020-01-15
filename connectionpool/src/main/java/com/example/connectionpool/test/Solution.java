package com.example.connectionpool.test;

/**
 * @author m
 * @date 2020/1/8 17:37
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] a=new int[26];
        int sum=0;
        for (int i = 0; i <chars.length() ; i++) {
            a[chars.charAt(i)-97]++;
        }
        for(String s:words){
            int i=0;
            int[] b=a.clone();
            for (; i <s.length() ; i++) {
                int pos=s.charAt(i)-97;
                if(b[pos]==0){
                    break;
                }
                b[pos]--;

            }
            if(i==s.length()){
                sum+=s.length();
            }

        }
        return sum;
    }

    public static void main(String[] args) {
       String[] s={"cat","bt","hat","tree"};
        String a= "atach";
        System.out.println(new Solution().countCharacters(s,a));
    }
}
