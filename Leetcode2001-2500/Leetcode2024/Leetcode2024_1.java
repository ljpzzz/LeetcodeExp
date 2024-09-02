package Leetcode2024;

public class Leetcode2024_1 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int ans1 = getAns(answerKey, k, 'T');
        int ans2 = getAns(answerKey, k, 'F');
        return Math.max(ans1, ans2);
    }
    public int getAns(String s, int k, char val){
        int n = s.length();
        int left = 0;
        int right = 0;
        int ans = 0;
        int changeCnt = 0;
        while(right < n){
            char c = s.charAt(right);
            if(c != val) changeCnt++;
            while(changeCnt > k){
                ans = Math.max(ans, right - left);
                char leftC = s.charAt(left);
                if(leftC != val) changeCnt--;
                left++;
            }
            right++;
        }
        ans = Math.max(ans, right - left);
        return ans;
    }
    public static void main(String[] args) {
        Leetcode2024_1 l = new Leetcode2024_1();
        String answerKey = "TFFT";
        int k = 1;
        System.out.println(l.maxConsecutiveAnswers(answerKey, k));
    }
}
