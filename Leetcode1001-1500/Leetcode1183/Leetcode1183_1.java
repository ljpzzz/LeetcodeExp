package Leetcode1183;

import java.util.*;

public class Leetcode1183_1 {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int x = sideLength;
        int[] cnt = new int[x*x];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int ii = i%x;
                int jj = j%x;
                cnt[ii*x+jj]++;
            }
        }
        Arrays.sort(cnt);
        int ans = 0;
        for(int i = x*x-1; i >= x*x-maxOnes; i--) ans += cnt[i];
        return ans;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode1183_1().maximumNumberOfOnes(3,3,2,1));
        System.out.println(new Leetcode1183_1().maximumNumberOfOnes(3,3,2,2));
    }
}
