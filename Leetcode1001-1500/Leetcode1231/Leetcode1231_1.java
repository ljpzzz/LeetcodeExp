package Leetcode1231;

public class Leetcode1231_1 {
    int n;
    int m;
    public int maximizeSweetness(int[] sweetness, int k) {
        n = sweetness.length;
        m = k+1;
        int min = sweetness[0];
        int sum = 0;
        for(int s : sweetness){
            min = Math.min(min, s);
            sum += s;
        }
        int left = min;
        int right = sum/m;
        int ans = min;
        while(left <= right){
            int mid = left + (right-left)/2;
            if(isOK(sweetness, mid)){
                ans = mid;
                left = mid+1;
            }
            else right = mid-1;
        }
        return ans;

    }
    boolean isOK(int[] sweetness, int mid){
        int curSum = 0;
        int grpCnt = 0;
        for(int i = 0; i < n; i++){
            curSum += sweetness[i];
            if(curSum >= mid){
                grpCnt++;
                curSum = 0;
            }
        }
        return grpCnt >= m;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode1231_1().maximizeSweetness(new int[]{1,2,3,4,5,6,7,8,9},5));
        System.out.println(new Leetcode1231_1().maximizeSweetness(new int[]{5,6,7,8,9,1,2,3,4},8));
        System.out.println(new Leetcode1231_1().maximizeSweetness(new int[]{1,2,2,1,2,2,1,2,2},2));
    }
}
