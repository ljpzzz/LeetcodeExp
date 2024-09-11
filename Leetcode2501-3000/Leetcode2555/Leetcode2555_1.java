package Leetcode2555;

import java.util.*;

public class Leetcode2555_1  {
    public int maximizeWin(int[] prizePositions, int k) {
        TreeMap<Integer, Integer> posCntMap = new TreeMap<>();
        for(int pos : prizePositions) posCntMap.put(pos, posCntMap.getOrDefault(pos, 0)+1);
        TreeMap<Integer, Integer> posSumMap = new TreeMap<>();
        int sum = 0;
        for(int pos : posCntMap.keySet()){
            sum += posCntMap.get(pos);
            posSumMap.put(pos, sum);
        }
        List<Integer> posList = new ArrayList<>(posSumMap.keySet());
        Collections.sort(posList);
        int n = posList.size();
        posSumMap.put(-0x3f3f3f3f, 0); //哨兵

        Map<Integer, Integer> leftK = new HashMap<>();
        Map<Integer, Integer> rightK = new HashMap<>();
        for(int pos : posList){
            int posSum = posSumMap.get(pos);
            Integer prePos = posSumMap.lowerKey(pos-k);
            int prePosSum = posSumMap.get(prePos);
            leftK.put(pos, posSum-prePosSum);
            Integer postPos = posSumMap.floorKey(pos+k);
            int postPosSum = posSumMap.get(postPos);
            Integer posPlus1 = posSumMap.lowerKey(pos);
            rightK.put(pos, postPosSum-posSumMap.get(posPlus1));
        }
        int[] leftMax = new int[n];
        leftMax[0] = leftK.get(posList.get(0));
        for(int i = 1; i < n; i++) leftMax[i] = Math.max(leftMax[i-1], leftK.get(posList.get(i)));

        int[] rightMax = new int[n];
        rightMax[n-1] = rightK.get(posList.get(n-1));
        for(int i = n-2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i+1], rightK.get(posList.get(i)));

        int ans = 0;
        for(int i = 0; i < n; i++){
            int left = i > 0 ? leftMax[i-1] : 0;
            int right = rightMax[i];
            ans = Math.max(ans, left+right);
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,2,2,3,3,5};
        int k = 2;
        Leetcode2555_1 leetcode2555_1 = new Leetcode2555_1();
        System.out.println(leetcode2555_1.maximizeWin(nums, k));
    }
}
