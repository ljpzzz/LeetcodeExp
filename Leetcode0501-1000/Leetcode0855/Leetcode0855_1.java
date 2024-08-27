package Leetcode0855;

import java.util.*;

public class Leetcode0855_1 {
    TreeSet<int[]> set;
    Map<Integer, int[]> startEndMap;
    Map<Integer, int[]> endStartMap;
    int n;
    public Leetcode0855_1(int n) {
        this.n = n;
        //按区间加人后的距离从大到小排序，一样则按区间起点从小小到大排序
        set = new TreeSet<>((a, b) -> {
            int len1 = a[1]-a[0]+1;
            int len2 = b[1]-b[0]+1;
            int sep1 = (len1-1)/2;
            if(a[0] == 0 || a[1] == n-1) sep1 = len1-1;
            int sep2 = (len2-1)/2;
            if(b[0] == 0 || b[1] == n-1) sep2 = len2-1;
            if(sep1 != sep2) return sep2 - sep1;
            else return a[0] - b[0];
        });
        int[] seg = new int[]{0, n-1};
        set.add(seg);
        startEndMap = new HashMap<>();
        endStartMap = new HashMap<>();
        startEndMap.put(0, seg);
        endStartMap.put(n-1, seg);
    }

    public int seat() {
        int[] interval = set.pollFirst();
        int start = interval[0];
        int end = interval[1];
        startEndMap.remove(start);
        endStartMap.remove(end);
        if(start == end) return start;
        if(start == 0){
            int[] seg = new int[]{start+1, end};
            set.add(seg);
            startEndMap.put(start+1, seg);
            endStartMap.put(end, seg);
            return start;
        }
        if(end == n-1){
            int[] seg = new int[]{start, end-1};
            set.add(seg);
            startEndMap.put(start, seg);
            endStartMap.put(end-1, seg);
            return end;
        }
        int ans = (start + end)/2;
        if(start < ans) {
            int[] seg = new int[]{start, ans-1};
            set.add(seg);
            startEndMap.put(start, seg);
            endStartMap.put(ans-1, seg);
        }
        if(ans < end) {
            int[] seg = new int[]{ans+1, end};
            set.add(seg);
            startEndMap.put(ans+1, seg);
            endStartMap.put(end, seg);
        }
        return ans;
    }

    public void leave(int p) {
        if(!startEndMap.containsKey(p+1) && !endStartMap.containsKey(p-1)){
            int[] seg = new int[]{p, p};
            set.add(seg);
            startEndMap.put(p, seg);
            endStartMap.put(p, seg);
        }
        else if(!startEndMap.containsKey(p+1) && endStartMap.containsKey(p-1)){
            int[] seg = endStartMap.remove(p-1);
            startEndMap.remove(seg[0]);
            set.remove(seg);
            int[] newSeg = new int[]{seg[0], p};
            set.add(newSeg);
            startEndMap.put(seg[0], newSeg);
            endStartMap.put(p, newSeg);
        }
        else if(startEndMap.containsKey(p+1) && !endStartMap.containsKey(p-1)){
            int[] seg = startEndMap.remove(p+1);
            endStartMap.remove(seg[1]);
            set.remove(seg);
            int[] newSeg = new int[]{p, seg[1]};
            set.add(newSeg);
            startEndMap.put(p, newSeg);
            endStartMap.put(seg[1], newSeg);
        }
        else{
            int[] seg1 = startEndMap.remove(p+1);
            endStartMap.remove(seg1[1]);
            set.remove(seg1);
            int[] seg2 = endStartMap.remove(p-1);
            startEndMap.remove(seg2[0]);
            set.remove(seg2);
            int[] newSeg = new int[]{seg2[0], seg1[1]};
            set.add(newSeg);
            startEndMap.put(seg2[0], newSeg);
            endStartMap.put(seg1[1], newSeg);
        }
    }
    public static void main(String[] args) {
        Leetcode0855_1 obj = new Leetcode0855_1(8);
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        System.out.println(obj.seat());
        obj.leave(0);
        obj.leave(7);
        System.out.println(obj.seat());
        System.out.println(obj.seat());
    }
}
