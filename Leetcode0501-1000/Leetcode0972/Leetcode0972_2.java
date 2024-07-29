package Leetcode0972;

public class Leetcode0972_2 {
    public boolean isRationalEqual(String s, String t) {
        Fraction sf = change(s);
        Fraction tf = change(t);
        return sf.numerator == tf.numerator && sf.denominator == tf.denominator;
    }
    public Fraction change(String s){
        int inxDot = s.indexOf(".");
        if(inxDot == -1) return new Fraction(Long.parseLong(s), 1);
        Fraction f = new Fraction(Long.parseLong(s.substring(0, inxDot)), 1); //整数部分
        s = s.substring(inxDot + 1, s.length());
        int len = s.length();
        int inxCircle = s.indexOf("(");
        if(inxCircle == -1) {
            if(len > 0) f.add(new Fraction(Long.parseLong(s), (long)Math.pow(10, len)));
            return f;
        }
        String sNoCircle = s.substring(0, inxCircle);
        int lenNoCircle = sNoCircle.length();
        if(lenNoCircle > 0) f.add(new Fraction(Long.parseLong(sNoCircle), (long)Math.pow(10, lenNoCircle)));
        String sCircle = s.substring(inxCircle + 1, len-1);
        int lenCircle = sCircle.length();
        f.add(new Fraction(Long.parseLong(sCircle), (long)(Math.pow(10, lenCircle)-1)*(long)Math.pow(10, lenNoCircle)));
        return f;
    }
    class Fraction {
        long numerator;
        long denominator;
        public Fraction(long n, long d) {
            long g = gcd(n, d);
            this.numerator = n/g;
            this.denominator = d/g;
        }
        public void add(Fraction f){
            this.numerator = this.numerator * f.denominator + f.numerator * this.denominator;
            this.denominator *= f.denominator;
            long g = gcd(this.numerator, this.denominator);
            this.numerator /= g;
            this.denominator /= g;
            if (this.numerator == 0) this.denominator = 1;
        }
        public long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }
    public static void main(String[] args) {
        Leetcode0972_2 l = new Leetcode0972_2();
        System.out.println(l.isRationalEqual("0.(9)", "1."));
    }
}
