package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphInit {
    /*节点[0,n-1]，无权重 + 入度*/
    public void init1(int n, int[][] edges){
        int[] degrees = new int[n];
        List<Integer>[] g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
            degrees[x]++;
            degrees[y]++;
        }
    }
    /*节点[1,n]，无权重 + 入度*/
    public void init2(int n, int[][] edges){
        int[] degrees = new int[n+1];
        List<Integer>[] g = new List[n+1];
        for(int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
            degrees[x]++;
            degrees[y]++;
        }
    }
    /*节点[0,n-1]，带权重 + 入度*/
    public void init3(int n, int[][] edges){
        int[] degrees = new int[n];
        List<int[]>[] g = new List[n];
        for(int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            g[x].add(new int[]{y, w});
            g[y].add(new int[]{x, w});
            degrees[x]++;
            degrees[y]++;
        }
    }
    /*节点[1,n]，带权重*/
    public void init4(int n, int[][] edges){
        int[] degrees = new int[n+1];
        List<int[]>[] g = new List[n+1];
        for(int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            g[x].add(new int[]{y, w});
            g[y].add(new int[]{x, w});
            degrees[x]++;
            degrees[y]++;
        }
    }
    /*节点[0,n-1]，带重边权重去重*/
    public void init5(int n, int[][] edges){
        Map<Integer, Integer>[] g = new Map[n];
        for(int i = 0; i < n; i++) g[i] = new HashMap<>();
        for(int[] edge : edges){
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            if(g[x].get(y) == null || g[x].get(y) > w) g[x].put(y, w);
            if(g[y].get(x) == null || g[y].get(x) > w) g[y].put(x, w);
        }
    }
}
