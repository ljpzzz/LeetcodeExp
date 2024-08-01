package Leetcode1157;

import java.util.*;

public class Leetcode1157_2 {
    //分块
    int n;
    int N; //大于分块长度s一半的元素数量
    int s; //分块大小
    int[] arr;
    Map<Integer, Integer> arrCntMap = new HashMap<>();
    int[] possibleVal = new int[205]; //记录大于分块长度s一半的元素
    int[][] pSum = new int[205][20005]; //pSum[i][j]表示第i个大于分块长度s一半的元素，在前j个元素出现的次数
    public Leetcode1157_2(int[] arr) {
        n = arr.length;
        this.arr = arr;
        N = 0;
        s = (int)Math.sqrt(2*n);
        for(int i = 0; i < n; i++) arrCntMap.put(arr[i], arrCntMap.getOrDefault(arr[i], 0)+1);
        for(int val : arrCntMap.keySet()){
            if(arrCntMap.get(val) > s/2){
                N++;
                possibleVal[N] = val;
                pSum[N][0] = 0;
                for(int i = 1; i <= n; i++){
                    pSum[N][i] = pSum[N][i-1];
                    if(arr[i-1] == val) pSum[N][i]++;
                }
            }
        }

    }

    public int query(int left, int right, int threshold) {
        int len = right - left + 1;
        //长度小于等于s，直接暴力投票
        if(len <= s){
            int cnt = 1;
            int vote = arr[left];
            for(int i = left+1; i <= right; i++){
                if(arr[i] == vote) cnt++;
                else if(cnt > 0) cnt--;
                else{
                    vote = arr[i];
                    cnt = 1;
                }
            }
            cnt = 0;
            for(int i = left; i <= right; i++){
                if(arr[i] == vote) cnt++;
            }
            if(cnt >= threshold) return vote;
            else return -1;
        }
        //长度大于s，则枚举数量大于s/2的值
        else{
            for(int i = 1; i <= N; i++){
                int val = possibleVal[i];
                int cnt = pSum[i][right+1] - pSum[i][left];
                if(cnt >= threshold) return val;
            }
            return -1;
        }
    }
}
