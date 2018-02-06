package com.demo.NioDemo;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.*;

public class NioClient {

    public static void main(String[] args) throws Exception {

        ExecutorService service = new ThreadPoolExecutor(10, 20, 2,
                TimeUnit.MINUTES, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10000; i++) {

            final int ival = i;

            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {

                        Socket socket = new Socket("localhost", 8889);
                        OutputStream os = new BufferedOutputStream(socket.getOutputStream());

//                        os.write(("hehe" + ival + "\n").getBytes());
                        os.write(("set " + "hehe" + ival + " " + "d" + "\n").getBytes());
                        os.flush();

                        os.write("quit\n".getBytes());
                        os.flush();
                        socket.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        service.shutdown();
        while (!service.isTerminated()) {
            Thread.yield();
        }


//        InputStream inputStream = socket.getInputStream();
//
//        byte[] bytes = new byte[48];
//        int read ;
//        while ((read = inputStream.read(bytes))!= - 1) {
//
//            System.out.println(new String(bytes, 0, read));
//        }

    }
}
