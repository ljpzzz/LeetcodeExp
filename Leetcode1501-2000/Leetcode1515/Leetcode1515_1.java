package Leetcode1515;

import java.util.*;

public class Leetcode1515_1 {
    public double getMinDistSum(int[][] positions) {
        double eplison = 1e-15; //停止条件
        double alpha = 1.0d; //梯度下降的步长
        double decay = 1e-3; //步长的衰减
        int n = positions.length;
        int batchSize = n*10;
        //初始化梯度下降的初始化x,y
        double x = 0.0d;
        double y = 0.0d;
        for(int i = 0; i < n; i++){
            x += positions[i][0];
            y += positions[i][1];
        }
        x /= n;
        y /= n;

        while(true){
            shuffle(positions);
            double xP = x;
            double yP = y;
            for(int i = 0; i < n; i++){
                //梯度累加
                double dx = 0.0d;
                double dy = 0.0d;
                for(int k = 0; k < n; k++){
                    int curX = positions[k][0];
                    int curY = positions[k][1];
                    //加一个eplison防止0做除数
                    dx += (x-curX)/Math.sqrt((x-curX)*(x-curX) + (y-curY)*(y-curY) + eplison);
                    dy += (y-curY)/Math.sqrt((x-curX)*(x-curX) + (y-curY)*(y-curY) + eplison);
                }
                //梯度下降
                x -= alpha*dx;
                y -= alpha*dy;

                //减少步长
                alpha *= (1-decay);
            }
            //判断是否满足终止条件
            if(Math.sqrt((x-xP)*(x-xP) + (y-yP)*(y-yP)) < eplison) break;
        }
        return calcDistAll(positions, x, y);
    }
    public void shuffle(int[][] p){
        Random random = new Random();
        for(int i = 0; i < p.length; i++){
            int x = p[i][0];
            int y = p[i][1];
            int swapIndex = random.nextInt(p.length);
            p[i][0] = p[swapIndex][0];
            p[i][1] = p[swapIndex][1];
            p[swapIndex][0] = x;
            p[swapIndex][1] = y;
        }
    }
    public double calcDistAll(int[][] p, double x, double y){
        double res = 0.0d;
        for(int i = 0; i < p.length; i++){
            res += Math.sqrt((x-p[i][0])*(x-p[i][0]) + (y-p[i][1])*(y-p[i][1]));
        }
        return res;
    }
}
