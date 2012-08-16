
/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-8-22
 * $Id$
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */

/**
 * @author liubida
 */
public class tmp20110822_1 {
    public static void main(String[] args) {
        //        Class<? extends Test> classTest = Test.class;
        //
        //        List<Method> testMethodList = new ArrayList<Method>();
        //        for (Method m : classTest.getMethods()) {
        //            String tmp = m.getName();
        //            if (tmp.startsWith("test_")) {
        //                testMethodList.add(m);
        //                //                System.out.println(m.toGenericString());
        //            }
        //        }
        //        for (Method m : testMethodList) {
        //            Object[] para = null;
        //            System.out.println(m.toGenericString() + ":   " + m.invoke(null, para));
        //        }
        try {
            for (String s : args) {
                System.out.println(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Test {
    public static int test_a() {
        int j = 0;
        try {
            j = 1;
            return j;
        } catch (Exception e) {
        } finally {
        }
        j = 3;
        return j;
    }

    public static int test_b() {
        int j = 0;
        try {
            j = 1;
            return j;
        } catch (Exception e) {
        } finally {
            j = 2;
        }
        j = 3;
        return j;
    }

    public static int test_c() throws Exception {
        int j = 0;
        try {
            j = 1;
            int i = 1 / 0;
            return j;
        } catch (Exception e) {
            throw new Exception("liubida: " + j);
        } finally {
            j = 2;
        }
    }

    public static int test_d() {
        int j = 0;
        try {
            j = 1 / 0;
            return j;
        } catch (Exception e) {
        } finally {
            j = 2;
        }
        j = 3;
        return j;
    }

    public static int test_e() {
        int j = 0;
        try {
            j = 1;
            return j;
        } catch (Exception e) {
        } finally {
            j = 2;
            return j;
        }
        //        j = 3;
        //        return j;
    }

    public static Person test_f() {
        Person p = new Person();
        try {
            p.setAge(1);
            return p;
        } catch (Exception e) {
        } finally {
            p.setAge(2);
        }
        return p;
    }
}

class Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return String.valueOf(age);
    }
}
