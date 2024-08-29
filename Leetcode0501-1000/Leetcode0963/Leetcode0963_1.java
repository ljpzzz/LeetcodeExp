package Leetcode0963;

public class Leetcode0963_1 {
    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        double ans = Long.MAX_VALUE;
        for(int i = 0; i < n; i++){
            int x1 = points[i][0], y1 = points[i][1];
            for(int j = 0; j < n; j++){
                if(i == j) continue;
                int x2 = points[j][0], y2 = points[j][1];
                for(int k = 0; k < n; k++){
                    if(i == k || j == k) continue;
                    int x3 = points[k][0], y3 = points[k][1];
                    for(int l = 0; l < n; l++){
                        if(i == l || j == l || k == l) continue;
                        int x4 = points[l][0], y4 = points[l][1];
                        if(x1+x3 != x2+x4 || y1+y3 != y2+y4) continue; //对角线互相平分
                        long distance12 = 1L*(x1-x2)*(x1-x2)+1L*(y1-y2)*(y1-y2);
                        long distance23 = 1L*(x2-x3)*(x2-x3)+1L*(y2-y3)*(y2-y3);
                        long distance13 = 1L*(x1-x3)*(x1-x3)+1L*(y1-y3)*(y1-y3);
                        if(distance12 + distance23 == distance13){
                            ans = Math.min(ans, Math.sqrt(distance12*distance23));
                        }
                    }
                }
            }
        }
        return ans < Long.MAX_VALUE ? ans : 0;
    }
    public static void main(String[] args) {
        int[][] points = {{1,2},{2,1},{1,0},{0,1}};
        System.out.println(new Leetcode0963_1().minAreaFreeRect(points));
    }
}
