package Leetcode0032;

public class Leetcode0032_2 {
    //左右各一次扫描
    public int longestValidParentheses(String s) {
        int max = 0;
        char[] sArr = s.toCharArray();
        int counter = 0; //遇到'('加1，遇到')'减1
        int orig0Index = -1;
        //从左到右遍历
        for(int i = 0; i < sArr.length; i++){
            if(sArr[i] == '('){
                counter++;
            }
            else{
                counter--;
                //如果counter小于0，需要重置counter，修改index位置
                if(counter < 0){
                    counter = 0;
                    orig0Index = i;
                    continue;
                }
                //找到一个可行解
                else if(counter == 0){
                    int len = i-orig0Index;
                    max = Math.max(max, len);
                }
                //仍然大于0不做处理
            }
        }

        counter = 0; //遇到'('加1，遇到')'减1
        orig0Index = sArr.length;
        //从右向左遍历
        for(int i = sArr.length-1; i >= 0; i--){
            if(sArr[i] == ')'){
                counter--;
            }
            else{
                counter++;
                //如果counter大于0，需要重置counter，修改index位置
                if(counter > 0){
                    counter = 0;
                    orig0Index = i;
                    continue;
                }
                //找到一个可行解
                else if(counter == 0){
                    int len = orig0Index-i;
                    max = Math.max(max, len);
                }
                //仍然小于于0不做处理
            }
        }
        return max;
    }
}
