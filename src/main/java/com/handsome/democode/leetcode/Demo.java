package com.handsome.democode.leetcode;

public class Demo {



    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        new Demo().reorderList(listNode1);
        System.out.println(listNode1);
    }

    public void reorderList(ListNode head) {
        //快慢指针找到中间位置
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode mid = slow;

        //找到尾节点的位置
        while(slow.next != null){
            slow = slow.next;
        }

        ListNode tail = slow;

        //把后半部分进行原地倒置,头插法
        while(mid.next != tail){
            ListNode p = mid.next;
            mid.next = p.next;
            p.next = tail.next;
            tail.next = p;
        }

        //归并， head和tail归并，此时tail是中间
        ListNode p = head, q = tail;
        ListNode head2 = new ListNode(-1);
        ListNode last = head2;
        while(p != tail && q != null){
            ListNode p_after = p.next, q_after = q.next;
            p.next = null;
            q.next = null;
            last.next = p;
            last = last.next;
            last.next = q;
            last = last.next;
            last.next = null;
            p = p_after;
            q = q_after;
        }
        if(p == tail) {
            last.next = q;
        }
        if(q == null) {
            last.next = p;
            while(last.next != tail){
                last = last.next;
            }
            last.next = null;
        }
        head = head2.next;
    }
}
