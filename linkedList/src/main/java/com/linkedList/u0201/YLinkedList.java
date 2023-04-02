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


    /**
     * 递归翻转结点链表
     * 将4->3->2->1 翻转成 4-<3-<2-<1，并将head->4 改为 head->1
     */
    public void invertLinkedList() {
        Node firNode = this.invertLinkedList(head.getNext());
        head.setNext(firNode);
    }

    /**
     * 递归翻转结点 node 开始的链表
     * 将x->y-> 翻转成 y->x->null ，并返回第一个结点
     *
     * @param node
     * @return
     */
    private Node invertLinkedList(Node node) {
        if (node.getNext() == null) {
            //当是尾结点时，返回尾结点到最顶层，需要重新设置head.next 等于尾结点
            return node;
        }

        Node firNode = invertLinkedList(node.getNext());
        //将x->y 翻转成x->y->x(这一步是个环状)
        node.getNext().setNext(node);
        //避免成环状,要将x->y 断掉,改为x->null
        node.setNext(null);
        //不断向上返回尾结点
        return firNode;
    }

    /**
     * 迭代解法 翻转链表
     * 将x->y-> 翻转成 y->x->null ，并返回第一个结点
     */
    public void iterationInvertLinkedList() {
        //定义两个节点：pre, cur ，其中 cur 是 pre 的后继结点
        Node preNode = head.getNext();
        Node curNode = preNode.getNext();
        preNode.setNext(null);

        //注意，只有原本的第一个结点的后继结点才需要设置为null，所以不能用perNode作为循环开始，而是在上面就设置preNode.next为null
        while (curNode != null) {
            /**
             * 务必注意：在 cur 指向 pre 之前一定要先保留 cur 的后继结点，不然 cur 指向 pre 后就再也找不到后继结点了
             * 也就无法对 cur 后继之后的结点进行翻转了
             */
            Node nodeNext = curNode.getNext();
            curNode.setNext(preNode);
            preNode = curNode;
            curNode = nodeNext;
        }
        //最后重新设置头结点的next
        head.setNext(preNode);
    }


    /**
     * 将链表指定区间的结点翻转，如 5->4->3->2->1,指定4~2翻转，结果为5->2->3->4->1
     *
     * @param fromIndex
     * @param toIndex
     */
    public void iterationInvertLinkedList(int fromIndex, int toIndex) {
        //fromIndex的前一个node，最后需要设置这个node的next为翻转后的第一个node，所以需要记录下来
        Node firNodePreNode = head;
        //fromIndex对应的node
        Node findFirNode = firNodePreNode.getNext();
        if (fromIndex > 0) {
            //如果非从头翻转，就要找到不断向下寻找到对应的那个Node
            int firNodePreIndex = fromIndex - 1;
            firNodePreNode = head.getNext();
            findFirNode = firNodePreNode.getNext();
            //先找到fromIndex对应的那个node已经这个node的前一个node
            while (firNodePreIndex > 0) {
                findFirNode = findFirNode.getNext();
                firNodePreIndex--;
            }
        }


        //开始翻转指定区间的链表
        Node preNode = findFirNode;
        Node curNode = findFirNode.getNext();
        preNode.setNext(null);
        //确定翻转的长度
        int replaceLength = toIndex - fromIndex;
        while (replaceLength > 0) {
            Node nextNode = curNode.getNext();
            curNode.setNext(preNode);
            preNode = curNode;
            curNode = nextNode;
            replaceLength--;
        }
        //设置fromIndex 前一个 node的next为翻转后的第一个node
        firNodePreNode.setNext(preNode);
        //将fromIndex对应的node的next设置为翻转区间翻转前的最后一个node的next，这样才会把整个链表连接起来了
        findFirNode.setNext(curNode);

    }

    public void iterationInvertLinkedListEveryK(int k) {
        Node startNode = head.getNext();
        Node endNode = head.getNext();

        Node preNode = startNode;
        Node curNode = endNode;
        while (endNode != null) {
            int step = 0;
            while (step < k - 1) {
                Node nextNode = curNode.getNext();
                preNode.setNext(null);
                curNode.setNext(preNode);

                preNode = curNode;
                curNode = nextNode;
                step++;
            }

            endNode = endNode.getNext();
        }


    }
}
