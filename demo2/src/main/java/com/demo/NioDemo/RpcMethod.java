package com.demo.NioDemo;

import java.io.Serializable;
import java.util.Arrays;

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

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "RpcMethod{" +
                "serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
