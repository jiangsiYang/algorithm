package com.linkedList.u0201;

/**
 * 给定数组 1，2，3，4 构造成如下链表 head-->4---->3---->2---->1
 */

public class FirstTask {
    public static void main(String args[]) {
        YLinkedList yLinkedList = new YLinkedList();
        int arr[] = {1, 2, 3, 4};
        for (int i = 0; i < arr.length; i++) {
            yLinkedList.headInsert(arr[i]);
        }
        yLinkedList.printList();
    }
}
