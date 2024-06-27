package Leetcode0023;

import java.util.PriorityQueue;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
public class Leetcode0023_1 {
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n == 0) return null;
        ListNode ans = new ListNode(-1);
        ListNode head = ans;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for(int i = 0; i < n; i++) {
            if(lists[i] != null) pq.add(lists[i]);
        }
        while (!pq.isEmpty()){
            ListNode node = pq.poll();
            ans.next = node;
            ans = ans.next;
            if(node.next != null) pq.add(node.next);
        }
        return head.next;
    }
}
