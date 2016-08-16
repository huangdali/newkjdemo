package com.hdl.newkjdemo.newkjdemo.bean;

/**
 * Created by HDL on 2016/7/28.
 */
public class TokenResult {
    private String token;

    public TokenResult(String result) {
        this.token = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenResult{" +
                "token='" + token + '\'' +
                '}';
    }
}
