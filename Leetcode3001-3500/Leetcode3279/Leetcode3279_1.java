package Leetcode3279;

public class Leetcode3279_1 {
    public long maxArea(int height, int[] positions, String directions) {
        int n = positions.length;
        long ans = 0;
        int[] delta = new int[2*height+2];
        for(int i = 0; i < n; i++){
            ans += positions[i];
            if(directions.charAt(i) == 'U' && positions[i] != height || directions.charAt(i) == 'D' && positions[i] == 0){
                delta[1]++;
                delta[height-positions[i]+1] -= 2;
                delta[2*height-positions[i]+1] += 2;
            }
            else{
                delta[1]--;
                delta[positions[i]+1] += 2;
                delta[positions[i]+height+1] -= 2;
            }
        }
        long maxDelta = 0;
        long curDelta = 0;
        for(int i = 1; i <= 2*height; i++){
            delta[i] += delta[i-1];
            curDelta += delta[i];
            //System.out.println("step " + i + ", delta:" + curDelta);
            maxDelta = Math.max(maxDelta, curDelta);
        }
        return maxDelta + ans;
    }
    public static void main(String[] args) {
        Leetcode3279_1 test = new Leetcode3279_1();
        int height = 5;
        int[] positions = {2,5};
        String directions = "UD";
        System.out.println(test.maxArea(height, positions, directions));
    }
}
