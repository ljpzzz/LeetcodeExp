package Leetcode0710;

import java.util.*;

public class Leetcode0710_1 {
    List<int[]> seg = new ArrayList<>();
    int n;
    int cnt;
    int bLen;
    public Leetcode0710_1(int n, int[] blacklist) {
        this.n = n;
        Arrays.sort(blacklist);
        bLen = blacklist.length;
        if(bLen == 0) return;
        cnt = 0;
        if(blacklist[0] != 0) {
            cnt += blacklist[0];
            seg.add(new int[]{0, blacklist[0] - 1, cnt});
        }
        for(int i = 1; i < blacklist.length; i++){
            if(blacklist[i] - blacklist[i - 1] > 1) {
                cnt += blacklist[i] - blacklist[i - 1] - 1;
                seg.add(new int[]{blacklist[i - 1] + 1, blacklist[i] - 1, cnt});
            }
        }
        if(blacklist[bLen - 1] != n - 1){
            cnt += n - blacklist[bLen - 1] - 1;
            seg.add(new int[]{blacklist[bLen - 1] + 1, n - 1, cnt});
        }

    }

    public int pick() {
        if(bLen == 0) return (int)(Math.random() * n);
        int inx = (int)(Math.random() * cnt);
        int left = 0;
        int right = seg.size() - 1;
        int ans = -1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(seg.get(mid)[2] > inx){
                ans = mid;
                right = mid - 1;
            }
            else left = mid + 1;
        }
        int end = seg.get(ans)[1];
        int total = seg.get(ans)[2];
        return end + inx + 1 - total;
    }
    public static void main(String[] args) {
        Leetcode0710_1 obj = new Leetcode0710_1(7, new int[]{2, 3, 5});
        System.out.println(obj.pick());
        System.out.println(obj.pick());
        System.out.println(obj.pick());
        System.out.println(obj.pick());
        System.out.println(obj.pick());
        System.out.println(obj.pick());
        System.out.println(obj.pick());
    }
}
