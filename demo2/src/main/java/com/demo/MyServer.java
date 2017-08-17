package com.demo;


import com.demo.TestServer.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServer {

    private static final Logger logger = LoggerFactory.getLogger(MyServer.class);

    public static void main(String[] args) throws Exception {
        String serverAddress = "localhost";//server地址
        WebServer webServer = new WebServer(serverAddress,8080,"/a");
        webServer.setMinThreads(2)
                .setMaxThreads(20)
                .start();

    }
}
