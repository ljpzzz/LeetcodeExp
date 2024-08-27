package Leetcode0835;

public class Leetcode0835_1 {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int ans = 0;
        //水平移动y个单位，正则右，负则左，0不移动
        for(int y = -n+1; y < n; y++){
            //垂直移动x个单位，正则下，负则上，0不移动
            for(int x = -n+1; x < n; x++){
                int count = 0;
                for(int i = 0; i < n; i++){
                    for(int j = 0; j < n; j++){
                        int x2 = i+x;
                        int y2 = j+y;
                        if(x2 >= 0 && x2 < n && y2 >= 0 && y2 < n && img1[i][j] == 1 && img2[x2][y2] == 1) count++;
                    }
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
