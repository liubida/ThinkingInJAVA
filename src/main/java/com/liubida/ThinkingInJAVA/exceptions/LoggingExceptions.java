/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-5-3
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
package com.liubida.ThinkingInJAVA.exceptions;

/**
 * @author liubida
 *
 */
import java.util.logging.*;
import java.io.*;

import static com.liubida.ThinkingInJAVA.util.Print.*;

@SuppressWarnings("serial")
class LoggingException extends Exception {
    private static Logger log = Logger.getLogger("LoggingException");

    public LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        log.severe(trace.toString());
    }

    public static void Log(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        log.severe(trace.toString());
    }
}
class Logging{
    private static Logger log = Logger.getLogger("Logging");

    public static void Log(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        log.severe(trace.toString());
    }
}

public class LoggingExceptions {
    public static void main(String[] args) throws Exception {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
        }
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            Logging.Log(e);
        }
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            print(e.getStackTrace().length);
            for (StackTraceElement ste : e.getStackTrace()) {
                print(ste);
            }
        }    
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            print("i caught an exception, then i rethrow it");
            throw e;
        }
        catch (Exception e) {
            print("can i caught the exception throw by upstair ?");
        } 
        finally{
            print("i'm liubida");
        }

    }
}
