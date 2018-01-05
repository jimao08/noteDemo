package com.demo.SerializerDemo;

import java.io.*;

public class SerializerDemo1 {

    public static void main(String[] args) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);


        dos.writeInt(124);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        DataInputStream dis = new DataInputStream(bis);

        System.out.println(dis.readInt());

        System.out.println(bos.toString());


        String cn = "中国";
        byte[] bytes = {0};
        System.out.println(new String(bytes));


        char ch = cn.charAt(0);


        System.out.println("dsfs".compareTo(null));

    }
}
