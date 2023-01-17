package com.handsome.democode.leetcode;

public class DeleteDuplicates {

    public static void main(String[] args) {
//        ListNode node7 = new ListNode(5);
//        ListNode node6 = new ListNode(4, node7);
//        ListNode node5 = new ListNode(4, node6);
//        ListNode node4 = new ListNode(3, node5);
//        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(1, null);
        ListNode node1 = new ListNode(1, node2);
        ListNode listNode = new DeleteDuplicates().deleteDuplicates(node1);
        System.out.println(listNode);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode h = new ListNode();
        h.next = head;
        return delete(h);
    }

    public ListNode delete(ListNode head){
        ListNode current = head;
        ListNode p = head.next;
        ListNode p_front = head;

        int currentNum = p.val;
        int count = 0;


        while(p != null) {
            if(count == 0) {
                currentNum = p.val;
                count = 1;
                p_front = p;
                p = p.next;
                continue;
            }
            if(currentNum == p.val) {
                count ++;
            } else {
                if(count == 1) {
                    current = p_front;
                } else if(count > 1) {
                    current.next = p_front.next;
                    //删除
                    p_front.next = null;
                }
                currentNum = p.val;
                count = 1;
            }
            p_front = p;
            p = p.next;
        }

        return head.next;
    }
}
