package Leetcode0799;

import java.util.*;

public class Leetcode0799_1 {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] amount = new double[query_row + 1][query_row + 1];
        amount[0][0] = poured;
        for(int x = 0; x <= query_row; x++){
            for(int y = 0; y <= x; y++){
                if(x == query_row && y == query_glass) return amount[x][y] > 1 ? 1 : amount[x][y];
                if(amount[x][y] > 1){
                    double delta = (amount[x][y] - 1) / 2;
                    if(x+1 <= query_row){
                        amount[x+1][y] += delta;
                        amount[x+1][y+1] += delta;
                    }
                }
            }
        }
        return 0.0d;
    }
}
