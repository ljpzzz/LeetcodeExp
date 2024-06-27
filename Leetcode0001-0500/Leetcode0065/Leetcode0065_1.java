package Leetcode0065;

public class Leetcode0065_1 {
    //暴力检查
    public boolean isNumber(String s) {
        //先确认有没有e以外的字符，对于e，确认只有1个
        int eIndex = -1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            //当前是字母
            if(Character.isLetter(c)){
                //当前字母是E或者e
                if(c == 'E' || c == 'e'){
                    //第一次出现E或者e
                    if(eIndex == -1) eIndex = i;
                        //多次出现E或者e，不合法
                    else return false;
                }
                //出现不是E或者e的字母不合法
                else return false;
            }
        }
        //如果包含指数，先检查指数部分是否合法
        if(eIndex != -1){
            String eNext = s.substring(eIndex+1);
            //指数部分处理完后截短
            s = s.substring(0, eIndex);
            // e(E)后面不是整数，则不合法
            if(!isValidNum(eNext)) return false;
        }
        //指数部分已经检查完毕，检查前面的数字
        //按小数检查
        if(s.contains(".")){
            if(isValidDotNum(s)) return true;
            else return false;
        }
        //按整数检查
        else{
            if(isValidNum(s)) return true;
            return false;
        }
    }
    //检查是否是合法整数
    private boolean isValidNum(String s){
        int n = s.length();
        if(n == 0) return false;
        int index = 0;
        if(s.charAt(0) == '+' || s.charAt(0) == '-') index++;
        //防止只有+，-，后面没有数字的特殊情况
        if(index == n) return false;
        if(isValidDigit(s.substring(index))) return true;
        else return false;
    }
    //检查是否是合法数字
    private boolean isValidDigit(String s){
        int n = s.length();
        if(n == 0) return false;
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }
    //检查是否是合法小数, 进入本函数已经确认有小数点了
    private boolean isValidDotNum(String s){
        int n = s.length();
        if(n == 0) return false;
        int index = 0;
        if(s.charAt(0) == '+' || s.charAt(0) == '-') index++;
        //防止只有+，-，后面没有数字的特殊情况
        if(index == n) return false;
        int dotIndex = -1;
        for(int i = index; i < n; i++){
            char c = s.charAt(i);
            if(c == '.'){
                //只有一个小数点的时候更新位置
                if(dotIndex == -1) dotIndex = i;
                    //有多个小数点，则不合法
                else return false;
            }
        }
        //确认小数点的位置
        //如果小数点在开头， 确认后面是否是数字
        if(dotIndex == index){
            if(isValidDigit(s.substring(index+1))) return true;
            else return false;
        }
        //如果小数点在末尾， 确认前面的是否是数字
        else if(dotIndex == n-1){
            if(isValidDigit(s.substring(index, n-1))) return true;
            else return false;
        }
        //小数点在中间
        else{
            if(isValidDigit(s.substring(index, dotIndex)) &&
                    isValidDigit(s.substring(dotIndex+1)) ) return true;
            else return false;
        }
    }
}
