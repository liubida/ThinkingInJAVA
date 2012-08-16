import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
/**
 * @author liubida 2012-2-17 下午1:31:42
 */
public class Server {

    private ServerSocket   ss;
    private Socket         socket;
    private BufferedReader in;
    private PrintWriter    out;
    private String         s;
    private String         ip;
    private String         port;

    public Server(){
        try {
            ss = new ServerSocket(8880);

            while (true) {
                socket = ss.accept();
                ip = socket.getInetAddress().getHostAddress();
                port = ":" + socket.getLocalPort();
                System.out.println("A client come in!IP:" + ip + port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                s = in.readLine();
                System.out.println(s);
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("server received!");
            }
        } catch (IOException e) {
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
