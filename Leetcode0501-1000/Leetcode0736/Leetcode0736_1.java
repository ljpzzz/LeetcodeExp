package Leetcode0736;

import java.util.*;

public class Leetcode0736_1 {
    int index = 0; //解析到exp的位置
    // key:变量  value：赋值栈，最后赋值的最先出，最先使用
    Map<String, Deque<Integer>> strScopeMap = new HashMap<>();
    public int evaluate(String expression) {
        return evaluateWithContext(expression);
    }
    private int evaluateWithContext(String exp){
        // 不是"("打头，说明不是表达式，那么只能是数字或者变量
        if(exp.charAt(index) != '('){
            //是数字，解析后直接返回
            if(!Character.isLowerCase(exp.charAt(index))) {
                int res =  parseInt(exp);
                //System.out.println("parse int res:" + res);
                return res;
            }
            //是变量，解析后，在scope中获取最后赋值返回
            else{
                String val = parseVal(exp);
                int res =  strScopeMap.get(val).peek();
                //System.out.println("parse val res:" + res);
                return res;
            }
        }
        //是左括号打头，说明是表达式
        int res = 0;
        //跳过左括号
        index++;
        // let表达式
        if(exp.charAt(index) == 'l'){
            //跳过let和空格
            index += 4;
            //用于保存当前赋值的变量列表
            List<String> valList = new ArrayList<>();
            while(true){
                //当前可以是变量或者数字
                //如果当前是不是变量，则说明是最后一个表达式了,解析完let结束循环
                if(!Character.isLowerCase(exp.charAt(index))){
                    res = evaluateWithContext(exp);
                    break;
                }
                //解析当前的变量名字
                String valTmp = parseVal(exp);
                //如果当前变量没有配对的赋值，则说明是最后一个表达式，解析完let结束循环
                if(exp.charAt(index) == ')'){
                    res = strScopeMap.get(valTmp).peek();
                    break;
                }
                //到此处说明有配对的赋值
                valList.add(valTmp);
                //跳过空格
                index++;
                int valValue = evaluateWithContext(exp);
                //将变量和对应赋值加到map
                if(strScopeMap.get(valTmp) == null){
                    Deque<Integer> st = new ArrayDeque<>();
                    strScopeMap.put(valTmp, st);
                }
                strScopeMap.get(valTmp).push(valValue);
                //移除空格
                index++;
                //继续下一轮变量与赋值的查找循环
            }
            //清除当前let作用域里赋值的变量
            for(String valll : valList) strScopeMap.get(valll).pop();
        }
        //add表达式
        else if(exp.charAt(index) == 'a'){
            //跳过add和空格
            index += 4;
            int val1 = evaluateWithContext(exp);
            //跳过空格
            index++;
            int val2 = evaluateWithContext(exp);
            res = val1+val2;
        }
        //mult表达式
        else{
            //跳过mult和空格
            index += 5;
            int val1 = evaluateWithContext(exp);
            //跳过空格
            index++;
            int val2 = evaluateWithContext(exp);
            res = val1*val2;
        }
        //跳过右括号
        index++;
        //System.out.println("parse exp res:" + res);
        return res;
    }
    private int parseInt(String exp){
        int sign = 1;
        //处理负数是负号打头的情况
        if(exp.charAt(index) == '-'){
            sign = -1;
            index++;
        }
        int val = 0;
        while(index < exp.length() && Character.isDigit(exp.charAt(index))){
            char c = exp.charAt(index);
            val = val*10 + c-'0';
            index++;
        }
        return sign*val;
    }
    private String parseVal(String exp){
        String res = "";
        while(index < exp.length()){
            char c = exp.charAt(index);
            //变量已经结束了，停止解析
            if(c == ')' || c == ' ') break;
            res += c;
            index++;
        }
        return res;
    }
}
