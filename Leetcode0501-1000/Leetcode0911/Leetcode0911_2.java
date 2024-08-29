package Leetcode0911;

import java.util.TreeMap;

public class Leetcode0911_2 {
    // key: time value: 被选中的人
    TreeMap<Integer, Integer> map = new TreeMap<>();
    public Leetcode0911_2(int[] persons, int[] times) {
        int n = persons.length;
        int[] personVote = new int[5000];
        int maxVote = 0;
        int maxPerson = -1;
        for(int i = 0; i < n; i++){
            personVote[persons[i]]++;
            if(personVote[persons[i]] >= maxVote){
                maxVote = personVote[persons[i]];
                maxPerson = persons[i];
            }
            map.put(times[i], maxPerson);
        }
    }

    public int q(int t) {
        int time = map.floorKey(t);
        return map.get(time);
    }
}
