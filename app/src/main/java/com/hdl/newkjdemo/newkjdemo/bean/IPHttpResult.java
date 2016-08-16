package com.hdl.newkjdemo.newkjdemo.bean;

/**
 * Created by HDL on 2016/7/30.
 */
public class IPHttpResult<T> {
    private int code;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IPHttpResult{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
