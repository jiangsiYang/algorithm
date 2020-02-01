package com.linkedList.u0201;

public class YLinkedList {
    //头节点（又叫哨兵结点，指向第一个结点）
    Node head = new Node(0);

    /**
     * 尾插法
     *
     * @param val
     */
    public void addNode(int val) {
        Node currentNode = head;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        Node node = new Node(val);
        currentNode.setNext(node);
    }

    /**
     * 头插法
     *
     * @param val
     */
    public void headInsert(int val) {
        Node nextNode = head.getNext();
        Node newNode = new Node(val);
        head.setNext(newNode);
        newNode.setNext(nextNode);
    }

    /**
     * 单向链表，删除指定的结点（知道指定结点的指针）
     * 非链表的尾结点，可以做到O(1)，若是尾结点，则是O(n)
     *
     * @param delNode
     */
    public void removeSelectedNode(Node delNode) {
        //如果不是尾结点，可以通过把后一个结点的值赋给指定的结点，然后删掉后一个结点，实现“狸猫换太子”
        if (delNode.getNext() != null) {
            delNode.setData(delNode.getNext().getData());
            delNode.setNext(delNode.getNext().getNext());
        } else {
            //若是尾结点，只能从头开始遍历了
            Node node = head.getNext();
            while (node.getNext() != null && node.getNext() != delNode) {
                node = node.getNext();
            }
            node.setNext(null);
        }
    }

    /**
     * 通过下标返回链表的某一结点
     *
     * @param index
     * @return
     */
    public Node getNodeByIndex(int index) {
        int i = 0;
        Node node = head.getNext();
        while (i < index) {
            node = node.getNext();
            i++;
        }
        return node;
    }

    public void printList() {
        Node node = head.getNext();
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }
}
