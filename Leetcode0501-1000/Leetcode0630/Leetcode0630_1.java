package Leetcode0630;

import java.util.*;

public class Leetcode0630_1 {
    public int scheduleCourse(int[][] courses) {
        int n = courses.length;
        //按完成时间从小到大排序
        Arrays.sort(courses, (a,b)->{
            return a[1]-b[1];
        });
        //优先队列里基于持续时间从大到小排列
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->(b-a));
        int time = 0;
        for (int i = 0; i < n; i++) {
            int duration = courses[i][0];
            int end = courses[i][1];
            if (time + duration <= end){
                time += duration;
                queue.offer(duration);
            }else if (!queue.isEmpty() && queue.peek() > duration){
                time += duration - queue.poll();
                queue.offer(duration);
            }
        }
        return queue.size();
    }
}
