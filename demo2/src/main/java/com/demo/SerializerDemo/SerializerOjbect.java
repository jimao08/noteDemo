package com.demo.SerializerDemo;

import java.io.Serializable;
import java.util.List;


public class SerializerOjbect implements Serializable{
    private int ival;
    private List<String> list;
    private double dval;

    public SerializerOjbect(int ival, List<String> list, double dval) {
        this.ival = ival;
        this.list = list;
        this.dval = dval;
    }

    @Override
    public String toString() {
        return "SerializerOjbect{" +
                "ival=" + ival +
                ", list=" + list +
                ", dval=" + dval +
                '}';
    }
}
