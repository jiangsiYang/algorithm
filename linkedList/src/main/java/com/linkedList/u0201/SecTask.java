package com.linkedList.u0201;

/**
 * 给定单向链表的头指针和一个节点指针，定义一个函数在 O(1) 内删除这个节点。
 */

public class SecTask {
    public static void main(String args[]) {
        YLinkedList yLinkedList = new YLinkedList();
        int arr[] = {1, 2, 3, 4};
        for (int i = 0; i < arr.length; i++) {
            yLinkedList.addNode(arr[i]);
        }

        yLinkedList.printList();

        //删除中间结点
        Node delNode = yLinkedList.getNodeByIndex(0);
        //删除最后一个结点
//        Node delNode = yLinkedList.getNodeByIndex(3);

        yLinkedList.removeSelectedNode(delNode);

        System.out.println("删除后");
        yLinkedList.printList();
    }
}
