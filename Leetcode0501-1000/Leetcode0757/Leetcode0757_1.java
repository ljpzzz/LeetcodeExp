package Leetcode0757;

import java.util.*;

public class Leetcode0757_1 {
    public int intersectionSizeTwo(int[][] intervals) {
        int n = intervals.length;
        //结束时间从小到大排序，如果结束时间相同，则按照区间开始时间从大到小排序
        Arrays.sort(intervals, (a, b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            else return b[0] - a[0];
        });
        TreeSet<Integer> set = new TreeSet<>();
        set.add(intervals[0][1]);
        set.add(intervals[0][1] - 1);
        for(int i = 1; i < n; i++){
            int start = intervals[i][0];
            int end = intervals[i][1];
            int last = set.last();
            if(last < start){
                set.add(end);
                set.add(end - 1);
            }
            else{
                Integer pre = set.lower(last);
                if(pre == null || pre < start) {
                    if(last != end) set.add(end);
                    else set.add(end - 1);
                }
            }
        }
        return  set.size();
    }
}
