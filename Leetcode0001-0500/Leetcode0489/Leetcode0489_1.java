package Leetcode0489;

import java.util.*;


  // This is the robot's control interface.
  // You should not implement it, or speculate about its implementation
  interface Robot {
     // Returns true if the cell in front is open and robot moves into the cell.
     // Returns false if the cell in front is blocked and robot stays in the current cell.
     public boolean move();

     // Robot will stay in the same cell after calling turnLeft/turnRight.
     // Each turn will be 90 degrees.
     public void turnLeft();
     public void turnRight();

     // Clean the current cell.
     public void clean();
  }


public class Leetcode0489_1 {
    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    Set<Integer> vis = new HashSet<>();
    Robot robot;
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        dfs(0, 0, 0);
    }
    public void dfs(int x, int y, int dir){
        vis.add(hash(x, y));
        robot.clean();
        for(int i = 0; i < 4; i++){
            int d = (dir+i)%4;
            int x2 = x + dirs[d][0];
            int y2 = y + dirs[d][1];
            if(!vis.contains(hash(x2, y2))){
                if(robot.move()){
                    dfs(x2, y2, d);
                    //以下为回溯流程
                    //掉头
                    robot.turnRight();
                    robot.turnRight();
                    robot.move();
                    //重新掉头为最初的方向
                    robot.turnRight();
                    robot.turnRight();
                }
            }
            robot.turnRight();
        }
    }
    public  int hash(int x, int y){
        return (x+101)*401+(y+201);
    }
}
