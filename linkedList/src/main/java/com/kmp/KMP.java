package com.kmp;

public class KMP {
    public static void main(String[] args) {
        // 待匹配的文本
//        String text = "ababaabaabac";
        String text = "bbbaaaaabcabdddabcabcc";
        // 要查找的模式串
//        String pattern = "abaabac";
        String pattern = "abcabdddabcabc";

        // 在文本中查找模式串
        int index = kmp(text, pattern);

        // 输出查找结果
        if (index != -1) {
            System.out.println("Pattern found at index " + index);
        } else {
            System.out.println("Pattern not found");
        }
    }

    /**
     * KMP 算法实现
     *
     * @param text    待匹配的文本
     * @param pattern 要查找的模式串
     * @return 如果模式串在文本中出现，则返回模式串在文本中的起始位置；否则返回 -1。
     */
    public static int kmp(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        // next 数组记录模式串中每个位置的最长相同前缀后缀的长度
        int[] next = getNextImproved(pattern);
        //pos 模式串中将要匹配的位置
        int pos = 0;
        //tar 主串中将要匹配的位置
        int tar = 0;
        for (tar = 0; tar < n; tar++) {
            // 不匹配时回溯，这里也是个小循环，直到pos=0才跳出
            while (pos > 0 && text.charAt(tar) != pattern.charAt(pos)) {
                pos = next[pos];
            }
            // 匹配时 pos 加 1
            if (text.charAt(tar) == pattern.charAt(pos)) {
                pos++;
            }
            // 如果匹配成功，则返回模式串在文本中的起始位置
            if (pos == m) {
                return tar - m + 1;
            }
        }
        // 没有找到模式串，返回 -1
        return -1;
    }

    /**
     * 这个方法的复杂度貌似是O（n^2），快速构建next数组是核心，可以通过dp的思维降到O（n）,参考getNextImproved
     *
     * @param pattern
     * @return
     */
    private static int[] getNext(String pattern) {
        int m = pattern.length();
        // next 数组记录模式串中每个位置的最长相同前缀后缀的长度
        int[] next = new int[m];
        int now = 0;

        // 构建 next 数组
        for (int x = 1; x < m; x++) {
            //若P[x]！=P[now],则将now缩小为，now = next[now-1]
            while (now > 0 && pattern.charAt(x) != pattern.charAt(now)) {
                now = next[now - 1];
            }
            // 如果P[x]==P[now],next[x]=now+1
            if (pattern.charAt(x) == pattern.charAt(now)) {
                now++;
            }
            next[x] = now;
        }

        return next;
    }

    /**
     * 已经知道了 next[x-1]（以下记为now），
     * 如果 P[x] 与 P[now] 一样，那最长相等前后缀的长度就可以扩展一位，很明显 next[x] = now + 1;
     * 如果当P[now]与P[x]不相等的时候，我们需要缩小now——把now变成next[now-1]，直到P[now]=P[x]为止。P[now]=P[x]时，就可以直接向右扩展了
     *
     * @param p
     * @return
     */
    private static int[] getNextImproved(String p) {
        // next 数组记录模式串中每个位置的最长相同前缀后缀的长度
        int[] next = new int[p.length()];
        //now的含义看方法注释
        int now = 0;
        int x = 1;

        while (x < p.length()) {
            if (p.charAt(x) == p.charAt(now)) {
                now++;
                next[x] = now;
                x++;
            } else if (now > 0) {
                //这里不断地去缩减now的值，直到满足P[x] 与 P[now]
                now = next[now - 1];
            } else {
                //实在没有匹配的now 能满足 P[x]=P[now]
                x++;
                next[x] = now;
            }
        }
        return next;

//        for (int x = 1; x < m; x++) {
//            if (p.charAt(x) == p.charAt(now)) {
//                next[x] = now + 1;
//            } else {
//                //不能用for循环的原因就是now其实也是不断去缩小的，直到满足next[x] = now + 1 或者 now=0
//                now = next[now - 1];
//            }
//        }
    }
}



