package com.ac;

import com.trie.Trie;

import java.util.*;

/**
 * AC自动机
 */
public class AhoCorasick {
    private AhoCorasick.AcNode root = new AhoCorasick.AcNode('/'); // 存储无意义字符


    public static void main(String args[]) {
        AhoCorasick ahoCorasick = new AhoCorasick();
        List<String> stringList = Arrays.asList("最厉害", "最棒", "世界第一", "世界最棒");
        //构建Trie树
        ahoCorasick.buildTrie(stringList);
        //构建fail数组
        ahoCorasick.buildFailurePointer();

    }

    public void buildTrie(List<String> stringList) {
        for (String str : stringList) {
            buildTrie(str.toCharArray());
        }
    }

    public void buildTrie(char[] text) {
        AcNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                AcNode newNode = new AcNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    /**
     * 每一层的每一个char是一个AcNode，比如hello、hi 会生成6个AcNode
     */

    public class AcNode {
        public char data;
        public AcNode[] children = new AcNode[26]; // 字符集只包含a~z这26个字符
        public boolean isEndingChar = false; // 结尾字符为true
        public int length = -1; // 当isEndingChar=true时，记录模式串长度
        public AcNode fail; // 失败指针

        public AcNode(char data) {
            this.data = data;
        }
    }
}
