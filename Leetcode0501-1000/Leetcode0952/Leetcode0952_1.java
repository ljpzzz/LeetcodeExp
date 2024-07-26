package Leetcode0952;

import java.util.*;

public class Leetcode0952_1 {
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        Set<Integer> numSet = new HashSet<>();
        int MAX = (int)1e5;
        for(int num : nums) {
            numSet.add(num);
            MAX = Math.max(MAX, num);
        }
        DSU dsu = new DSU(MAX+1);
        int[] score = new int[MAX+1];
        Set<Integer> primes = new HashSet<>();
        for(int i = 2; i <= MAX; i++){
            if(score[i] == 0){
                primes.add(i);
                for(int j = i; j <= MAX; j += i) {
                    score[j]++;
                    if(numSet.contains(j)) dsu.union(j, i);
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            int prime = dsu.find(num);
            map.put(prime, map.getOrDefault(prime, 0) + 1);
        }
        int ans = 0;
        for(int prime : map.keySet()) ans = Math.max(ans, map.get(prime));
        return ans;
    }
    public class DSU {
        private int count;
        private int[] parent;
        public DSU(int count){
            this.count = count;
            parent = new int[count];
            for(int i = 0; i < count; i++) parent[i] = i;
        }
        public int find(int x){
            if(x == parent[x]) return x;
            else return find(parent[x]);
        }
        public boolean union(int edgeX, int edgeY){
            int rootX = find(edgeX);
            int rootY = find(edgeY);
            if(rootX == rootY) return false;
            if(rootX < rootY) parent[rootY] = rootX;
            else parent[rootX] = rootY;
            count--;
            return true;
        }
        public int getCount(){
            return count;
        }
    }
}
