package Leetcode1274;

  // You should not implement it, or speculate about its implementation
class Sea {
      public boolean hasShips(int[] topRight, int[] bottomLeft){}
}

public class Leetcode1274_1 {
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        boolean ans = sea.hasShips(topRight, bottomLeft);
        if(!ans) return 0;
        if(topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) return 1;
        if(topRight[0] == bottomLeft[0]){
            int midY = (topRight[1] + bottomLeft[1])/2;
            return countShips(sea, new int[]{topRight[0], midY}, bottomLeft) +
                    countShips(sea, topRight, new int[]{topRight[0], midY+1});
        }
        else{
            int midX = (topRight[0] + bottomLeft[0])/2;
            return countShips(sea, new int[]{midX, topRight[1]}, bottomLeft) +
                    countShips(sea, topRight, new int[]{midX+1, bottomLeft[1]});
        }
    }
}
