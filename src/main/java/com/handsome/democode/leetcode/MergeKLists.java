package com.handsome.democode.leetcode;

import java.util.PriorityQueue;

public class MergeKLists {

    public static void main(String[] args) {
        ListNode[] arr = new ListNode[3];
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        arr[0] = n1;
        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(4);
        n4.next = n5;
        n5.next = n6;
        arr[1] = n4;
        ListNode n7 = new ListNode(2);
        ListNode n8 = new ListNode(6);
        n7.next = n8;
        arr[2] = n7;
        new MergeKLists().mergeKLists(arr);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);

        ListNode head = new ListNode();
        ListNode p = head;

        for(ListNode node : lists){
            heap.offer(node);
        }

        while(!heap.isEmpty()){
            ListNode cur_min = heap.poll();
            p.next = cur_min;
            p = p.next;
            if(cur_min.next != null) {
                heap.offer(cur_min.next);
            }
            cur_min.next = null;
        }
        return head.next;
    }
}
