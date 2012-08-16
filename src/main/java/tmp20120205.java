/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
/**
 * @author liubida 2012-2-5 上午10:45:29
 */
class A20120205 {

    protected int method1(int a, int b) throws Exception {
        return 0;
    }
}

public class tmp20120205 extends A20120205 {

    protected int method1(int a, int b) {
        return 0;
    }

    public static String transfer(long n) {

        final char[] index = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十' };
        String value = String.valueOf(n);
        char[] array = value.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            sb.append(index[array[i] - '0']);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(transfer(107631223));
    }
    // public int method1(int a, int b) { return 0; }
    // private int method1(int a, int b) { return 0; }
    // private int method1(int a, long b) { return 0; }
    // protected int method1(int a, int b) { return 0; }
}

class Outer {

    public void someOuterMethod() {
        new Inner();
        new Outer.Inner();
    }

    public class Inner {
    }

    public static void main(String[] argv) {
        Outer o = new Outer();
        o.new Inner();
    }
}
