package com.linkedList.u0201;

/**
 * 变形题 1：给定一个链表的头结点 head,以及两个整数 from 和 to ,在链表上把第 from 个节点和第 to 个节点这一部分进行翻转。例如：给定如下链表，from = 2, to = 4 head-->5-->4-->3-->2-->1 将其翻转后，链表变成 head-->5--->2-->3-->4-->1
 */

public class ChangeFirst {
    public static void main(String args[]) {
        YLinkedList yLinkedList = new YLinkedList();
        int arr[] = {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            yLinkedList.headInsert(arr[i]);
        }
        yLinkedList.printList();
        System.out.println("翻转后");
        yLinkedList.iterationInvertLinkedList(0, 3);
        yLinkedList.printList();
    }
}
