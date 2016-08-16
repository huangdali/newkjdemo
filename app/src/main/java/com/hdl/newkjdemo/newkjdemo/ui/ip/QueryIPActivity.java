package com.hdl.newkjdemo.newkjdemo.ui.ip;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hdl.newkjdemo.newkjdemo.R;
import com.hdl.newkjdemo.newkjdemo.base.BaseActivity;
import com.hdl.newkjdemo.newkjdemo.bean.IpInfo;
import com.hdl.newkjdemo.newkjdemo.ui.movie.MovieActivity;
import com.hdl.newkjdemo.newkjdemo.utils.ToastUtils;
import com.hdl.newkjdemo.newkjdemo.widget.ClearEditText;
import com.socks.library.KLog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 查询ip信息的主页面
 */
public class QueryIPActivity extends BaseActivity implements QueryIPContract.IMainView {
    QueryIPContract.IMainPresenter mMainPresenter;
    @InjectView(R.id.tv_movie_ip)
    TextView tvMainIp;
    @InjectView(R.id.tv_movie_country)
    TextView tvMainCountry;
    @InjectView(R.id.tv_movie_area)
    TextView tvMainArea;
    @InjectView(R.id.tv_movie_region)
    TextView tvMainRegion;
    @InjectView(R.id.tv_movie_city)
    TextView tvMainCity;
    @InjectView(R.id.tv_movie_isp)
    TextView tvMainIsp;
    @InjectView(R.id.et_movie_input_ip)
    ClearEditText etMainInputIp;
    ProgressDialog mProgressDialog;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        mMainPresenter = new QueryIPPresenter(this);
        mMainPresenter.showInfo();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        setContentView(R.layout.activity_ip);
        ButterKnife.inject(this);
        tvTitle.setText("IP查询");
    }

    @Override
    public Context getCurContext() {
        return mActivity;
    }

    @Override
    public void showInfo(String info) {
//        tvMainUserinfo.setText(info);
        ToastUtils.showToast(mActivity, info);
    }

    @Override
    public void showData(IpInfo ipInfo) {
        tvMainIp.setText(ipInfo.getIp());
        tvMainArea.setText(ipInfo.getArea());
        tvMainCity.setText(ipInfo.getCity());
        tvMainIsp.setText(ipInfo.getIsp());
        tvMainCountry.setText(ipInfo.getCountry());
        tvMainRegion.setText(ipInfo.getRegion());
    }

    @Override
    public String getInputIp() {
        return etMainInputIp.getText().toString().trim();
    }

    @Override
    public void showProgress() {
        mProgressDialog = ProgressDialog.show(this, "提示", "正在查询中,请稍后...");
    }

    @Override
    public void hindProgress() {
        mProgressDialog.hide();
    }


    public void onToMovie(View view) {
        startActivity(new Intent(this, MovieActivity.class));
    }

    @OnClick({R.id.iv_title_back, R.id.btn_movie_queryIp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.btn_movie_queryIp:
                KLog.e("查询ip了");
                mMainPresenter.queryIp();
                break;
        }
    }
}
