package com.tc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * @Auther: tianchao
 * @Date: 2020/3/24 21:37
 * @Description:
 */
public class Hack {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6379);
        while (true){
            Socket socket = serverSocket.accept();
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            inputStream.read(bytes);
            System.out.println(new String(bytes));
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("OK".getBytes());
        }

    }
}
