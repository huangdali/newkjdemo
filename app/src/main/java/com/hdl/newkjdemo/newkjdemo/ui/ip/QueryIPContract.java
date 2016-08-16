package com.hdl.newkjdemo.newkjdemo.ui.ip;

import android.content.Context;

import com.hdl.newkjdemo.newkjdemo.base.OnHttpCallBack;
import com.hdl.newkjdemo.newkjdemo.bean.IpInfo;

/**
 * 这是主页面MVC的关联类----该需求的话直接在这里加功能即可
 * Created by HDL on 2016/7/30.
 */
public class QueryIPContract {
    interface IMainView {//------规范View层的功能

        Context getCurContext();//获取当前的上下文对象

        void showInfo(String info);//通过toast显示消息

        void showData(IpInfo ipInfo);//显示查询到的数据到界面

        String getInputIp();//获取用户输入的ip

        void showProgress();//显示进度条对话框

        void hindProgress();//隐藏进度条对话框
    }

    interface IMainModle {//-------业务处理规范类

        String getUserInfo(Context context);//获取已经保存在sp中的用户信息

        void queryIp(String ip, OnHttpCallBack<IpInfo> callBack);//查询ip地址

        boolean isIp(String ip);//判断是否是正确的ip地址
    }

    interface IMainPresenter {//------P层处理规范类

        void showInfo();//显示用户的信息

        void queryIp();//查询IP
    }
}
