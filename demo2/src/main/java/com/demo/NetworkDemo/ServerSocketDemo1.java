package com.demo.NetworkDemo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by linkang on 10/10/16.
 */
public class ServerSocketDemo1 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
             serverSocket = new ServerSocket(9000);
//            serverSocket.setSoTimeout(8000);

            //client count
            int num = 0;

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("port=" + socket.getPort());
                InetAddress inetAddress = socket.getInetAddress();
                System.out.println("host add=" + inetAddress.getHostAddress());


                new Thread(new SocketTask(socket,num++)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class SocketTask implements Runnable{
    private int num;
    private Socket socket;

    public SocketTask(Socket socket,int num) {
        this.socket = socket;
        this.num = num;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            String getString = dataInputStream.readUTF();
            System.out.println(getClientName() + ">>>" + getString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getClientName() {
        return String.format("client_%s", num);
    }
}
