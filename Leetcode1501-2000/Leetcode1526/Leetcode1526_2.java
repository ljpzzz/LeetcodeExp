package Leetcode1526;

public class Leetcode1526_2 {
    public int minNumberOperations(int[] target) {
        int n = target.length;
        int op = target[0];
        for(int i = 1; i < n; i++){
            if(target[i] <= target[i-1]) continue;
            else op += target[i]-target[i-1];
        }
        return op;
    }
}
