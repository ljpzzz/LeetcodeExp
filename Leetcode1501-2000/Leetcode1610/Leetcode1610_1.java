package Leetcode1610;

import java.util.*;

public class Leetcode1610_1 {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int sx = location.get(0);
        int sy = location.get(1);
        int ans = 0;
        List<Double> angles = new ArrayList<>();
        for(List<Integer> p: points){
            int x = p.get(0);
            int y = p.get(1);
            if(x == sx && y == sy) ans++;
            else{
                double theta = Math.atan2(y - sy, x - sx);
                theta = theta * 180 / Math.PI;
                if(theta < 0) theta += 360;
                angles.add(theta);
                angles.add(theta + 360);
            }
        }
        Collections.sort(angles);
        TreeMap<Double, Integer> angleInxMap = new TreeMap<>();
        for(int i = 0; i < angles.size(); i++) angleInxMap.put(angles.get(i), i);
        int maxAns = 0;
        for(int i = 0; i < angles.size(); i++){
            double ang = angles.get(i);
            Double max = angleInxMap.floorKey(ang + angle);
            if(max != null) maxAns = Math.max(maxAns, angleInxMap.get(max) - i + 1);
        }
        return ans + maxAns;
    }
    public static void main(String[] args) {
        Leetcode1610_1 leetcode1610_1 = new Leetcode1610_1();
        List<List<Integer>> points = new ArrayList<>();
        points.add(new ArrayList<>(Arrays.asList(1, 1)));
        points.add(new ArrayList<>(Arrays.asList(2, 2)));
        points.add(new ArrayList<>(Arrays.asList(3, 3)));
        points.add(new ArrayList<>(Arrays.asList(4, 4)));
        points.add(new ArrayList<>(Arrays.asList(1, 2)));
        points.add(new ArrayList<>(Arrays.asList(2, 1)));
        System.out.println(leetcode1610_1.visiblePoints(points, 0, new ArrayList<>(Arrays.asList(1, 1))));
    }
}
