package Leetcode0759;

import java.util.*;

class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
}
public class Leetcode0759_1 {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        TreeMap<Integer, Integer> deltaMap = new TreeMap<>();
        for (List<Interval> cur : schedule) {
            for (Interval inter : cur) {
                deltaMap.put(inter.start, deltaMap.getOrDefault(inter.start, 0) + 1);
                deltaMap.put(inter.end, deltaMap.getOrDefault(inter.end, 0) - 1);
            }
        }
        int delta = 0;
        int start = -1;
        List<Interval> ans = new ArrayList<>();
        for (int pos : deltaMap.keySet()) {
            int val = deltaMap.get(pos);
            delta += val;
            if (delta == 0) {
                if (start == -1) start = pos;
                //start已经有，不需要更新
            } else if (start != -1) {
                ans.add(new Interval(start, pos));
                start = -1;
            }
        }
        return ans;
    }

    public static void main(String args[]) {
        List<List<Interval>> schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 2), new Interval(5, 6)));
        schedule.add(Arrays.asList(new Interval(1, 3), new Interval(4, 10)));
        List<Interval> ans = new Leetcode0759_1().employeeFreeTime(schedule);
        for (Interval cur : ans) System.out.println(cur.start + "," + cur.end);
        schedule = new ArrayList<>();
        schedule.add(Arrays.asList(new Interval(1, 3), new Interval(6, 7)));
        schedule.add(Arrays.asList(new Interval(2, 4)));
        schedule.add(Arrays.asList(new Interval(2, 5), new Interval(9, 12)));
        ans = new Leetcode0759_1().employeeFreeTime(schedule);
        for (Interval cur : ans) System.out.println(cur.start + "," + cur.end);
    }
}
