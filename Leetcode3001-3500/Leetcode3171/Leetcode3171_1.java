package Leetcode3171;

public class Leetcode3171_1 {
    int LEN = 30;
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int[] cnt1 = new int[LEN];
        int left = 0;
        int right = 0;
        int ans = Math.abs(nums[0]-k);
        while(right < n){
            String rightStr = getNumStr(nums[right]);
            int orVal = 0;
            for(int i = 0; i < LEN; i++) {
                cnt1[i] += rightStr.charAt(i) == '1'? 1 : 0;
                if(cnt1[i] > 0) orVal |= (1<<(LEN-1-i));
            }
            ans = Math.min(ans, Math.abs(orVal-k));
            while(orVal > k){
                String leftStr = getNumStr(nums[left]);
                orVal = 0;
                for(int i = 0; i < LEN; i++) {
                    cnt1[i] -= leftStr.charAt(i) == '1'? 1 : 0;
                    if(cnt1[i] > 0) orVal |= (1<<(LEN-1-i));
                }
                if(orVal > 0) ans = Math.min(ans, Math.abs(orVal-k));
                left++;
            }
            if(orVal > 0) ans = Math.min(ans, Math.abs(orVal-k));
            right++;
        }
        return ans;
    }
    public String getNumStr(int num){
        String numStr = Integer.toBinaryString(num);
        int numLen = numStr.length();
        while(numLen < LEN) {
            numStr = "0" + numStr;
            numLen++;
        }
        return numStr;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode3171_1().minimumDifference(new int[]{6}, 2));
    }
}
