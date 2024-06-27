package Leetcode0025;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class Leetcode0025_1 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode preHead = new ListNode(-1);
        ListNode curHead = null;
        ListNode ans = preHead;
        ListNode current = head;
        int count = 0;
        while (current != null){
            count++;
            current = current.next;
        }
        int grpCount = count / k;
        current = head;
        ListNode prev = null;
        int currentGrpCount = 0;
        int currentGrpNodeCount = 0;
        while(currentGrpCount < grpCount){
            currentGrpNodeCount = 0;
            while(currentGrpNodeCount < k){
                if(currentGrpNodeCount == 0) curHead = current;
                if(currentGrpNodeCount == k-1){
                    preHead.next = current;
                    preHead = curHead;
                }
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                currentGrpNodeCount++;
                if(currentGrpNodeCount == k){
                    currentGrpCount++;
                    break;
                }
            }
        }
        preHead.next = current;
        return ans.next;
    }
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(new Leetcode0025_1().reverseKGroup(head, 2));
    }
}
