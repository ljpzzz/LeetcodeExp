package Leetcode0858;

public class Leetcode0858_1 {
    public int mirrorReflection(int p, int q) {
        int x = 0;
        int y = 0;
        boolean isUp = true;
        while(true){
            if(x == p && y == 0) return 0;
            if(x == p && y == p) return 1;
            if(x == 0 && y == p) return 2;
            x = (x+p)%(2*p);
            if(isUp){
                y = y+q;
                if(y > p) {
                    y = 2*p-y;
                    isUp = false;
                }
            }
            else{
                y = y-q;
                if(y < 0){
                    y = -y;
                    isUp = true;
                }
            }
        }
    }
}
