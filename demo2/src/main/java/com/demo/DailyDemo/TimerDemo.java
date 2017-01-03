package com.demo.DailyDemo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by linkang on 12/29/16.
 */
public class TimerDemo {

    public static void main(String[] args) throws Exception{
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        };


        long convert = TimeUnit.MILLISECONDS.convert(20, TimeUnit.SECONDS);
        System.out.println(convert);
        timer.schedule(task, 2, convert);

    }


}
