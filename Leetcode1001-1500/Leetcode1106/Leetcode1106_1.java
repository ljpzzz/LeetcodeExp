package Leetcode1106;

public class Leetcode1106_1 {
    public boolean parseBoolExpr(String expression) {
        char op = expression.charAt(0);
        if(op == 't') return true;
        if(op == 'f') return false;
        String sub = expression.substring(2, expression.length()-1);
        if(op == '!') return !parseBoolExpr(sub);

        int cnt = 0;
        int begin = 0;
        for(int i = 0; i < sub.length(); i++){
            if(sub.charAt(i) == '(') cnt++;
            else if (sub.charAt(i) == ')') cnt--;
            else if(sub.charAt(i) == ',' && cnt == 0){
                boolean curAns = parseBoolExpr(sub.substring(begin, i));
                if(op == '&' && !curAns) return curAns;
                if(op == '|' && curAns) return curAns;
                begin = i+1;
            }
        }
        if(begin < sub.length()){
            boolean curAns = parseBoolExpr(sub.substring(begin));
            if(op == '&' && !curAns) return curAns;
            if(op == '|' && curAns) return curAns;
        }
        if(op == '&') return true;
        else return false;
    }
    public static void main(String[] args) {
        Leetcode1106_1 test = new Leetcode1106_1();
        String expression = "!(&(f,t))";
        System.out.println(test.parseBoolExpr(expression));
    }
}
