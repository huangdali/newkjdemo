package com.hdl.newkjdemo.newkjdemo.ui.ip;

import android.content.Context;
import android.content.SharedPreferences;

import com.hdl.newkjdemo.newkjdemo.base.APIService;
import com.hdl.newkjdemo.newkjdemo.base.GlobalField;
import com.hdl.newkjdemo.newkjdemo.base.OnHttpCallBack;
import com.hdl.newkjdemo.newkjdemo.bean.IPHttpResult;
import com.hdl.newkjdemo.newkjdemo.bean.IpInfo;
import com.hdl.newkjdemo.newkjdemo.bean.UserInfo;
import com.hdl.newkjdemo.newkjdemo.http.RetrofitUtils;
import com.socks.library.KLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.regex.Pattern;

import retrofit.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 查询ip的逻辑处理类
 * Created by HDL on 2016/7/30.
 */
public class QueryIPModle implements QueryIPContract.IMainModle {

    @Override
    public String getUserInfo(Context context) {
        SharedPreferences sp_userinfo = context.getSharedPreferences(GlobalField.USERINFO_SP_NAME, Context.MODE_PRIVATE);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(sp_userinfo.getString("userName", ""));
        userInfo.setAddress(sp_userinfo.getString("address", ""));
        userInfo.setPhone(sp_userinfo.getString("phone", ""));
        userInfo.setPwd(sp_userinfo.getString("pwd", ""));
        return userInfo.toString() + "\ntoken=" + sp_userinfo.getString("token", "");
    }

    @Override
    public void queryIp(final String ip, final OnHttpCallBack<IpInfo> callBack) {
        KLog.e("开始查询ip了-----presenter");
        RetrofitUtils.newInstence(GlobalField.IP_QUERY_URL)
                .create(APIService.class)
                .queryIp(ip)
                .observeOn(AndroidSchedulers.mainThread())//在主线程中观察
                .subscribeOn(Schedulers.newThread())//在新线程中执行网络请求
                .subscribe(new Subscriber<IPHttpResult<IpInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e("请求失败,错误信息-------" + e.getMessage());
//                        callBack.onFaild("请求失败,错误信息" + e.getMessage());
                        //失败的时候调用-----一下可以忽略 直接 callBack.onFaild("请求失败");
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onFaild("服务器出错");
                            }
                        } else if (e instanceof ConnectException) {
                            callBack.onFaild("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onFaild("网络连接超时!!");
                        } else {
                            callBack.onFaild("发生未知错误" + e.getMessage());
                            KLog.e("Myloy", e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(IPHttpResult<IpInfo> ipInfoIPHttpResult) {
                        KLog.e(ipInfoIPHttpResult.getData().toString());
                        callBack.onSuccessful(ipInfoIPHttpResult.getData());
                    }
                });
    }

    /**
     * 利用正则表达式判断是否是合法的ip地址
     *
     * @param ip
     * @return
     */
    @Override
    public boolean isIp(String ip) {
        String regex = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return Pattern.compile(regex).matcher(ip).matches();
    }
}
