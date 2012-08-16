/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.practise;

/**
 * @author liubida 2012-2-14 下午4:29:19
 */
public class Prime {

    boolean isPrime1(int n) {
        if (n <= 1) return false;

        for (int i = 2; i < n - 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    boolean isPrime2(int n) {
        if (n <= 1) return false;
        int b = (int) Math.sqrt(n);

        for (int i = 2; i <= b; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void listPrime1(int n) {
        // false为质数
        boolean[] list = new boolean[n + 1];
        int p;

        for (int i = 2; i <= n; i++) {
            if (!list[i]) {
                p = i + i;
                while (p <= n) {
                    list[p] = true;
                    p += i;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (!list[i]) {
                // System.out.print(i);
                // System.out.print(" ");
            }
        }
    }

    void listPrime(int n) {
        boolean[] list = new boolean[n + 1];
        int p;
        for (int i = 2; i <= n; i++) {
            if (!list[i]) {
                p = i * i;
                if (p > n) break;
                if (i > 2) {
                    while (p <= n) {
                        list[p] = true;
                        p = p + i + i;
                    }
                } else {
                    while (p <= n) {
                        list[p] = true;
                        p += i;
                    }
                }
            }
        }
    }

    static void listPrime2(int n) {
        boolean[] list = new boolean[n + 1];
        int p;

        for (int i = 2; i <= n; i++) {
            if (!list[i]) {
                p = i * i;
                if (p > n) break;
                if (i > 2) {
                    while (p <= n) {
                        list[p] = true;
                        p = p + i + i;
                    }
                } else {
                    while (p <= n) {
                        list[p] = true;
                        p += i;
                    }
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!list[i]) {
                // System.out.print(i);
                // System.out.print(" ");
            }
        }
    }

    public static void main(String[] args) {
        long c = System.currentTimeMillis();
        listPrime2(10000000);
        System.out.println(System.currentTimeMillis() - c);
        // new Prime().listPrime1(1000);
        // System.out.println();
        // new Prime().listPrime2(1000);
    }
}
