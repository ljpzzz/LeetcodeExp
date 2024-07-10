package Leetcode0488;

import java.util.*;

public class Leetcode0488_1 {
    public int findMinStep(String board, String hand) {
        char[] handArr = hand.toCharArray();
        Arrays.sort(handArr);
        hand = new String(handArr);
        Queue<State> q = new ArrayDeque<>();
        q.offer(new State(board, hand, 0));
        Set<String> vis = new HashSet<>();
        vis.add(board + "#" + hand);
        while(!q.isEmpty()){
            State cur = q.poll();
            //放在位置i的前面，包括末尾
            for(int i = 0; i <= cur.board.length(); i++){
                //选择打出第j个球
                for(int j = 0; j < cur.hand.length(); j++){
                    //剪枝1：相同颜色的不需要重复选
                    if(j > 0 && cur.hand.charAt(j) == cur.hand.charAt(j-1)) continue;
                    //剪枝2：如果当前打的球和i-1颜色一样，不需要打，只考虑打连续相同颜色的最左边
                    if(i > 0 && cur.board.charAt(i-1) == cur.hand.charAt(j)) continue;
                    boolean ok = false;
                    //可以放球场景1：手里的球和当前球颜色相同，可以放在i的前面，此时隐含i-1的球颜色不停
                    if(i < cur.board.length() && cur.hand.charAt(j) == cur.board.charAt(i)) ok = true;
                    //可以放球场景2：当前球，当前前面的球相同，但是和手里的球不同
                    if(i > 0 && i < cur.board.length() && cur.hand.charAt(j) != cur.board.charAt(i) && cur.board.charAt(i) == cur.board.charAt(i-1)) ok = true;
                    if(!ok) continue;
                    String nextBoard = getNext(cur.board.substring(0, i) + cur.hand.charAt(j) + cur.board.substring(i));
                    String nextHand = cur.hand.substring(0, j) + cur.hand.substring(j+1);
                    if(nextBoard.isEmpty()) return cur.step + 1;
                    if(!vis.contains(nextBoard + "#" + nextHand)){
                        q.offer(new State(nextBoard, nextHand, cur.step + 1));
                        vis.add(nextBoard + "#" + nextHand);
                    }
                }
            }
        }
        return -1;
    }
    public String getNext(String board){
        Deque<Character> st = new ArrayDeque<>();
        Deque<Integer> stCnt = new ArrayDeque<>();
        int n = board.length();
        for(int i = 0; i <n; i++){
            char c = board.charAt(i);
            while(!st.isEmpty() && st.peek() != c && stCnt.peek() >= 3){
                st.pop();
                stCnt.pop();
            }
            if(st.isEmpty() || st.peek() != c){
                st.push(c);
                stCnt.push(1);
            }
            else stCnt.push(stCnt.pop()+1);
        }
        while(!st.isEmpty() && stCnt.peek() >= 3){
            st.pop();
            stCnt.pop();
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            char c = st.pop();
            int cnt = stCnt.pop();
            for(int i = 0; i < cnt; i++) sb.append(c);
        }
        return sb.reverse().toString();
    }
    class State{
        String board;
        String hand;
        int step;
        public State(String board, String hand, int step){
            this.board = board;
            this.hand = hand;
            this.step = step;
        }
    }
}
