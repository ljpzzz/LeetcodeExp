package Leetcode0770;

import java.util.*;

public class Leetcode0770_1 {
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        Map<String, String> valValueMap = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) valValueMap.put(evalvars[i], String.valueOf(evalints[i]));
        //获取逆波兰表达式
        List<String> rpn = getRPN(expression, valValueMap);
        //计算逆波兰表达式的值
        Expr expr = evalRPN(rpn);
        return expr.toList();

    }

    public List<String> getRPN(String expression, Map<String, String> valValueMap) {
        List<String> rpn = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();
        String[] strArr = expression.split(" ");
        int len = strArr.length;
        for (int i = 0; i < len; i++) {
            String str = strArr[i];
            int start = 0;
            int end = str.length() - 1;
            while (str.charAt(start) == '(') {
                stack.push('(');
                start++;
            }
            int popCount = 0;
            while (str.charAt(end) == ')') {
                popCount++;
                end--;
            }
            str = str.substring(start, end + 1);
            //遇到字符串，先看看能不能在哈希表拿到值，拿不到则直接存字符串到逆波兰表达式
            if (Character.isLetterOrDigit(str.charAt(0))) {
                String val = valValueMap.getOrDefault(str, str);
                rpn.add(val);
            }
            //遇到运算符
            else {
                char op = str.charAt(0);
                int precedure = getPrecedence(op);
                //将栈中高优先级的运算符加到逆波兰表达式
                while (stack.size() > 0 && getPrecedence(stack.peek()) >= precedure) {
                    rpn.add(String.valueOf(stack.pop()));
                }
                //将当前运算符入栈
                stack.push(op);
            }
            //处理右括号，将栈里左括号之前的运算符都加到到逆波兰表达式
            while (popCount > 0) {
                while (stack.peek() != '(') rpn.add(String.valueOf(stack.pop()));
                //弹出左括号
                stack.pop();
                popCount--;
            }
        }
        //将栈中剩下的运算符加到逆波兰表达式
        while (stack.size() > 0) rpn.add(String.valueOf(stack.pop()));
        return rpn;
    }

    public Expr evalRPN(List<String> rpn) {
        Deque<Expr> stack = new ArrayDeque<>();
        int cnt = rpn.size();
        for (int i = 0; i < cnt; i++) {
            String token = rpn.get(i);
            if (Character.isLetterOrDigit(token.charAt(token.length() - 1))) stack.push(new Expr(token));
            else {
                Expr exp2 = stack.pop();
                Expr exp1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(exp1.add(exp2));
                        break;
                    case "-":
                        stack.push(exp1.subtract(exp2));
                        break;
                    case "*":
                        stack.push(exp1.multiply(exp2));
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    //获取运算符优先级
    public int getPrecedence(char op) {
        if (op == '*') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    class Expr {
        private Map<List<String>, Integer> counts;

        public Expr(String term) {
            counts = new HashMap<List<String>, Integer>();
            List<String> key = new ArrayList<String>();
            if (Character.isLetter(term.charAt(0))) {
                key.add(term);
                counts.put(key, 1);
            } else {
                counts.put(key, Integer.parseInt(term));
            }
        }

        public Expr(Map<List<String>, Integer> counts) {
            this.counts = counts;
        }

        public Expr add(Expr expr2) {
            Map<List<String>, Integer> countsSum = new HashMap<List<String>, Integer>(counts);
            Map<List<String>, Integer> counts2 = expr2.counts;
            Set<Map.Entry<List<String>, Integer>> entries = counts2.entrySet();
            for (Map.Entry<List<String>, Integer> entry : entries) {
                List<String> key = entry.getKey();
                int value = entry.getValue();
                countsSum.put(key, countsSum.getOrDefault(key, 0) + value);
            }
            return new Expr(countsSum);
        }

        public Expr subtract(Expr expr2) {
            Map<List<String>, Integer> countsDifference = new HashMap<List<String>, Integer>(counts);
            Map<List<String>, Integer> counts2 = expr2.counts;
            Set<Map.Entry<List<String>, Integer>> entries = counts2.entrySet();
            for (Map.Entry<List<String>, Integer> entry : entries) {
                List<String> key = entry.getKey();
                int value = entry.getValue();
                countsDifference.put(key, countsDifference.getOrDefault(key, 0) - value);
            }
            return new Expr(countsDifference);
        }

        public Expr multiply(Expr expr2) {
            Map<List<String>, Integer> countsProduct = new HashMap<List<String>, Integer>();
            Map<List<String>, Integer> counts2 = expr2.counts;
            Set<Map.Entry<List<String>, Integer>> entries1 = counts.entrySet();
            Set<Map.Entry<List<String>, Integer>> entries2 = counts2.entrySet();
            for (Map.Entry<List<String>, Integer> entry1 : entries1) {
                List<String> key1 = entry1.getKey();
                int value1 = entry1.getValue();
                for (Map.Entry<List<String>, Integer> entry2 : entries2) {
                    List<String> key2 = entry2.getKey();
                    int value2 = entry2.getValue();
                    List<String> newKey = getNewKey(key1, key2);
                    countsProduct.put(newKey, countsProduct.getOrDefault(newKey, 0) + value1 * value2);
                }
            }
            return new Expr(countsProduct);
        }

        public List<String> toList() {
            List<String> list = new ArrayList<String>();
            List<List<String>> keys = new ArrayList<List<String>>(counts.keySet());
            Collections.sort(keys, (a, b) -> {
                if (a.size() != b.size()) {
                    return b.size() - a.size();
                } else {
                    return a.toString().compareTo(b.toString());
                }
            });
            int size = keys.size();
            for (int i = 0; i < size; i++) {
                List<String> key = keys.get(i);
                int count = counts.get(key);
                if (count != 0) {
                    list.add(getTerm(key, count));
                }
            }
            return list;
        }

        private List<String> getNewKey(List<String> key1, List<String> key2) {
            List<String> newKey = new ArrayList<String>(key1);
            newKey.addAll(key2);
            Collections.sort(newKey);
            return newKey;
        }

        private String getTerm(List<String> key, int count) {
            StringBuffer sb = new StringBuffer();
            sb.append(count);
            int size = key.size();
            for (int i = 0; i < size; i++) {
                sb.append('*');
                sb.append(key.get(i));
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Leetcode0770_1().basicCalculatorIV("(e + 8) * (e - 8)", new String[]{}, new int[]{}));
    }
}
