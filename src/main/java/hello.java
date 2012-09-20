import java.io.File;
import java.io.IOException;

/**
 * Project: ThinkingInJAVA
 * File Created at 2011-9-15
 * $Id$
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */

/**
 * @author liubida
 */

interface a {}

public abstract class hello {

    public hello(){
    }

    static String handStr(String s) {
        char[] a = s.toCharArray();
        int count = 1;
        char tmp = a[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < a.length; i++) {
            if (i < a.length - 1 && tmp == a[i]) {
                count++;
            } else {
                if (count > 1) {
                    sb.append(count);
                }
                sb.append(tmp);
                count = 1;
            }
            tmp = a[i];
        }
        return sb.toString();
    }

    static char getMagic(String s) {
        char[] a = s.toCharArray();
        char tmp = a[0];
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            if (tmp == a[i])
                count++;
            else count--;
            if (count <= 0) {
                tmp = a[i];
                count = 1;
            }
        }
        return tmp;
    }

    public static void hello() {
        System.out.println("hello");
    }

    static int get() {
        return 10;
    }

    static String get1() {
        return new String("aaa");
    }

    public static void main(String[] args) throws IOException {
        // System.out.println(isPrime(561));
        // System.out.println(isEqual(9.000087, 9.000086));
        // printPrime1(10000);
        // printPrime2(10000);
        // new hello().hello();
        // System.out.println(args.length);
        // for (String s : args) {
        // System.out.println(s);
        // }
        // System.out.println(handStr("AAABBBCAAADD"));
        // System.out.println(getMagic("ABABABC"));

        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //
        // String s;
        // while ((s = reader.readLine()) != null && (s.length() != 0)) {
        // }
        //
//        String ROOT_PATH = "/home/liubida/";
//        String dirName = "aa/bb/cc";
//        File dir = new File(ROOT_PATH + dirName);
//        dir.createNewFile();
//        System.out.println("1");

        System.out.println("----------------");
        System.out.println(80.0 / 437.0);
    }

    static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        int M = (int) Math.sqrt(n);
        for (int i = 2; i <= M; i++) {
            if (n % i == 0)
                return true;
        }
        return false;
    }

    static boolean isEqual(double a, double b) {
        double min = 0.00001;
        if ((a > b ? (a - b) : (b - a)) <= min) {
            return true;
        } else {
            return false;
        }
    }

    static void printPrime1(int n) {
        int[] array = new int[n + 1];
        int j = 0;
        for (int i = 2; i <= n; i++) {
            if (0 == array[i]) {
                j = i + i;
                while (j <= n) {
                    array[j] = 1;
                    j += i;
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (0 == array[i]) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println(count);
    }

    static void printPrime2(int n) {
        boolean[] a = new boolean[n + 1];
        int j = 0;
        for (int i = 2; i <= n; i++) {
            if (!a[i]) {
                j = i * i;
                if (j > n)
                    break;
                else {
                    while (j <= n) {
                        a[j] = true;
                        j = j + (i > 2 ? 2 : 1) * i;
                    }
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!a[i]) {
                count++;
                System.out.println(i);
            }
        }
        System.out.println(count);
    }
}
