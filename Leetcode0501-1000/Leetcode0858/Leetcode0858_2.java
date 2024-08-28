package Leetcode0858;

public class Leetcode0858_2 {
    public int mirrorReflection(int p, int q) {
        if(q == 0) return 0;
        if(q == p) return 1;
        int g = gcd(p,q);
        int s = p*q/g;
        int upDownTimes = s/q;
        int leftRightTimes = s/p;

        if(upDownTimes%2 == 0) return 2;
        else if(leftRightTimes%2 == 0) return 0;
        else return 1;

    }
    private int gcd(int x, int y){
        int pivot=x%y;
        while(pivot > 0){
            x = y;
            y =pivot;
            pivot = x%y;
        }
        return y;
    }
}
