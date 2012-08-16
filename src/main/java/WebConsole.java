/**
 * Project: ThinkingInJAVA
 * 
 * File Created at 2011-9-15
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author liubida
 */
public class WebConsole {
    public static void main(String[] args) {
        //        test.load();
        test.load4();
    }
}

class worker extends Thread {
    private InputStream in;
    private String      type;

    worker(InputStream in, String type) {
        this.in = in;
        this.type = type;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("["+type + "]:" + line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class test {
    public static void load4() {
        try {
            Runtime rt = Runtime.getRuntime();

            // 调用 mysql 的 cmd:
            Process p = rt.exec("ssh liubida:15927286199@127.0.0.1");

            OutputStream out = p.getOutputStream();
            InputStream in = p.getInputStream();
            InputStream err = p.getErrorStream();

            new worker(err, "error").start();
            new worker(in, "output").start();

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            OutputStreamWriter writer = new OutputStreamWriter(out);
            String tmp;
            while ((tmp = stdin.readLine()) != null && tmp.length() != 0) {
//                System.out.println(tmp);
                writer.write(tmp+"\n");
                writer.flush();
            }

            writer.close();
            err.close();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void load3() {
        try {
            Runtime rt = Runtime.getRuntime();

            // 调用 mysql 的 cmd:
            Process p = rt.exec("bc ");

            OutputStream out = p.getOutputStream();
            InputStream in = p.getInputStream();
            InputStream err = p.getErrorStream();

            worker w1 = new worker(err, "error");
            worker w2 = new worker(in, "output");
            w1.start();
            w2.start();

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            String tmp;
            while ((tmp = stdin.readLine()) != null && tmp.length() != 0) {
                //                System.out.println(tmp);
                writer.write(tmp + '\n');
                writer.flush();
                if ("quit".equals(tmp)) {
                    break;
                }
            }

            err.close();
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void load2() {
        try {
            Runtime rt = Runtime.getRuntime();
            System.out.println(rt.totalMemory());
            String s = null;

            // 调用 mysql 的 cmd:
            Process p = rt.exec("mysql -uroot -p");

            OutputStream out = p.getOutputStream();
            InputStream in = p.getInputStream();
            InputStream err = p.getErrorStream();

            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf8"));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(err, "utf8"));

            writer.write("password\n use armory;\n show tables;\n");
            writer.flush();
            writer.close();

            while ((s = errorReader.readLine()) != null) {
                System.out.println(s);
            }
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }

            //            s = reader.readLine();
            //            System.out.println(s);

            //            writer.write("password\n");
            //            writer.flush();
            //            writer.close();

            //            writer = new OutputStreamWriter(out, "utf8");
            //            writer.write("show tables; \r\n select * from logic_site; \r\n");
            //            writer.flush();
            //            writer.close();

            //            s = reader.readLine();
            //            System.out.println(s);
            //            while ((s = reader.readLine()) != null && s.length() != 0) {
            //                System.out.println(s);
            //            }

            System.out.println(p.waitFor());

            out.close();
            reader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void load() {
        try {
            Runtime rt = Runtime.getRuntime();
            System.out.println(rt.totalMemory());

            // 调用 mysql 的 cmd:
            Process child = rt.exec("mysql -uroot -ppassword armory ");
            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流
            String outStr = "show tables; \r\n select * from logic_site; \r\n";
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();
            // --别忘记关闭输入输出流--
            writer.close();
            out.close();

            InputStream in = child.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf8"));

            String s = null;
            while ((s = reader.readLine()) != null && s.length() != 0) {
                System.out.println(s);
            }

            reader.close();
            in.close();

            System.out.println("/* Load OK! */");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main1(String[] args) throws IOException {
        load();
        //        Process process = Runtime.getRuntime().exec("mysql -uroot -ppassword armory");
        //        PrintWriter writer = new PrintWriter(process.getOutputStream());
        //
        //        writer.write("\r\n");
        //        writer.flush();
        //        InputStream error_in = process.getErrorStream();
        //        InputStream child_in = process.getInputStream();
        //        int c;
        //
        //        while ((c = child_in.read()) != -1) {
        //            System.out.print((char) c);
        //        }
        //        child_in.close();
        //                new String[] { "sh", "-c", "ping 10.20.138.27" });
        //                new String[] { "sh", "-c", "mysql -uroot -ppassword" });
        //        Process process = Runtime.getRuntime()
        //        .exec(
        //                new String[] { "sh", "-c",
        //                "mysqldump -uroot -ppassword test>/home/charles/b.sql" });

        //        PrintWriter writer = new PrintWriter(process.getOutputStream());
        //        writer.write("password");
        //        writer.write("use armory;\n");
        //        writer.write("select * from logic_site;\n");

        //System.out.println(process.exitValue());

        //        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        //        System.out.println(reader.readLine());
        //        System.out.println(reader.readLine());
        //
        //        reader.close();

    }
}
