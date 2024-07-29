package Leetcode0972;

public class Leetcode0972_1 {
    public boolean isRationalEqual(String s, String t) {
        String s2 = s;
        if(s.contains("(") ){
            int index = s.indexOf("(");
            String duplicate = s.substring(index+1, s.length()-1);
            String prefix = s.substring(0, index);
            s2 = prefix + duplicate;
            while(s2.length() <= 50){
                s2 += duplicate;
            }
        }

        //System.out.println(s2);
        double s2d = Double.valueOf(s2);


        String t2 = t;
        if(t.contains("(") ){
            int index = t.indexOf("(");
            String duplicate = t.substring(index+1, t.length()-1);
            String prefix = t.substring(0, index);
            t2 = prefix + duplicate;
            while(t2.length() <= 50){
                t2 += duplicate;
            }
        }
        //System.out.println(t2);
        double t2d = Double.valueOf(t2);
        //处理0.9999和1这样的特殊情况
        if(Math.abs(s2d-t2d) <= Math.pow(10, -30)) return true;
        else return false;
    }
}
