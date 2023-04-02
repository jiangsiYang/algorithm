package com.linkedList.u0201;

/**
 * 变形题 2：给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * <p>
 * 示例 : 给定这个链表：head-->1->2->3->4->5 当 k = 2 时，应当返回: head-->2->1->4->3->5 当 k = 3 时，应当返回: head-->3->2->1->4->5 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class ChangeSecond {
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
