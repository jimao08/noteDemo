package com.demo.NioDemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamUtils {

    public static byte[] getObjectBytes(Object obj) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(obj);
        byte[] data = bos.toByteArray();

        oos.flush();
        oos.close();
        return data;
    }

    public static Object readObject(byte[] data) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = ois.readObject();

        ois.close();
        return o;
    }
}
