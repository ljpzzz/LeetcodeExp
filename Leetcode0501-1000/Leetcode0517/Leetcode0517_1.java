package Leetcode0517;

public class Leetcode0517_1 {
    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0;
        for (int i = 0; i < n; i++) sum += machines[i];
        if(sum % n != 0) return -1;
        int avg = sum / n;
        int ans = 0;
        for(int i = 0; i < n; i++) {
            machines[i] -= avg;
            if(machines[i] > 0) ans = Math.max(ans, machines[i]);
        }

        int move = 0;
        for(int i = 0; i < n; i++) {
            move += machines[i];
            if(move > 0) ans = Math.max(ans, move);
        }
        move = 0;
        for(int i = n - 1; i >= 0; i--) {
            move += machines[i];
            if(move > 0) ans = Math.max(ans, move);
        }
        return ans;
    }
}
