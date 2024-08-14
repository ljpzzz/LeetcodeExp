package Leetcode1453;

public class Leetcode1453_1 {
    double eps = 1e-6;
    public int numPoints(int[][] darts, int r) {
        int n = darts.length;
        int ans = 1;
        for(int i = 0; i < n; i++){
            int x1 = darts[i][0]; int y1 = darts[i][1];
            for(int j = i + 1; j < n; j++){
                int x2 = darts[j][0]; int y2 = darts[j][1];
                double xm = (x1 + x2) / 2.0d; double ym = (y1 + y2) / 2.0d;
                if(getDistance2(x1, y1, x2, y2) > 4*r*r) continue;
                if(getDistance2(x1, y1, x2, y2) == 4*r*4){
                    ans = Math.max(ans, getCnt(darts, r, xm, ym));
                    continue;
                }
                double l1m = Math.sqrt(getDistance2(x1, y1, xm, ym));
                double lom = Math.sqrt(r*r-l1m*l1m);
                double xo = xm - lom * (ym - y1) / l1m;
                double yo = ym + lom * (xm - x1) / l1m;
                ans = Math.max(ans, getCnt(darts, r, xo, yo));
                xo = xm + lom * (ym - y1) / l1m;
                yo = ym - lom * (xm - x1) / l1m;
                ans = Math.max(ans, getCnt(darts, r, xo, yo));
            }
        }
        return ans;
    }
    public int getCnt(int[][] darts, int r, double x, double y){
        int cnt = 0;
        for(int[] dart: darts){
            double dis2 = getDistance2(x, y, dart[0], dart[1]);
            if(dis2 - r*r <= eps) cnt++;
        }
        return cnt;
    }
    public double getDistance2(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
