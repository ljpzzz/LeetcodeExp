package Leetcode0774;

public class Leetcode0774_1 {
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        double left = 0.0d;
        double right = stations[n-1]-stations[0];
        double ans = -1;
        while(right-left > 1e-7){
            double mid = left + (right-left)/2;
            if(isOK(stations, k, mid)){
                ans = mid;
                right = mid;
            }
            else left = mid;
        }
        return ans;
    }
    public boolean isOK(int[] stations, int k, double mid){
        int n = stations.length;
        for(int i = 1; i < n; i++){
            int distance = stations[i]-stations[i-1];
            if(distance <= mid) continue;
            int need = (int)Math.ceil(distance/mid-1);
            if(need > k) return false;
            k -= need;
        }
        return true;
    }
    public static void main(String args[]){
        System.out.println(new Leetcode0774_1().minmaxGasDist(new int[]{1,2,3,4,5,6,7,8,9,10},9));
        System.out.println(new Leetcode0774_1().minmaxGasDist(new int[]{23,24,36,39,46,56,57,65,84,98},1));
    }
}
