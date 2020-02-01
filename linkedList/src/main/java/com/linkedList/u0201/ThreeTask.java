package com.linkedList.u0201;

/**
 * 链表翻转
 * 给定链表 head-->4--->3-->2-->1，将其翻转成 head-->1-->2-->3-->4 (使用递归)
 */

public class ThreeTask {
    public static void main(String args[]) {
        YLinkedList yLinkedList = new YLinkedList();
        int arr[] = {1, 2, 3, 4};
        for (int i = 0; i < arr.length; i++) {
            yLinkedList.headInsert(arr[i]);
        }
        yLinkedList.printList();
        System.out.println("翻转后");
        yLinkedList.invertLinkedList();
        yLinkedList.printList();
    }
}
