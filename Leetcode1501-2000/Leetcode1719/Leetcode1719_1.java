package Leetcode1719;

import java.util.*;

public class Leetcode1719_1 {
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for(int[] p : pairs){
            int x = p[0];
            int y = p[1];
            g.computeIfAbsent(x, k->new HashSet<>());
            g.computeIfAbsent(y, k->new HashSet<>());
            g.get(x).add(y);
            g.get(y).add(x);
        }
        int n = g.size();
        int root = -1;
        for(int cur : g.keySet()){
            if(g.get(cur).size() == n-1){
                root = cur;
                break;
            }
        }
        if(root == -1) return 0;
        int res = 1; //默认只有一棵树，遍历过程中可能发现有2棵树，或者一颗都没有
        for(int cur : g.keySet()){
            if(cur == root) continue;
            Set<Integer> neigh = g.get(cur);
            int parent = -1; //度大于等于当前cur的度的最小值即为cur的父节点
            int parentDegree = 0x3f3f3f3f;
            for(int next : neigh){
                if(g.get(next).size() >= neigh.size() && g.get(next).size() < parentDegree){
                    parent = next;
                    parentDegree = g.get(next).size();
                }
            }
            if(parent == -1) return 0;
            //检查parent的邻接表是不是cur的邻接表的超集
            Set<Integer> neighParent = g.get(parent);
            for(int tmp : neigh){
                if(tmp == parent) continue;
                if(!neighParent.contains(tmp)) return 0;
            }
            if(neighParent.size() == neigh.size()) res = 2;
        }
        return res;
    }
}
