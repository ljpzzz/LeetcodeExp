package Leetcode1526;

public class Leetcode1526_1 {
    public int minNumberOperations(int[] target) {
        int n = target.length;
        int ans = 0;
        int base = 0;
        int index = 0;
        while(index < n){
            //寻找峰值
            while(index+1 < n && target[index] <= target[index+1]) index++;
            ans += target[index] - base;
            //寻找谷底
            while(index+1 < n && target[index] >= target[index+1]) index++;
            base = target[index];
            index++;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] target = {1,2,3,2,1};
        Leetcode1526_1 leetcode1526_1 = new Leetcode1526_1();
        System.out.println(leetcode1526_1.minNumberOperations(target));
    }
}
