package Leetcode0466;

public class Leetcode0466_3 {
    //dp+倍增
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int BIT = Integer.toBinaryString(n1).length();
        //dpCnt[i][j]表示从s2的位置i开始匹配，匹配到2^j个完整的s1时，已经匹配s2末尾的次数
        int[][] dpCnt = new int[len2][BIT];
        //dpPos[i][j]表示从s2的位置i开始匹配，匹配到2^j个完整的s1时，s2的下一个位置
        int[][] dpPos = new int[len2][BIT];
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
            dpCnt[i][0] = cnt;
            dpPos[i][0] = j;
        }
        //倍增
        for(int j = 1; j < BIT; j++){
            for(int i = 0; i < len2; i++){
                dpPos[i][j] = dpPos[dpPos[i][j-1]][j-1];
                dpCnt[i][j] = dpCnt[i][j-1] + dpCnt[dpPos[i][j-1]][j-1];
            }
        }
        int ans = 0;
        int pos = 0;
        for(int i = 0; i < BIT; i++){
            if((n1 & (1 << i)) == 0) continue;
            ans += dpCnt[pos][i];
            pos = dpPos[pos][i];
        }
        return ans / n2;
    }
    public static void main(String[] args) {
        Leetcode0466_3 leetcode0466_3 = new Leetcode0466_3();
        String s1 = "acb";
        String s2 = "ab";
        System.out.println(leetcode0466_3.getMaxRepetitions(s1, 4, s2, 2));
    }
}
