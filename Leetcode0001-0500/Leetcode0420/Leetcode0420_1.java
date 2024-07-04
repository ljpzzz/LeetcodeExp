package Leetcode0420;

public class Leetcode0420_1 {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        int hasDigit = 0;
        int hasLowerChar = 0;
        int hasUpperChar = 0;
        for(int i = 0; i < n; i++){
            char cur = password.charAt(i);
            if(cur >= '0' && cur <= '9') hasDigit = 1;
            else if(cur >= 'a' && cur <= 'z') hasLowerChar = 1;
            else if(cur >= 'A' && cur <= 'Z') hasUpperChar = 1;
        }
        //当前数字，小写，大写三种类型的数量，小于3的话需要补到3, 即需要添加3-typeCount
        int typeCount = hasDigit + hasLowerChar + hasUpperChar;
        //当长度小于6时，只需要考虑添加长度到6， 以及补齐类型数量
        if(n < 6){
            return Math.max(6-n, 3-typeCount);
        }
        //长度符合要求的时候，只需要针对超长的进行替换，以及补齐类型数量
        else if(n >= 6 && n <= 20){
            int replaceCount = 0;
            for(int i = 0; i < n;){
                int j = i;
                for(; j < n; j++){
                    if(password.charAt(j) != password.charAt(i)) break;
                }
                int cnt = j-i;
                if(cnt >= 3) replaceCount += cnt/3;
                i = j;
            }
            return Math.max(replaceCount, 3-typeCount);
        }
        //长度超过20， 需要删除n-20个字符，要针对超长的进行替换，以及补齐类型数量
        else{
            int plus0Cnt = 0;
            int plus1Cnt = 0;
            int removeCount = n-20;
            int replaceCount = 0;
            for(int i = 0; i < n;){
                int j = i;
                for(; j < n; j++){
                    if(password.charAt(j) != password.charAt(i)) break;
                }
                int cnt = j-i;
                if(cnt >= 3){
                    replaceCount += cnt/3;
                    if(cnt%3 == 0) plus0Cnt++;
                    else if(cnt%3 == 1) plus1Cnt++;
                }
                i = j;
            }
            //由于删除可以减少替换次数，因此需要检查确认最终的替换次数
            int removeLeft = removeCount;
            //对于余数为0的连续长度，删除一个字符可以使替换次数减1
            if(plus0Cnt > 0 && removeLeft > 0){
                int removeCur = Math.min(plus0Cnt, removeLeft);
                removeLeft -= removeCur;
                replaceCount -= removeCur;
            }
            //对于余数为1的连续长度，删除2个字符可以使替换次数减1
            if(plus1Cnt > 0 && removeLeft > 0){
                int removeCur = Math.min(2*plus1Cnt, removeLeft);
                removeLeft -= removeCur;
                replaceCount -= removeCur/2;
            }
            //剩下的全部是需要删除3个字符才能使替换次数减1的
            int plus2Cnt = replaceCount;
            if(plus2Cnt > 0 && removeLeft > 0){
                int removeCur = Math.min(3*plus2Cnt, removeLeft);
                removeLeft -= removeCur;
                replaceCount -= removeCur/3;
            }
            return removeCount + Math.max(replaceCount, 3-typeCount);
        }
    }
}
