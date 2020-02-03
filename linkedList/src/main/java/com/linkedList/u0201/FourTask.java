package com.linkedList.u0201;

/**
 * 迭代解法：链表翻转
 * 给定链表 head-->4--->3-->2-->1，将其翻转成 head-->1-->2-->3-->4 (使用迭代)
 * 时间复杂度O(n),另外由于没有额外的空间使用，也未像递归那样调用递归函数不断压栈，所以空间复杂度是 O(1),对比递归，显然使用迭代的方式来处理更佳!
 */

public class FourTask {
    public static void main(String args[]) {
        YLinkedList yLinkedList = new YLinkedList();
        int arr[] = {1, 2, 3, 4};
        for (int i = 0; i < arr.length; i++) {
            yLinkedList.headInsert(arr[i]);
        }
        yLinkedList.printList();
        System.out.println("翻转后");
        yLinkedList.iterationInvertLinkedList();
        yLinkedList.printList();
    }
}
