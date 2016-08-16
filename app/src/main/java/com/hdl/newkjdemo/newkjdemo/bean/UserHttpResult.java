package com.hdl.newkjdemo.newkjdemo.bean;

/**
 * Created by HDL on 2016/7/30.
 */
public class UserHttpResult<T> {
    private int resultCode;
    private String resultMes;
    private T data;

    public UserHttpResult() {
    }

    public UserHttpResult(int resultCode, String resultMes, T data) {
        this.resultCode = resultCode;
        this.resultMes = resultMes;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMes() {
        return resultMes;
    }

    public void setResultMes(String resultMes) {
        this.resultMes = resultMes;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserHttpResult{" +
                "resultCode=" + resultCode +
                ", resultMes='" + resultMes + '\'' +
                ", data=" + data +
                '}';
    }
}
