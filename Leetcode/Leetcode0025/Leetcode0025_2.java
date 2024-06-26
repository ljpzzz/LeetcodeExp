package Leetcode0025;

public class Leetcode0025_2 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1) return head;
        //先统计总的数量
        int n = 0;
        ListNode current = head;
        while(current != null){
            n++;
            current = current.next;
        }
        int group = n/k; //需要翻转的组数，每组k个
        int nRev = group*k; //需要翻转的节点数
        ListNode res = null; //最终用于返回的head;
        ListNode preGroupLast = null; // 前一组的最后一个节点
        ListNode curGroupLast = null; //当前组的最后节点
        ListNode curGroupFirst = null; //当前组的第一个节点
        ListNode pre = null; //current前缀节点
        current = head; //当前节点
        int count = 1; //当前处理的节点
        while(count <= nRev){
            //翻转节点
            ListNode next = current.next;
            current.next = pre;
            if(count%k == 1) curGroupLast = current;
            else if(count%k == 0) curGroupFirst = current;
            //已经凑齐一组了
            if(count%k == 0){
                curGroupFirst = current;
                //如果是第一组，更新返回的head
                if(res == null)   res = current;
                    //衔接本组和上一组
                else preGroupLast.next = curGroupFirst;
                //更新curGroupLast为下一轮的第一个节点，它翻转后会变成最后一个节点
                preGroupLast = curGroupLast;
                //为下一个周期初始化
                count++;
                pre = null;
                current = next;
            }
            else{
                //向后移动一个节点
                count++;
                pre = current;
                current = next;
            }
        }
        //有多的节点
        if(nRev < n) curGroupLast.next = current;
        return res;
    }
}
