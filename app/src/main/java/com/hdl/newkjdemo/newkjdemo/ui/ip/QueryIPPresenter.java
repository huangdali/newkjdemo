package com.hdl.newkjdemo.newkjdemo.ui.ip;

import com.hdl.newkjdemo.newkjdemo.base.OnHttpCallBack;
import com.hdl.newkjdemo.newkjdemo.bean.IpInfo;
import com.socks.library.KLog;

/**
 * P层
 * Created by HDL on 2016/7/30.
 */
public class QueryIPPresenter implements QueryIPContract.IMainPresenter {
    QueryIPContract.IMainView mMainView;
    QueryIPContract.IMainModle mMainMoudle;

    public QueryIPPresenter(QueryIPContract.IMainView mMainView) {
        this.mMainView = mMainView;
        mMainMoudle = new QueryIPModle();
    }

    @Override
    public void showInfo() {
        mMainView.showInfo(mMainMoudle.getUserInfo(mMainView.getCurContext()));
    }

    @Override
    public void queryIp() {
        String ip = mMainView.getInputIp();
        if (mMainMoudle.isIp(ip)) {//判断是否是合法的ip地址
            mMainView.showProgress();
            mMainMoudle.queryIp(ip, new OnHttpCallBack<IpInfo>() {
                @Override
                public void onSuccessful(IpInfo ipInfo) {//查询成功了回调
                    mMainView.hindProgress();
                    mMainView.showData(ipInfo);
                    KLog.e("查询到了" + ipInfo.toString());
                }

                @Override
                public void onFaild(String errorMsg) {//查询失败了回调
                    mMainView.hindProgress();
                    KLog.e(errorMsg);
                    mMainView.showInfo(errorMsg);
                }
            });
        } else {
            mMainView.showInfo("IP地址格式有误...");
        }
    }
}
