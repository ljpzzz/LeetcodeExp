package Leetcode0911;

import java.util.*;

public class Leetcode0911_1 {
    //key:时间， value：领先的用户
    TreeMap<Integer, Integer> map;
    TreeSet<int[]> set;  //int[] 包括用户，票数，最后投票时间
    Map<Integer, int[]> personVoteMap;
    public Leetcode0911_1(int[] persons, int[] times) {
        map = new TreeMap<>();
        set = new TreeSet<>((a, b)->{
            if(a[1] != b[1]) return b[1]-a[1];
            else return b[2]-a[2];
        });
        personVoteMap = new HashMap<>();
        map.put(times[0], persons[0]);
        int[] obj = new int[] {persons[0], 1, times[0]};
        set.add(obj);
        personVoteMap.put(persons[0], obj);
        int n = persons.length;
        for(int i = 1; i < n; i++){
            int[] cur = personVoteMap.get(persons[i]);
            if(cur == null){
                cur = new int[] {persons[i], 1, times[i]};
                set.add(cur);
                personVoteMap.put(persons[i], cur);
            }
            else{
                set.remove(cur);
                cur[1]++;
                cur[2] = times[i];
                set.add(cur);
                personVoteMap.put(persons[i], cur);
            }
            int[] leader = set.first();
            map.put(times[i], leader[0]);
        }
    }

    public int q(int t) {
        Integer time = map.floorKey(t);
        if(time == null) return -1;
        return map.get(time);
    }
    public static void main(String[] args) {
        int[] persons = {0,1,1,0,0,1,0};
        int[] times = {0,5,10,15,20,25,30};
        Leetcode0911_1 obj = new Leetcode0911_1(persons, times);
        System.out.println(obj.q(3));
        System.out.println(obj.q(12));
        System.out.println(obj.q(25));
        System.out.println(obj.q(15));
        System.out.println(obj.q(24));
        System.out.println(obj.q(8));
    }

}
