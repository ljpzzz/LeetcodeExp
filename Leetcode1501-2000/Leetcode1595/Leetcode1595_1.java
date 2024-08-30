package Leetcode1595;

import java.util.*;

public class Leetcode1595_1 {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        int source = 0;
        int dest = m + n + 1;
        int sup_source = m + n + 2;
        int sup_dest = m + n + 3;
        MinCostMaxFlowDinic mfd = new MinCostMaxFlowDinic(m + n + 4);
        for(int i = 1; i <= m; i++) {
            mfd.addEdge(source, i, n-1, 0);
            mfd.addEdge(sup_source, i, 1, 0);
        }
        for(int i = m+1; i <= m+n; i++) {
            mfd.addEdge(i, dest, m-1, 0);
            mfd.addEdge(i, sup_dest, 1, 0);
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                mfd.addEdge(i+1, m+j+1, 1, cost.get(i).get(j));
            }
        }
        mfd.addEdge(dest, sup_source, n, 0);
        mfd.addEdge(source, sup_dest, m-n, 0);
        mfd.addEdge(sup_dest, source, n, 0);
        mfd.addEdge(dest, source, m*n-n, 0);
        mfd.addEdge(source, dest , n, 0);
        long[] ans = mfd.minCostMaxFlow(sup_source, sup_dest);
        return (int)ans[1];

    }
    class Edge {
        int t, rev;
        long f, cost;

        public Edge(int t, int rev, long f, long cost) {
            this.t = t;
            this.rev = rev;
            this.f = f;
            this.cost = cost;
        }
    }
    class MinCostMaxFlowDinic {
        int N ; //节点数
        int M; //边的数量
        long INF = Long.MAX_VALUE/2;
        List<Edge>[] g;
        long[] dis; // 存储每个点的距离
        boolean[] vis;
        public long ret; //最小费用
        int[] ptr; //当前弧
        public MinCostMaxFlowDinic(int N) {
            g = new List[N];
            for (int i = 0; i < N; i++) g[i] = new ArrayList<>();
            dis = new long[N]; // 存储每个点的距离
            vis = new boolean[N];
            ret = 0;
            ptr = new int[N];
        }
        public void addEdge(int u, int v, int w, int c) {
            g[u].add(new Edge(v, g[v].size(), w, c));
            g[v].add(new Edge(u, g[u].size() - 1, 0, -c));
        }
        boolean spfa(int src, int dest) {
            Arrays.fill(vis, false);
            Arrays.fill(dis, INF);
            dis[src] = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(src);
            vis[src] = true;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                vis[u] = false;
                for (Edge e : g[u]) {
                    if (e.f > 0 && dis[e.t] > dis[u] + e.cost) {
                        dis[e.t] = dis[u] + e.cost;
                        if (!vis[e.t]) {
                            queue.push(e.t);
                            vis[e.t] = true;
                        }
                    }
                }
            }
            return dis[dest] != INF;
        }
        long dinicDfs(int u, int t, long flow) {
            if (u == t) return flow;
            vis[u] = true;
            long ans = 0;
            for (; ptr[u] < g[u].size() && ans < flow; ptr[u]++) {
                Edge e = g[u].get(ptr[u]);
                if (!vis[e.t] && e.f > 0 && dis[e.t] == dis[u] + e.cost) {
                    long x = dinicDfs(e.t, t, Math.min(e.f, flow - ans));
                    if (x> 0) {
                        ret += x * e.cost;
                        e.f -= x;
                        g[e.t].get(e.rev).f += x;
                        ans += x;
                    }
                }
            }
            //vis[u] = false;
            return ans;
        }

        public long[] minCostMaxFlow(int src, int dest) {
            long flow = 0;
            while (spfa(src, dest)) {
                ptr = new int[g.length];
                while (true) {
                    long df = dinicDfs(src, dest,0x3f3f3f3f);
                    if (df == 0) break;
                    flow += df;
                }
            }
            return new long[]{flow, ret};
        }
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode1595_1().connectTwoGroups(Arrays.asList(Arrays.asList(15, 96), Arrays.asList(36,2))));
    }
}
