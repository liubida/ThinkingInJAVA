/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.liubida.ThinkingInJAVA.concurrency;

/**
 * @author liubida 2012-2-20 下午4:37:49
 */


public class XL  extends Thread{  
    
    public static String[] NAMES = new String[] { "A", "B", "C" };  
  
    public static int POS = 0;  
  
    private static final long DURATION = 1000;  
      
    private int count = 10;  
  
    public XL (String name) {  
        this.setName(name);  
    }  
  
    @Override  
    public void run() {  
        while (count > 0) {  
            if (this.getName().equals(NAMES[POS])) {  
                this.print();  
                this.count--;  
            }  
            try {  
                Thread.sleep(DURATION);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    private synchronized void print() {  
        System.out.print(this.getName());  
        POS = (POS >= NAMES.length - 1 ? 0 : ++POS);  
    }  
      
    public static void main(String[] args) {  
        new XL ("A").start();  
        new XL ("B").start();  
        new XL ("C").start();  
    }  

}
