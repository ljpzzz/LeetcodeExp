package Leetcode0335;

public class Leetcode0335_1 {
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if(n <= 3) return false;
        for(int i = 3; i < n; i++){

            /*               i-2
                case 1 : i-1┌─┐
                            └─┼─>i
                             i-3
            */
            if(distance[i] >= distance[i-2] && distance[i-1] <= distance[i-3]) return true;
            /*                      i-2
                        case 2 : i-1 ┌────┐
                                     └─══>┘i-3
                                     i  i-4      (i overlapped i-4)
            */
            if(i >= 4 && distance[i-1] == distance[i-3] && distance[i] + distance[i-4] >= distance[i-2]) return true;
            /*      case 3 :    i-4
                               ┌──┐
                               │i<┼─┐
                            i-3│ i-5│i-1
                               └────┘
                                i-2

            */
            if(i >= 5 && distance[i-4] <= distance[i-2] && distance[i-4]+distance[i] >= distance[i-2] &&
                    distance[i-1] <= distance[i-3] && distance[i-5] + distance[i-1] >= distance[i-3]) return true;
        }
        return false;
    }
}
