package com.demo.SerializerDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class SerializerDemo0 {


    public static void main(String[] args) throws Exception {
        SerializerOjbect object = new SerializerOjbect(1, Arrays.asList("1", "2", "3"), 0.01);


        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("tt"));
        outputStream.writeObject(object);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("tt"));

        Object read = inputStream.readObject();

        System.out.println(read.getClass().getName());

        System.out.println(read);




    }
}
