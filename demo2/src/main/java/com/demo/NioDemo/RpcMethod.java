package com.demo.NioDemo;

import java.io.Serializable;

public class RpcMethod implements Serializable {
    private String serviceName;
    private String methodName;
    private Object[] params;

    public RpcMethod(String serviceName, String methodName, Object[] params) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.params = params;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
