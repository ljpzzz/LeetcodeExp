package Leetcode0898;

import java.util.*;

public class Leetcode0898_1 {
    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> ans = new HashSet<>();
        int LEN = 30;
        int[] pos = new int[LEN]; //最近的1的位置
        Arrays.fill(pos, n);
        for(int i = n - 1; i >= 0; i--){
            int x = arr[i];
            ans.add(x);
            List<Integer> changeList = new ArrayList<>();
            for(int j = 0; j < LEN; j++){
                if((x&(1<<j)) != 0) pos[j] = i;
                else if(pos[j] < n) changeList.add(pos[j]);
            }
            Collections.sort(changeList);
            for(int change : changeList){
                x |= arr[change];
                ans.add(x);
            }
        }
        return ans.size();
    }
}
