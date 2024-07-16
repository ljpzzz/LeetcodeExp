package Leetcode0679;

import java.util.*;

public class Leetcode0679_2 {
    //这里是输出所有的方案的
    List<String> ans = new ArrayList<>();
    public List<String> judgePoint24(int[] cards) {
        List<NumObj> cardList = new ArrayList<>();
        for(int card : cards) cardList.add(new NumObj(card));
        dfs(cardList);
        return ans;
    }
    private void dfs(List<NumObj> cardList){
        if(cardList.size() == 1){
            NumObj curRes = cardList.get(0);
            if(curRes.up%curRes.down == 0 && curRes.up/curRes.down == 24) {
                ans.add(curRes.cur.substring(1, curRes.cur.length()-1));
            }
            return;
        }
        //尝试取2个值出来先计算
        for(int i = 0; i < cardList.size(); i++){
            for(int j = 0; j < cardList.size(); j++){
                if(i == j) continue;
                NumObj a = cardList.get(i);
                NumObj b = cardList.get(j);
                List<NumObj> cardListNew = new ArrayList<>(cardList);
                if(i < j){
                    cardListNew.remove(j);
                    cardListNew.remove(i);
                }
                else{
                    cardListNew.remove(i);
                    cardListNew.remove(j);
                }
                //尝试加号
                NumObj addRes = add(a, b);
                cardListNew.add(addRes);
                dfs(cardListNew);
                cardListNew.remove(cardListNew.size()-1);
                //尝试减号
                NumObj minusRes = minus(a, b);
                cardListNew.add(minusRes);
                dfs(cardListNew);
                cardListNew.remove(cardListNew.size()-1);
                //尝试乘号
                NumObj multiRes = multi(a, b);
                cardListNew.add( multiRes);
                dfs(cardListNew);
                cardListNew.remove(cardListNew.size()-1);
                //尝试除号, 但是不能除以0
                if(b.up == 0 || b.down == 0) continue;
                NumObj divRes = div(a, b);
                cardListNew.add(divRes);
                dfs(cardListNew);
            }
        }
    }
    //a[0]/a[1] + b[0]/b[1]
    private NumObj add(NumObj a, NumObj b){
        NumObj res = new NumObj(0);
        res.down = lcm(a.down, b.down);
        res.up = res.down/a.down*a.up + res.down/b.down*b.up;
        res.cur = "(" + a.cur + "+" + b.cur + ")";
        return res;
    }
    //a[0]/a[1] - b[0]/b[1]
    private NumObj minus(NumObj a, NumObj b){
        NumObj res = new NumObj(0);
        res.down = lcm(a.down, b.down);
        res.up = res.down/a.down*a.up - res.down/b.down*b.up;
        res.cur = "(" + a.cur + "-" + b.cur + ")";
        return res;
    }
    //a[0]/a[1] * b[0]/b[1]
    private NumObj multi(NumObj a, NumObj b){
        NumObj res = new NumObj(0);
        res.down = a.down*b.down;
        res.up = a.up*b.up;
        res.cur = "(" + a.cur + "*" + b.cur + ")";
        return res;
    }
    //a[0]/a[1] / b[0]/b[1]
    private NumObj div(NumObj a, NumObj b){
        NumObj res = new NumObj(0);
        res.down = a.down*b.up;
        res.up = a.up*b.down;
        res.cur = "(" + a.cur + "/" + b.cur + ")";
        return res;
    }
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
    class NumObj{
        public int up;
        public int down;
        public String cur;
        public NumObj(int up){
            this.up = up;
            down = 1;
            cur = String.valueOf(up);
        }
        public NumObj(int up, int down, String cur){
            this.up = up;
            this.down = down;
            this.cur = cur;
        }
    }
    public static void main(String args[]){
        List<String> res = new Leetcode0679_2().judgePoint24(new int[]{7,2,6,6});
        for(String tmp : res) System.out.println(tmp);
        res = new Leetcode0679_2().judgePoint24(new int[]{3,9,7,7});
        for(String tmp : res) System.out.println(tmp);
    }
}
