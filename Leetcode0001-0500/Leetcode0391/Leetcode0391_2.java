package Leetcode0391;

import java.util.*;

public class Leetcode0391_2 {
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        //每条矩阵2条竖边， 用(x, yDown, yUp, status)表示， status为1表示左竖边，-1表示右竖边
        int[][] edges = new int[n*2][4];
        int index = 0;
        for(int i = 0; i < n; i++){
            int[] rect = rectangles[i];
            edges[index++] = new int[]{rect[0], rect[1], rect[3], 1};
            edges[index++] = new int[]{rect[2], rect[1], rect[3], -1};
        }
        //对竖边数组排序，先按x排序，再按y排序
        Arrays.sort(edges, (a, b)->{
            if(a[0] != b[0]) return a[0]-b[0];
            else return a[1]-b[1];
        });
        List<int[]> leftEdgeList = new ArrayList<>();
        List<int[]> rightEdgeList = new ArrayList<>();
        for(int i = 0; i < n*2;){
            leftEdgeList.clear();
            rightEdgeList.clear();
            int j = i;
            //找到所有x相同的竖边
            while(j < 2*n && edges[j][0] == edges[i][0]) j++;
            for(int t = i; t < j; t++){
                int[] curEdge = new int[]{edges[t][1], edges[t][2]};
                List<int[]> curList = edges[t][3] == 1? leftEdgeList:rightEdgeList;
                if(curList.size() == 0) curList.add(curEdge);
                else{
                    int[] prevEdge = curList.get(curList.size()-1);
                    if(prevEdge[1] > curEdge[0]) return false;
                        //首尾相连，则将两条线段连起来
                    else if(prevEdge[1] == curEdge[0]) prevEdge[1] = curEdge[1];
                    else curList.add(curEdge);
                }
            }
            //横坐标相同的的所有竖边都已经处理完，检查是否合法
            if(i == 0 || j == n*2){
                if(leftEdgeList.size() + rightEdgeList.size() != 1) return false;
            }
            else{
                if(leftEdgeList.size() != rightEdgeList.size()) return false;
                for(int t = 0; t < leftEdgeList.size(); t++){
                    int[] lEdge = leftEdgeList.get(t);
                    int[] rEdge = rightEdgeList.get(t);
                    if(lEdge[0] != rEdge[0] || lEdge[1] != rEdge[1]) return false;
                }
            }
            //从下一个横坐标开始处理
            i = j;
        }
        return true;
    }
}
