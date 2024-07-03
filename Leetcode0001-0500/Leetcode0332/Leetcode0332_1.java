package Leetcode0332;

import java.util.*;

public class Leetcode0332_1 {
    List<String> ans = new ArrayList<>();
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    //欧拉回路hierholzer算法
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> ticket : tickets){
            map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>((a, b)->(a.compareTo(b)))).add(ticket.get(1));
        }
        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }
    public void dfs(String cur){
        while(map.containsKey(cur) && !map.get(cur).isEmpty()){
            dfs(map.get(cur).poll());
        }
        ans.add(cur);
    }
    public static void main(String args[]){
        Leetcode0332_1 leetcode0332_1 = new Leetcode0332_1();
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));
        System.out.println(leetcode0332_1.findItinerary(tickets));
    }
}
