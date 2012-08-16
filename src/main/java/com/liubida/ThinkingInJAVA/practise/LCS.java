/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.practise;

/**
 * @author liubida 2012-3-9 下午1:02:26
 */
public class LCS {

    private char[] x = null;
    private char[] y = null;

    public LCS(String a, String b){
        x = new char[a.length() + 1];
        y = new char[b.length() + 1];
        System.arraycopy(a.toCharArray(), 0, x, 1, a.length());
        System.arraycopy(b.toCharArray(), 0, y, 1, b.length());
    }

    public void getLCS() {
        int[][] l = new int[x.length + 1][y.length + 1];

        for (int i = 1; i < x.length; i++) {
            for (int j = 1; j < y.length; j++) {
                if (x[i] == y[j]) {
                    l[i][j] = l[i - 1][j - 1] + 1;
                } else {
                    l[i][j] = max(l[i - 1][j], l[i][j - 1]);
                }
            }
        }

        int i = x.length - 1;
        int j = y.length - 1;
        String s = "";
        while (i >= 1 && j >= 1) {
            if (x[i] == y[j]) {
                s = x[i] + s;
                i--;
                j--;
            } else {
                if (l[i - 1][j] < l[i][j - 1]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        System.out.println("最长公共子序列为：" + s + " [length=" + s.length() + "]");
    }

    private int max(int m, int n) {
        return m > n ? m : n;
    }

    public static void main(String[] args) {
        LCS lcs = new LCS("ABCDEFG", "ACDBEF");
        lcs.getLCS();
    }
}
