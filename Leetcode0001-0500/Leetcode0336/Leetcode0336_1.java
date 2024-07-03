package Leetcode0336;

import java.util.*;

public class Leetcode0336_1 {
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        List<List<Integer>> ans = new ArrayList<>();
        Map<String, Integer> reverseMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String rev = new StringBuffer(words[i]).reverse().toString();
            reverseMap.put(rev, i);
        }
        for(int i = 0; i < n; i++){
            String s = words[i];
            int m = s.length();
            if(reverseMap.get(s) != null && reverseMap.get(s) != i){
                ans.add(Arrays.asList(i, reverseMap.get(s)));
            }
            int[][] huiwen = isHuiwenPreAndPost(s);
            for(int j = 0; j < m; j++){
                if(huiwen[j][0] == 1){
                    String postStr = s.substring(j + 1);
                    if(reverseMap.containsKey(postStr)) ans.add(Arrays.asList(reverseMap.get(postStr), i));
                }
                if(huiwen[j][1] == 1){
                    String preStr = s.substring(0, j);
                    if(reverseMap.containsKey(preStr)) ans.add(Arrays.asList(i, reverseMap.get(preStr)));
                }
            }
        }
        return ans;
    }
    // 使用Manacher算法寻找以index结束和开始的子串是否回文子串
//返回值里第一维是字符串的位置index， 第二维大小是2，第一个是[0, index]是否是回文,第二个是[index, n-1]是否是回文
    public int[][] isHuiwenPreAndPost(String s) {
        int n = s.length();
        StringBuffer tmp = new StringBuffer("#");
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                tmp.append('*');
            }
            tmp.append(s.charAt(i));
        }
        tmp.append('!');
        int m = n * 2;
        int[] len = new int[m];
        int[][] ret = new int[n][2];
        int p = 0, maxn = -1;
        for (int i = 1; i < m; i++) {
            len[i] = maxn >= i ? Math.min(len[2 * p - i], maxn - i) : 0;
            while (tmp.charAt(i - len[i] - 1) == tmp.charAt(i + len[i] + 1)) {
                len[i]++;
            }
            if (i + len[i] > maxn) {
                p = i;
                maxn = i + len[i];
            }
            if (i - len[i] == 1) {
                ret[(i + len[i]) / 2][0] = 1;
            }
            if (i + len[i] == m - 1) {
                ret[(i - len[i]) / 2][1] = 1;
            }
        }
        return ret;
    }
    public static void main(String args[]){
        Leetcode0336_1 leetcode0336_1 = new Leetcode0336_1();
        String[] words = {"abcd","dcba","lls","s","sssll"};
        System.out.println(leetcode0336_1.palindromePairs(words));
    }
}
