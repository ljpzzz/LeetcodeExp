package Leetcode1206;

public class Leetcode1206_1 {
    int MAX_LEVEL = 16; //从1到16
    Node head;
    int level; //从1到16
    public Leetcode1206_1() {
        head = new Node(0, MAX_LEVEL);
        level = 1;
    }

    public boolean search(int target) {
        Node current = head;
        for(int i = level-1; i >= 0; i--){
            while(current.next[i] != null && current.next[i].num < target){
                current = current.next[i];
            }
            //注意这里要比较的是current的后续节点，而不是current
            if(current.next[i] != null && current.next[i].num == target) return true;
        }
        return false;
    }

    public void add(int num) {
        Node current = head;
        //寻找需要更新的前置节点
        Node[] update = new Node[MAX_LEVEL];
        for(int i = level-1; i >= 0; i--){
            while(current.next[i] != null && current.next[i].num <= num){
                current = current.next[i];
            }
            update[i] = current;
        }
        java.util.Random random = new java.util.Random();
        int numLevel = random.nextInt(MAX_LEVEL)+1;
        //最大level增加
        if(numLevel > level){
            // 由于最大level增加，前置节点也需要增加
            for(int i = level; i < numLevel; i++){
                update[i] = head;
            }
            level = numLevel;
        }
        //创建节点
        Node newNode = new Node(num, numLevel);
        //每一层都插入到对应位置,注意这里是numLevel，而不是整体的level
        for(int i = 0; i < numLevel; i++){
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    public boolean erase(int num) {
        Node current = head;
        //寻找需要删除节点的前置节点
        Node[] update = new Node[MAX_LEVEL];
        for(int i = level-1; i >= 0; i--){
            while(current.next[i] != null && current.next[i].num < num){
                current = current.next[i];
            }
            update[i] = current;
        }
        current = current.next[0];
        //根本找不到num
        if(current == null || current.num > num) return false;
        //更新删除后的跳表
        for(int i = 0; i < level; i++){
            if(update[i].next[i] == current){
                update[i].next[i] = current.next[i];
            }
        }
        //查看level是否需要缩减
        while(level > 1 && head.next[level-1] == null){
            level--;
        }
        return true;
    }
    class Node{
        public int num;
        public int nodeLevel;
        public Node[] next;
        public Node(int val, int level){
            num = val;
            nodeLevel = level;
            next = new Node[MAX_LEVEL];
        }
    }
}
