package Leetcode0963;

import java.util.*;

public class Leetcode0963_2 {
    public double minAreaFreeRect(int[][] points) {
        double minSqure = Double.MAX_VALUE;
        //对坐标做压缩，压缩到一维
        Set<Long> pointSet = new HashSet<>();
        for(int[] point : points) pointSet.add(100000L*point[0] + point[1]);
        for(int i = 0; i < points.length-2; i++){
            for(int j = i+1; j < points.length-1; j++){
                int len2ij = (points[i][0]-points[j][0])*(points[i][0]-points[j][0]) +
                        (points[i][1]-points[j][1])*(points[i][1]-points[j][1]);
                for(int k = j+1; k < points.length; k++){
                    //检查i,j,k这三个点是否可以成直角三角形
                    int len2ik = (points[i][0]-points[k][0])*(points[i][0]-points[k][0]) +
                            (points[i][1]-points[k][1])*(points[i][1]-points[k][1]);
                    int len2jk = (points[j][0]-points[k][0])*(points[j][0]-points[k][0]) +
                            (points[j][1]-points[k][1])*(points[j][1]-points[k][1]);
                    //此时i，j为对角线
                    if(len2ij == len2ik + len2jk){
                        int lastX = points[i][0] + points[j][0]-points[k][0];
                        int lastY = points[i][1] + points[j][1]-points[k][1];
                        //如果存在第四点，则确认是否最小
                        if(pointSet.contains(100000L*lastX + lastY)){
                            double square = Math.sqrt(len2ik)*Math.sqrt(len2jk);
                            minSqure = Math.min(minSqure, square);
                        }
                    }
                    //此时i,k为对角线
                    else if(len2ik == len2ij + len2jk){
                        int lastX = points[i][0] + points[k][0]-points[j][0];
                        int lastY = points[i][1] + points[k][1]-points[j][1];
                        //如果存在第四点，则确认是否最小
                        if(pointSet.contains(100000L*lastX + lastY)){
                            double square = Math.sqrt(len2ij)*Math.sqrt(len2jk);
                            minSqure = Math.min(minSqure, square);
                        }
                    }
                    //此时j,k为对角线
                    else if(len2jk == len2ij + len2ik){
                        int lastX = points[j][0] + points[k][0]-points[i][0];
                        int lastY = points[j][1] + points[k][1]-points[i][1];
                        //如果存在第四点，则确认是否最小
                        if(pointSet.contains(100000L*lastX + lastY)){
                            double square = Math.sqrt(len2ij)*Math.sqrt(len2ik);
                            minSqure = Math.min(minSqure, square);
                        }
                    }
                }
            }
        }
        if(minSqure == Double.MAX_VALUE) return 0d;
        else return minSqure;
    }
}
