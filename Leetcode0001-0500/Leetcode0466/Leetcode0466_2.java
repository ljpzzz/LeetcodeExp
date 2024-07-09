package Leetcode0466;

public class Leetcode0466_2 {
    //dp
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length();
        int len2 = s2.length();
        //dpCnt[i]表示从s2的位置i开始匹配，匹配到一个完整的s1时，已经匹配s2末尾的次数
        int[] dpCnt = new int[len2];
        //dpPos[i]表示从s2的位置i开始匹配，匹配到一个完整的s1时，s2的下一个位置
        int[] dpPos = new int[len2];
        for (int i = 0; i < len2; i++) {
            int j = i;
            int cnt = 0;
            for(int k = 0; k < len1; k++){
                if(s1.charAt(k) == s2.charAt(j)) j++;
                if(j == len2){
                    j = 0;
                    cnt++;
                }
            }
            dpCnt[i] = cnt;
            dpPos[i] = j;
        }
        int ans = 0;
        int pos = 0;
        for(int i = 0; i < n1; i++){
            ans += dpCnt[pos];
            pos = dpPos[pos];
        }
        return ans / n2;
    }
    public static void main(String[] args) {
        Leetcode0466_2 leetcode0466_2 = new Leetcode0466_2();
        String s1 = "acb";
        String s2 = "acb";
        System.out.println(leetcode0466_2.getMaxRepetitions(s1, 1, s2, 1));
    }
}
