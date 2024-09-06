package Leetcode1622;

import java.util.*;

public class Leetcode1622_2 {
    int MOD = (int)1e9+7;
    List<Integer> valList;
    List<Long> mulList;
    List<Long> addList;
    public Leetcode1622_2() {
        valList = new ArrayList<>();
        mulList = new ArrayList<>();
        mulList.add(1L);
        addList = new ArrayList<>();
        addList.add(0L);
    }

    public void append(int val) {
        valList.add(val);
        mulList.add(mulList.get(mulList.size()-1));
        addList.add(addList.get(addList.size()-1));
    }

    public void addAll(int inc) {
        if(valList.size() == 0) return;
        long lastAdd = addList.remove(addList.size()-1);
        lastAdd = (lastAdd + inc)%MOD;
        addList.add(lastAdd);
    }

    public void multAll(int m) {
        if(valList.size() == 0) return;
        long lastMul = mulList.remove(mulList.size()-1);
        long lastAdd = addList.remove(addList.size()-1);
        lastMul = lastMul*m%MOD;
        lastAdd = lastAdd*m%MOD;
        mulList.add(lastMul);
        addList.add(lastAdd);
    }

    public int getIndex(int idx) {
        if(idx > valList.size()-1) return -1;
        long lastMul = mulList.get(mulList.size()-1);
        long lastAdd = addList.get(addList.size()-1);
        long idxMul = mulList.get(idx);
        long idxAdd = addList.get(idx);
        long a = inv(idxMul)*lastMul%MOD;
        long b = (lastAdd-a*idxAdd%MOD + MOD)%MOD;
        long res = (a*valList.get(idx)%MOD+b)%MOD;
        return (int)res;
    }
    //计算a^b%MOD
    private long pow(long a, int b){
        long res = 1;
        long a1 = a;
        while(b > 0){
            if((b&1) > 0) res =  (res*a1)%MOD;
            a1 = (a1*a1)%MOD;
            b = b/2;
        }
        return res;
    }
    private long inv(long val){
        return pow(val, MOD-2);
    }
}
