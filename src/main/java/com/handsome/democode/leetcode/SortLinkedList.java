package com.handsome.democode.leetcode;

public class SortLinkedList {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(1);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode listNode = new SortLinkedList().sortList(listNode1);
        System.out.println(listNode);
    }

    public ListNode sortList(ListNode head) {
        //原地排序， 冒泡
        ListNode nh = new ListNode();
        nh.next = head;
        ListNode p = nh;
        ListNode end = null;
        while(p.next != end){
            while(p.next != end && p.next.next != end){
                ListNode left = p.next;
                ListNode right = p.next.next;
                if(left.val > right.val) {
                    p.next = right;
                    left.next = right.next;
                    right.next = left;
                }
                p = p.next;
            }
            end = p.next;
            p = nh;
        }
        return nh.next;
    }
}
