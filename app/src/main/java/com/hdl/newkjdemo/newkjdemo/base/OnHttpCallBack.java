package com.hdl.newkjdemo.newkjdemo.base;

/**
 * 公共的请求回调监听器
 * Created by HDL on 2016/7/29.
 */
public interface OnHttpCallBack<T> {
    void onSuccessful(T t);//成功了就回调这个方法,可以传递任何形式的数据给调用者

    void onFaild(String errorMsg);//失败了就调用这个方法,可以传递错误的请求消息给调用者
}
