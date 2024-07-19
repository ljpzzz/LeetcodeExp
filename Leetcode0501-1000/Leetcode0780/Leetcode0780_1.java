package Leetcode0780;

public class Leetcode0780_1 {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while(sx <= tx && sy <= ty){
            if(sx == tx && sy == ty) return true;
            if(sx == tx) return (ty - sy)%sx == 0;
            if(sy == ty) return (tx - sx)%sy == 0;
            //到此意味着sx < tx && sy < ty
            if(tx == ty) return false; // 没法去减
            else if(tx < ty) ty = ty%tx;
            else tx = tx%ty;
        }
        return false;
    }
}
