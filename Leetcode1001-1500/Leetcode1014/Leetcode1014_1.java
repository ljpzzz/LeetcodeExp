package Leetcode1014;

public class Leetcode1014_1 {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int max = values[0];
        int ans = 0;
        for(int i = 1; i < n; i++){
            ans = Math.max(ans, max + values[i] - i);
            max = Math.max(max, values[i] + i);
        }
        return ans;
    }
}
