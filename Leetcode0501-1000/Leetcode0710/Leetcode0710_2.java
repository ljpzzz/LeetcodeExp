package Leetcode0710;

import java.util.*;

public class Leetcode0710_2 {
    int n;
    // key:去掉黑名单后，累加的数字个数， value:合法的区间起点和终点
    TreeMap<Integer, int[]> sectionMap = new TreeMap<>();
    int totalCount;
    public Leetcode0710_2(int n, int[] blacklist) {
        this.n = n;
        Arrays.sort(blacklist);
        totalCount = n-blacklist.length;
        int currentCount = 0;
        int start = 0;
        for(int i = 0; i < blacklist.length; i++){
            int num = blacklist[i];
            if(num == start) start++;
            else{
                currentCount += num-start;
                sectionMap.put(currentCount, new int[]{start, num-1});
                start = num+1;
            }
        }
        if(start < n) sectionMap.put(currentCount+n-start, new int[]{start, n-1});
    }

    public int pick() {
        Random random = new Random();
        int num = random.nextInt(totalCount);
        Integer key = sectionMap.higherKey(num);
        int[] section = sectionMap.get(key);
        int len = section[1]-section[0]+1;
        return section[0] + random.nextInt(len);
    }
}
