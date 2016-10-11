package com.demo.IoDemo;

import java.io.*;

/**
 * io流demo
 * Created by linkang on 10/9/16.
 */
public class ObjectIoDemo {
    public static void main(String[] args) {

        mmm();
    }


    public static void mmm() {
        try {
            File file = new File("1.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            MyObject myObject = new MyObject(1, "nba game");

            objectOutputStream.writeObject(myObject);
            fileOutputStream.close();


            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            MyObject mobj = (MyObject)objectInputStream.readObject();
            System.out.println(mobj.getSval());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyObject implements Serializable{
    private int ival;
    private String sval;

    public MyObject(int ival, String sval) {
        this.ival = ival;
        this.sval = sval;
    }

    public String getSval() {
        return sval;
    }

    public void setSval(String sval) {
        this.sval = sval;
    }

    public int getIval() {
        return ival;
    }

    public void setIval(int ival) {
        this.ival = ival;
    }
}
