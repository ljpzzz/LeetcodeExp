package Leetcode1125;

import java.util.*;

public class Leetcode1125_1 {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int m = req_skills.length;
        Map<String, Integer> skillMap = new HashMap<>();
        for (int i = 0; i < m; i++) skillMap.put(req_skills[i], i);
        int n = people.size();
        int[] maskPeople = new int[n];
        for(int i = 0; i < n; i++) {
            List<String> cur = people.get(i);
            int mask = 0;
            for (String skill : cur) mask |= 1 << (skillMap.get(skill));
            maskPeople[i] = mask;
        }
        //dp[i][j]表示mask为j时，最少需要多少人
        int[] dp = new int[1 << m];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        List<Integer>[] dpSet = new List[1 << m];
        for(int j = 0; j < (1 << m); j++) dpSet[j] = new ArrayList<>();

        for(int j = 0; j < (1 << m); j++) {
            for(int i = 0; i < n; i++){
                if((j|maskPeople[i]) == j) continue;
                if(dp[j|maskPeople[i]] > 1+dp[j]){
                    dp[j|maskPeople[i ]] =  1+dp[j];
                    dpSet[j|maskPeople[i]] = new ArrayList<>(dpSet[j]);
                    dpSet[j|maskPeople[i]].add(i);
                }
            }
        }
        int[] ans = new int[dpSet[(1 << m) - 1].size()];
        for(int i = 0; i < ans.length; i++) ans[i] = dpSet[(1 << m) - 1].get(i);
        return ans;
    }
    public static void main(String[] args) {
        String[] req_skills = {"java","nodejs","reactjs"};
        List<List<String>> people = new ArrayList<>();
        people.add(Arrays.asList("java"));
        people.add(Arrays.asList("nodejs"));
        people.add(Arrays.asList("nodejs","reactjs"));
        Leetcode1125_1 l = new Leetcode1125_1();
        System.out.println(Arrays.toString(l.smallestSufficientTeam(req_skills, people)));
    }
}
