package Leetcode0679;

import java.util.*;

public class Leetcode0679_1 {
    boolean isOK = false;
    public boolean judgePoint24(int[] cards) {
        List<int[]> cardList = new ArrayList<>();
        for(int card : cards) cardList.add(new int[]{card, 1});
        dfs(cardList);
        return isOK;
    }
    private void dfs(List<int[]> cardList){
        if(isOK) return;
        if(cardList.size() == 1){
            int[] curRes = cardList.get(0);
            if(curRes[0]%curRes[1] == 0 && curRes[0]/curRes[1] == 24) isOK = true;
            return;
        }
        //尝试取2个值出来先计算
        for(int i = 0; i < cardList.size(); i++){
            for(int j = 0; j < cardList.size(); j++){
                if(i == j) continue;
                int[] a = cardList.get(i);
                int[] b = cardList.get(j);
                List<int[]> cardListNew = new ArrayList<>(cardList);
                if(i < j){
                    cardListNew.remove(j);
                    cardListNew.remove(i);
                }
                else{
                    cardListNew.remove(i);
                    cardListNew.remove(j);
                }
                //尝试加号
                int[] addRes = add(a, b);
                cardListNew.add(addRes);
                dfs(cardListNew);
                cardListNew.remove(cardListNew.size()-1);
                //尝试减号
                int[] minusRes = minus(a, b);
                cardListNew.add(minusRes);
                dfs(cardListNew);
                cardListNew.remove(cardListNew.size()-1);
                //尝试乘号
                int[] multiRes = multi(a, b);
                cardListNew.add( multiRes);
                dfs(cardListNew);
                cardListNew.remove(cardListNew.size()-1);
                //尝试除号, 但是不能除以0
                if(b[0] == 0 || b[1] == 0) continue;
                int[] divRes = div(a, b);
                cardListNew.add(divRes);
                dfs(cardListNew);
            }
        }
    }
    //a[0]/a[1] + b[0]/b[1]
    private int[] add(int[] a, int[] b){
        int[] res = new int[2];
        res[1] = lcm(a[1], b[1]);
        res[0] = res[1]/a[1]*a[0] + res[1]/b[1]*b[0];
        //System.out.println(a[0]+ "/" + a[1] + "+" + b[0]+ "/" + b[1] + "=" + res[0]+ "/" + res[1]);
        return res;
    }
    //a[0]/a[1] + b[0]/b[1]
    private int[] minus(int[] a, int[] b){
        int[] res = new int[2];
        res[1] = lcm(a[1], b[1]);
        res[0] = res[1]/a[1]*a[0] - res[1]/b[1]*b[0];
        //System.out.println(a[0]+ "/" + a[1] + "-" + b[0]+ "/" + b[1] + "=" + res[0]+ "/" + res[1]);
        return res;
    }
    //a[0]/a[1] * b[0]/b[1]
    private int[] multi(int[] a, int[] b){
        int[] res = new int[2];
        res[0] = a[0]*b[0];
        res[1] = a[1]*b[1];
        //System.out.println(a[0]+ "/" + a[1] + "x" + b[0]+ "/" + b[1] + "=" + res[0]+ "/" + res[1]);
        return res;
    }
    //a[0]/a[1] / b[0]/b[1]
    private int[] div(int[] a, int[] b){
        int[] res = new int[2];
        res[0] = a[0]*b[1];
        res[1] = a[1]*b[0];
        //System.out.println(a[0]+ "/" + a[1] + "÷" + b[0]+ "/" + b[1] + "=" + res[0]+ "/" + res[1]);
        return res;
    }
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
    public static void main(String[] args) {
        System.out.println(new Leetcode0679_1().judgePoint24(new int[]{3,9,7,7}));
        System.out.println(new Leetcode0679_1().judgePoint24(new int[]{7,2,6,6}));
    }
}
