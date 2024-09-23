package Leetcode1803;

public class Leetcode1803_1 {
    int LEN;
    public int countPairs(int[] nums, int low, int high) {
        int n = nums.length;
        DictTree dictTree = new DictTree();
        LEN = Integer.toBinaryString(high).length();
        String highStr = getBinary(high);
        String lowStr = getBinary(low-1);
        int ans = 0;
        for(int i = 0; i < n; i++){
            String cur = getBinary(nums[i]);
            dictTree.add(cur);
            int curAns = dictTree.search(cur, highStr) - dictTree.search(cur, lowStr);
            ans += curAns;
        }
        return ans;
    }
    public String getBinary(int num){
        String ans = Integer.toBinaryString(num);
        while(ans.length() < LEN) ans = "0" + ans;
        return ans;
    }
    public class DictTree {
        public DictTree[] children = new DictTree[2];
        public int count = 0;
        public void add(String word) {
            DictTree cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children[c - '0'] == null) cur.children[c - '0'] = new DictTree();
                cur = cur.children[c - '0'];
                cur.count++;
            }
        }
        public int search(String word, String cmp) {
            DictTree cur = this;
            int cnt = 0;
            for (int i = 0; i < word.length(); i++) {
                int posWord = word.charAt(i) - '0';
                int posCmp = cmp.charAt(i) - '0';
                if (posCmp == 1) {
                    if (cur.children[posWord] != null) cnt += cur.children[posWord].count;
                    if (cur.children[posWord ^ 1] == null) return cnt;
                    else cur = cur.children[posWord ^ 1];
                } else {
                    if (cur.children[posWord] != null) cur = cur.children[posWord];
                    else return cnt;
                }
            }
            cnt += cur.count;
            return cnt;
        }
    }
    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 7};
        int low = 2;
        int high = 6;
        Leetcode1803_1 leetcode1803_1 = new Leetcode1803_1();
        System.out.println(leetcode1803_1.countPairs(nums, low, high));
    }
}
