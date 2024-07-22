package Leetcode0815;

import java.util.*;

public class Leetcode0815_1 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;
        Map<Integer, List<Integer>> routeBusMap = new HashMap<>();
        int n = routes.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                routeBusMap.computeIfAbsent(routes[i][j], k -> new ArrayList<>()).add(i);
            }
        }
        Set<Integer> visRoute = new HashSet<>();
        visRoute.add(source);
        Set<Integer> visBus = new HashSet<>();
        //int[]包含3个值，route, bus, step
        Deque<int[]> queue = new ArrayDeque<>();
        if(!routeBusMap.containsKey(source) || !routeBusMap.containsKey(target)) return -1;
        for(int bus : routeBusMap.get(source)) {
            queue.add(new int[]{source, bus, 1});
            visBus.add(bus);
        }
        while(queue.size() > 0){
            int[] tmp = queue.poll();
            int route = tmp[0];
            int bus = tmp[1];
            int step = tmp[2];
            if(route == target) return step;
            //先找当前bus的其他站点
            for(int nextRoute : routes[bus]) {
                if(visRoute.contains(nextRoute)) continue;
                queue.addFirst(new int[]{nextRoute, bus, step});
                visRoute.add(nextRoute);
            }
            //再找当前route的其他bus
            for(int nextBus : routeBusMap.get(route)) {
                if(visBus.contains(nextBus)) continue;
                queue.addLast(new int[]{route, nextBus, step + 1});
                visBus.add(nextBus);
            }
        }
        return -1;
    }
}
