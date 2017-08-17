package com.demo;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.InetSocketAddress;

/**
 * Created by myle on 2017/08/18 00:27
 */
public class MyServer2 {

    public static void main(String[] args) throws Exception{

        String contentPath = "/a";
        String webappDir = "C:\\Users\\Administrator\\workspace\\noteDemo\\demo2\\src\\main/webapp";

        final InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        final Server server = new Server(inetSocketAddress);

        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setMinThreads(1);
        threadPool.setMaxThreads(20);
        //设置线程池
        server.setAttribute("threadpool",threadPool);
        WebAppContext webapp = new WebAppContext(webappDir,contentPath);
//        handlerList.add(webapp);
        HandlerList handlers = new HandlerList();

        handlers.setHandlers(new Handler[]{webapp});
//        handlers.setHandlers(handlerList.stream().toArray(Handler[]::new));

        server.setHandler(handlers);

        GzipHandler gzip = new GzipHandler();
        gzip.setHandler(handlers);
        server.setHandler(gzip);
        server.setStopAtShutdown(true);
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();
    }


}
