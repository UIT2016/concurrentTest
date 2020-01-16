package com.example.connectionpool.test;

/**
 * @author m
 * @date 2020/1/9 10:11
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 */
public class Solution2 {
    public int findMaxLength(int[] nums) {
        int countOne=0;
        int countZero=0;
        int length=0;
        int sum=0;
        for (int i = 0; i <nums.length ; i++) {
         countOne++;
         countZero++;

        }
      while(countOne!=countZero){
          int temp=countOne>countZero?1:0;
          for (int i = 0; i <nums.length ; i++) {
              if(countOne==countZero)break;
              if(nums[i]==temp)
          }
      }
        System.out.println(countOne+countZero);




        return 0;
    }

    public static void main(String[] args) {

    }
}
/*0 1 0 0 1 0 1 0 1 1 0 0
 * 0 0 0 1
 * 1 1 0 0
 * 0 1 1 0*/