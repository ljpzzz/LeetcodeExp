package Leetcode0587;

import java.util.Arrays;

public class Leetcode0587_1 {
    public int[][] outerTrees(int[][] trees) {
        Arrays.sort(trees, (a, b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            else return a[1]-b[1];
        });
        int n = trees.length;
        boolean[] visited = new boolean[n];//标记已经在上凸壳中用过的点, 首节点除外，下凸壳还要用它
        int[] stack = new int[n+10];
        int top = 0;
        stack[top++] = 0; //首节点入栈，上凸壳起点
        //遍历上凸壳
        for(int i=1;i<n;i++){
            while(top > 1 && isClockWise(trees[stack[top-2]], trees[stack[top-1]], trees[i]) > 0){
                visited[stack[top-1]] = false;
                top--; //栈顶出栈
            }
            visited[i] = true;
            stack[top++] = i;
        }
        int upperCount = top; //上凸壳节点数量
        //遍历下凸壳
        for(int i = n-1; i>=0; i--){
            if(visited[i]) continue;
            while(top > upperCount && isClockWise(trees[stack[top-2]], trees[stack[top-1]], trees[i]) > 0){ //不能弹上凸壳的节点
                visited[stack[top-1]] = false;
                top--;
            }
            visited[i] = true;
            stack[top++] = i;
        }
        int[][] ans = new int[top-1][2]; //首节点只需要计算一次
        for(int i = 0; i < top-1; i++) ans[i] = trees[stack[i]];
        return ans;
    }
    // a为栈里倒数第二个节点
    // b为栈顶节点
    // c为当前待判断节点
    //小于0，则ac在ab的顺时针方向
    //等于0, 则ac和ab同方向
    //大于0，则ac在ab的逆时针方向
    private int isClockWise(int[] a, int[] b, int[] c){
        int[] vectorAB = new int[]{b[0]-a[0], b[1]-a[1]};
        int[] vectorAC = new int[]{c[0]-a[0], c[1]-a[1]};
        return vectorAB[0]*vectorAC[1]-vectorAB[1]*vectorAC[0];
    }
}
