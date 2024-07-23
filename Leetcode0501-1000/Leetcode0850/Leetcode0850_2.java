package Leetcode0850;

import java.util.*;

public class Leetcode0850_2 {
    public int rectangleArea(int[][] rectangles) {
        int MOD = 1000000007;
        int n = rectangles.length;
        //记录所有矩阵y坐标的位置
        Set<Integer> yPos = new HashSet<>();
        for(int[] rec : rectangles){
            yPos.add(rec[1]);
            yPos.add(rec[3]);
        }
        List<Integer> yPosList = new ArrayList<>(yPos);
        //将所有的y坐标从小到大排序
        Collections.sort(yPosList);
        // y坐标一共m个点， 一共m-1个线段，第i个线段长度是yPosList.get(i+1)-yPosList.get(i)
        int m = yPosList.size();
        //记录m-1条y线段在当前的x扫描时，是否有矩阵覆盖，没有为0， 有x个矩阵覆盖则为x
        int[] segCount = new int[m-1];
        //记录需要水平扫的x坐标, int[]是3个值的数组， 第一个是x坐标，第二个是矩阵编号，
        //第三个是标志位, 矩阵左边则+1, 右边则-1,和日程表预定类似
        List<int[]> sweepList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            sweepList.add(new int[]{rectangles[i][0], i, 1});
            sweepList.add(new int[]{rectangles[i][2], i, -1});
        }
        //对扫的列表排序，首先基于x坐标从小到大，再基于矩阵序号从小到大，最后先左边，再右边
        Collections.sort(sweepList, (a,b)->{
            if(a[0] != b[0]) return a[0]-b[0];
            else if(a[1] != b[1]) return a[1]-b[1];
            else return a[2]-b[2];

        });
        long res = 0L;
        for(int i = 0; i < sweepList.size(); i++){
            int j = i;
            while(j+1 < sweepList.size() && sweepList.get(j+1)[0] == sweepList.get(j)[0]) j++;
            if(j+1 == sweepList.size()) break;
            //[i,j]的横坐标都相同，一起处理
            for(int k = i; k <= j; k++){
                int[] cur = sweepList.get(k);
                int recIndex = cur[1];
                long yDown = rectangles[recIndex][1];
                long yUp = rectangles[recIndex][3];
                for(int p = 0; p < m-1; p++){
                    long yCurDown = yPosList.get(p);
                    long yCurUp = yPosList.get(p+1);
                    //[yCurDown, yCurUp]为最小划分的区间，如果在当前矩阵的区间[yDown, yUp]之内，
                    //则更新计数, 是矩阵左边则计数+1， 是矩阵右边则计数-1
                    if(yCurDown >= yDown && yCurUp <= yUp) segCount[p] += cur[2];                }
            }
            //计算扫描到的面积
            for(int p = 0; p < m-1; p++){
                long yCurDown = yPosList.get(p);
                long yCurUp = yPosList.get(p+1);
                if(segCount[p] > 0) {
                    res += (yCurUp-yCurDown)*(sweepList.get(j+1)[0]-sweepList.get(j)[0]);
                    res %= MOD;
                }
            }
            //每次从下一个不同的x开始遍历
            i = j;
        }
        return (int)(res%MOD);
    }
}
