package Leetcode0218;

import java.util.*;

public class Leetcode0218_1 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = buildings.length;
        //按序号从小到大，序号一样，按开始0结束1标志排序,开始结束标志一样，按高度从小到大排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->{
            if(a[0] != b[0]) return a[0] - b[0];
            else if(a[1] != b[1]) return a[1] - b[1];
            else return a[2] - b[2];
        });
        for(int i = 0; i < n; i++)
        {
            pq.offer(new int[]{buildings[i][0], 0, buildings[i][2]});
            pq.offer(new int[]{buildings[i][1], 1, buildings[i][2]});
        }
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        int preIndex = -1;
        int preHeight = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int index = cur[0];
            int status = cur[1];
            int height = cur[2];
            if(status == 0)
            {
                heightMap.put(height, heightMap.getOrDefault(height, 0) + 1);
                if(height <= preHeight) continue; //不是新的最大值
                if(index == preIndex) preHeight = height; //还是当前索引，仅仅更新高度
                else{ //新的索引
                    if(preIndex == -1){ //第一个索引
                        preHeight = height;
                        preIndex = index;
                    }
                    else { //不是第一个，尝试更新结果
                        ans.add(Arrays.asList(preIndex, preHeight));
                        preHeight = height;
                        preIndex = index;
                    }
                }
            }
            else
            {
                heightMap.put(height, heightMap.getOrDefault(height, 0) - 1);
                if(heightMap.get(height) > 0) continue; //当前值不止一个
                heightMap.remove(height); //只有一个，删除
                if(height < preHeight) continue; //删除的不是最大值

                //删除的是唯一最大值，如果是当前索引，尝试更新高度
                if(index == preIndex) preHeight = heightMap.isEmpty() ? 0 : heightMap.lastKey();
                else{
                    ans.add(Arrays.asList(preIndex, preHeight));
                    preIndex = index;
                    preHeight = heightMap.isEmpty() ? 0 : heightMap.lastKey();
                }
            }
        }
        ans.add(Arrays.asList(preIndex, 0));
        return ans;
    }
    public static void main(String[] args)
    {
        //int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        //int[][] buildings = {{4,9,10},{4,9,15},{4,9,12},{10,12,10},{10,12,8}};
        int[][] buildings = {{1,5,3},{1,5,3}};
        Leetcode0218_1 leetcode0218_1 = new Leetcode0218_1();
        List<List<Integer>> ans = leetcode0218_1.getSkyline(buildings);
        for(List<Integer> list : ans)
        {
            System.out.println(list.get(0) + " " + list.get(1));
        }
    }
}
