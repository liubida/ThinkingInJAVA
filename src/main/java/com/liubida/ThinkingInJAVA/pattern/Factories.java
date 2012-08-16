/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-4-27
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
package com.liubida.ThinkingInJAVA.pattern;

/**
 * @author liubida
 *
 */
interface Trace {
    // turn on and off debugging
    public void setDebug(boolean debug);

    // write out a debug message
    public void debug(String message);

    // write out an error message
    public void error(String message);
}

class FileTrace implements Trace {
    private java.io.PrintWriter pw;
    private boolean             debug;

    public FileTrace() throws java.io.IOException {
        // a real FileTrace would need to obtain the filename somewhere
        // for the example I'll hardcode it
        pw = new java.io.PrintWriter(new java.io.FileWriter("trace.log"));
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void debug(String message) {
        if (debug) { // only print if debug is true
            pw.println("DEBUG: " + message);
            pw.flush();
        }
    }

    public void error(String message) {
        // always print out errors
        pw.println("ERROR: " + message);
        pw.flush();
    }
    
    /**
     * 声明一个匿名类A，A继承自TracFactoryI，并且A实现了其getTrace的method
     * 创建了A的实例：factory
     * 
     * Create an object of an anonymous class that’s 
     * inherited from TraceFactoryI
     */
    public static TraceFactoryI factory = new TraceFactoryI(){
        public Trace getTrace(){
            try {
                return new FileTrace();
            } catch (java.io.IOException e) {
                Trace t = new SystemTrace();
                t.error("could not instantiate FileTrace:" + e.getMessage());
                return t;
            }
        }
    };
}

class SystemTrace implements Trace {
    private boolean debug;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public void debug(String message) {
        if (debug) { // only print if debug is true
            System.out.println("DEBUG: " + message);
        }
    }

    public void error(String message) {
        // always print out errors
        System.out.println("ERROR: " + message);
    }   

    /**
     * 声明一个匿名类A，A继承自TracFactoryI，并且A实现了其getTrace的method
     * 创建了A的实例：factory
     * 
     * Create an object of an anonymous class that’s inherited from TraceFactoryI
     */
    public static TraceFactoryI factory = new TraceFactoryI(){
        public Trace getTrace(){
            return new SystemTrace();
        }
    };
}

class TraceFactoryC {
    public static Trace getTrace() {
        /**
         * 通过这个Trace工厂，可以很方便的把后面的代码需要的Trace
         * 在SystemTrace和FileTrace两者之间切换
         */
        //option 1：
        //return new SystemTrace();

        //option 2：
        try {
            return new FileTrace();
        } catch (java.io.IOException e) {
            Trace t = new SystemTrace();
            t.error("could not instantiate FileTrace:" + e.getMessage());
            return t;
        }
    }
}

interface TraceFactoryI{
    Trace getTrace();
}
class FileTraceImpl implements TraceFactoryI{
    public Trace getTrace(){
        try {
            return new FileTrace();
        } catch (java.io.IOException e) {
            Trace t = new SystemTrace();
            t.error("could not instantiate FileTrace:" + e.getMessage());
            return t;
        }
    }
//    public static class testMain{
//        public static void main(String[] args) {
//            FileTraceImpl f = new FileTraceImpl();
//            f.getTrace();
//        }
//    }

}
class SystemTraceImpl implements TraceFactoryI{
    public Trace getTrace(){
        return new SystemTrace();
    }
}


public class Factories {
    static void testTrace(TraceFactoryI tf){
        Trace logI = tf.getTrace();
        logI.setDebug(true);
        logI.debug("this is factory pattern by interface");
    }
    public static void main(String[] args) {
        /**
         * 只用类，所以在变动时需要修改TracFactoryC的代码
         */
        Trace logC = TraceFactoryC.getTrace();
        logC.setDebug(true);
        logC.debug("this is factory pattern by class");

        /**
         * 用接口+impl实现的方法
         */
        testTrace(new SystemTraceImpl());
        testTrace(new FileTraceImpl());

        /**
         * 用内部匿名类的方法
         */
        testTrace(SystemTrace.factory);
        testTrace(FileTrace.factory);
    }

}
