package Leetcode0754;

public class Leetcode0754_1 {
    public int reachNumber(int target) {
        long t = Math.abs(1L*target);
        long step = ((int)Math.sqrt(1+8*t)-1)/2;
        if(step*(step+1) == 2*t) return (int)step;
        long delta = (step+2)*(step+1)/2 - t;
        if(delta%2 == 0) return (int)(step+1);
        else if((step+1)%2 == 0) return (int)(step+2);
        else return (int)(step+3);
    }
    public static void main(String[] args) {
        Leetcode0754_1 leetcode0754_1 = new Leetcode0754_1();
        System.out.println(leetcode0754_1.reachNumber(2));
    }
}
