package Leetcode0458;

public class Leetcode0458_1 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int round = minutesToTest / minutesToDie;
        return (int)Math.ceil(Math.log(buckets) / Math.log(round + 1)-1e-5);
    }
}
