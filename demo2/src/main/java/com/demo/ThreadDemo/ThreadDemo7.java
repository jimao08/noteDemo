package com.demo.ThreadDemo;

public class ThreadDemo7 {
    private static volatile boolean FLAG = false;
    private static int ival = 0;

    public static void main(String[] args) throws Exception{

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!FLAG) {
                    ival++;
//                    System.out.println("..." + Thread.currentThread().getState());
                }
                System.out.println("done.");
            }
        }).start();


        Thread.sleep(100);
        FLAG = true;
    }


}
