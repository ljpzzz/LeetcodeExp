package Leetcode1095;

class MountainArray {
      int[] arr = {3,5,3,2,0};
      public int get(int index){
          return arr[index];
      }
      public int length(){
          return arr.length;
      }
}

public class Leetcode1095_1 {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        return search(target, mountainArr, 0, len - 1, len);
    }
    public int search(int target, MountainArray mountainArr, int start, int end, int len){
        int left = start, right = end;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int val = mountainArr.get(mid);
            if(val == target) {
                int ans1 = search(target, mountainArr, left, mid - 1, len);
                if(ans1 != -1) return ans1;
                return mid;
            }
            if(mid == right) return -1;
            int val2 = mountainArr.get(mid + 1);
            if(target > val){
                if(val < val2) left = mid + 1;
                else right = mid - 1;
            }
            else{
                if(val < val2){
                    int ans1 = search(target, mountainArr, left, mid - 1, len);
                    if(ans1 != -1) return ans1;
                    else return search(target, mountainArr, mid + 1, right, len);
                }
                else{
                    int ans1 = search(target, mountainArr, left, mid - 1, len);
                    if(ans1 != -1) return ans1;
                    else return search(target, mountainArr, mid + 1, right, len);
                }
            }
        }
        return -1;
    }
    public static void main(String[] args)
    {
        MountainArray mountainArr = new MountainArray();
        System.out.println(new Leetcode1095_1().findInMountainArray(3, mountainArr));
    }
}
