/*
 * Copyright 2011 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.practise;

import java.nio.ByteOrder;

/**
 * @author liubida 2011-11-9 上午9:28:34
 */
public class tip {

    static int getFactor(int a, int b) {
        if (a >= b && 0 == a % b) {
            return b;
        }
        if (a < b && 0 == b % a) {
            return a;
        }
        return a > b ? getFactor(a, a % b) : getFactor(b, b % a);
    }

    static void buildHeap2(int[] a, int len, int index) {
        int lIndex = (index << 1) + 1;
        int rIndex = lIndex + 1;
        int max = index;
        int tmp;

        if (lIndex < len) {
            max = a[index] > a[lIndex] ? index : lIndex;
        }
        if (rIndex < len) {
            max = a[max] > a[rIndex] ? max : rIndex;
        }
        if (max != index) {
            tmp = a[index];
            a[index] = a[max];
            a[max] = tmp;
            buildHeap2(a, len, max);
        }
    }

    static int[] heapSort2(int[] a) {
        int len = a.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            buildHeap2(a, len, i);
        }

        int tmp;
        for (int i = len - 1; i > 0; i--) {
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            buildHeap2(a, i, 0);
        }
        return a;
    }

    static int find(int[] a) {
        int time = 0;
        int key = 0;

        for (int i = 0; i < a.length; i++) {
            if (0 == time) {
                key = a[i];
                time++;
            } else {
                if (key == a[i]) {
                    time++;
                } else {
                    time--;
                }
            }
        }
        return key;
    }

    static boolean IsAllExisted(String chunk, String[] keywords) {
        for (String s : keywords) {
            if (!chunk.contains(s)) {
                return false;
            }
        }
        return true;
    }

    static String extractSummary(String description, String[] keywords) {
        int i = 0;
        int left = 0;
        int right = i;
        int len = description.length();
        String best = new String(description);
        while (true) {
            for (i = left + 1; i < len; i++) {
                if (IsAllExisted(description.substring(left, i), keywords)) {
                    break;
                }
            }
            right = i;
            while (left < right && IsAllExisted(description.substring(left + 1, right), keywords)) {
                left++;
            }
            if (description.substring(left, right).length() < best.length()) {
                best = new String(description.substring(left, right));
                System.out.println(best);
            }
            if (right >= len) {
                break;
            }
            left++;
        }
        return best;
    }

    static boolean isExist(String des, String[] keys) {
        for (String s : keys) {
            if (!des.contains(s)) {
                return false;
            }
        }
        return true;
    }

    static String exeSum(String description, String[] keys) {
        int i = 0;
        int left = 0;
        int right = 0;
        int len = description.length();
        String best = new String(description);
        while (true) {
            for (i = left + 1; i < len; i++) {
                if (isExist(description.substring(left, i), keys)) {
                    break;
                }
            }
            right = i;
            while (left < right && isExist(description.substring(left + 1, right), keys)) {
                left++;
            }
            if (right - left < best.length()) {
                best = new String(description.substring(left, right));
                System.out.println(best);
            }
            if (right >= len) {
                break;
            }
            left++;
        }
        return best;
    }

    static int abs(int a) {
        return (1 - 2 * (a >>> 31)) * a;
    }

    static int[] reverse1(int[] a) {
        int len = a.length;
        int tmp;

        for (int i = 0; i <= len / 2 - 1; i++) {
            tmp = a[i];
            a[i] = a[len - i - 1];
            a[len - i - 1] = tmp;
        }
        return a;
    }

    static int[] reverse2(int[] a) {
        int len = a.length;

        for (int i = 0; i <= len / 2 - 1; i++) {
            a[i] = a[i] ^ a[len - i - 1];
            a[len - i - 1] = a[i] ^ a[len - i - 1];
            a[i] = a[i] ^ a[len - i - 1];
        }
        return a;
    }

    static int[] reverse(int[] a) {
        int len = a.length;
        // for (int i = 0; i < len / 2; i++) {
        // a[i] = a[i] + a[len - 1 - i];
        // a[len - 1 - i] = a[i] - a[len - 1 - i];
        // a[i] = a[i] - a[len - 1 - i];
        // }
        for (int i = 0; i < len / 2; i++) {
            a[i] = a[i] ^ a[len - 1 - i];
            a[len - 1 - i] = a[i] ^ a[len - 1 - i];
            a[i] = a[i] ^ a[len - 1 - i];
        }
        return a;
    }

    static char[] reverse(char[] a, int start, int end) {
        int len = a.length;
        char c = 0;
        while (start < end && end < len) {
            c = a[start];
            a[start++] = a[end];
            a[end--] = c;
        }

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
        return a;
    }

    static char[] leftRotate(char[] a, int n) {
        n = n % (a.length);
        reverse(a, 0, n - 1);
        reverse(a, n, a.length - 1);
        reverse(a, 0, a.length - 1);
        return a;
    }

    static void buildHeap1(int[] a, int len, int index) {
        int lIndex = (index << 1) + 1;
        int rIndex = lIndex + 1;
        int maxIndex = index;
        int tmp = 0;

        if (lIndex < len) {
            maxIndex = a[lIndex] > a[index] ? lIndex : index;
        }
        if (rIndex < len) {
            maxIndex = a[rIndex] > a[maxIndex] ? rIndex : maxIndex;
        }

        if (maxIndex != index) {
            tmp = a[index];
            a[index] = a[maxIndex];
            a[maxIndex] = tmp;

            buildHeap1(a, len, maxIndex);
        }
    }

    static boolean isLittleEndian() {
        int a = 0x12345678;
        char c = (char) a;
        if (c == 0x5678) {
            return true;
        } else {
            return false;
        }

    }

    static int[] heap(int[] a) {
        int len = a.length;
        int tmp = 0;
        for (int i = len / 2 - 1; i >= 0; i--) {
            buildHeap1(a, len, i);
        }
        for (int i = len - 1; i > 0; i--) {
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;

            buildHeap1(a, i, 0);
        }
        return a;
    }

    static int[] bubbleSort(int[] a) {
        int len = a.length;
        int tmp;
        boolean flag = true;
        for (int i = 0; i < len; i++) {
            flag = true;
            for (int j = 0; j < len - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    flag = false;
                    tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
            if (flag) {
                break;
            }
        }
        return a;
    }

    static int[] quickSort(int[] a, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int key = a[i];

            while (i < j) {
                while (i < j && key <= a[j])
                    j--;
                a[i] = a[j];
                while (i < j && key >= a[i])
                    i++;
                a[j] = a[i];
            }
            a[i] = key;
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        }
        return a;
    }

    static void print(int[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println();
    }

    static void buildHeap(int[] a, int len, int index) {
        int lIndex = (index << 1) + 1;
        int rIndex = lIndex + 1;
        int maxIndex = index;
        int tmp = 0;

        if (lIndex < len) {
            maxIndex = a[lIndex] > a[index] ? lIndex : index;
        }
        if (rIndex < len) {
            maxIndex = a[maxIndex] > a[rIndex] ? maxIndex : rIndex;
        }
        if (maxIndex != index) {
            tmp = a[maxIndex];
            a[maxIndex] = a[index];
            a[index] = tmp;
            buildHeap(a, len, maxIndex);
        }
    }

    static int[] heapSort(int[] a) {
        int len = a.length;
        int tmp = 0;
        // i=len/2-1 是堆的第一个非叶子节点
        for (int i = (len / 2) - 1; i >= 0; i--) {
            buildHeap(a, len, i);
        }
        for (int i = len - 1; i > 0; i--) {
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            buildHeap(a, i, 0);
        }
        return a;
    }

    static long fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        long a = 1;
        long b = 1;
        long c = 2;
        for (int i = 4; i <= n; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }

    static int binSearch(int[] a, int key) {
        int high = a.length - 1;
        int low = 0;
        int mid;
        while (low < high) {
            mid = (low + high) >>> 2;
            if (key == a[mid]) {
                return mid;
            } else {
                if (key < a[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String content = "上述思245路二相比于思23路一";
        String[] keys = { "思", "路" };
        // String content =
        // "上述思路二相比于思路一，很明显提高了不小效率。我们在匹配的过程中利用了可以省去其中某些死板的步骤，这让我想到了KMP算法的匹配过程。同样是经过观察，比较，最后总结归纳出的高效算法。我想，一定还有更好的办法，只是我们目前还没有看到，想到，待我们去发现，创造";
        // String[] keys = { "思路", "我们" };
        // extractSummary(content, keys);
        // exeSum(content, keys);
        // System.out.println(abs(100));
        int[] a = { 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 12 };
        int[] b = { 1, 5, 3, 7, 5, 3, 7, 12, 9 };
        // print(bubbleSort(a));
        // reverse(a);
        // print(quickSort(a, 0, a.length - 1));
        // reverse(a);
        // print(a);
        // print(heapSort2(b));
        // System.out.println(0 % 7);
        // print(heap(b));
        // for (int i = 1; i < 20; i++) {
        // System.out.print(fib(i) + " ");
        // }
        System.out.println(isLittleEndian());
        System.out.println(ByteOrder.nativeOrder());
        System.out.println(fib(5));
        // int[] c = { 1, 2, 3, 4, 5, 6, 7 };
        // print(reverse1(c));
        // print(reverse2(c));
        // String b1 = "12345678";
        // System.out.println(b1);
        // leftRotate(b1.toCharArray(), 13);
        // new test().test();
    }
}
