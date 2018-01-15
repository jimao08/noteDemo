package com.demo.SerializerDemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class SerializerOjbect implements Serializable{
    private static final long serialVersionUID = 1L;

    private int ival;
    private List<String> list;
    private double dval;
    private CondType condType;

    public SerializerOjbect(int ival, List<String> list, double dval) {
        this.ival = ival;
        this.list = list;
        this.dval = dval;
        condType = CondType.and;
    }


    @Override
    public String toString() {
        return "SerializerOjbect{" +
                "ival=" + ival +
                ", list=" + list +
                ", dval=" + dval +
                ", condType=" + condType +
                '}';
    }

    public enum CondType {
        none,
        not,
        and,
        or
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
//        DataOutputStream output = new DataOutputStream(out);
//
//        out.writeInt(list.size());
//        if (list != null && !list.isEmpty()) {
//            for (String s : list) {
//                output.writeUTF(s);
//            }
//        }
//        System.out.println("write object");

        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        DataInputStream input = new DataInputStream(in);
//
//        int size = input.readInt();
//
//        if (size != 0) {
//            list = new ArrayList<>();
//            for (int i = 0; i < size; i++) {
//                String s =  input.readUTF();
//                System.out.println("read object:" + s);
//                list.add(s);
//            }
//        }

        in.defaultReadObject();
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIval() {
        return ival;
    }

    public void setIval(int ival) {
        this.ival = ival;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public double getDval() {
        return dval;
    }

    public void setDval(double dval) {
        this.dval = dval;
    }

    public CondType getCondType() {
        return condType;
    }

    public void setCondType(CondType condType) {
        this.condType = condType;
    }
}
