package com.hdl.newkjdemo.newkjdemo.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hdl.newkjdemo.newkjdemo.R;
import com.hdl.newkjdemo.newkjdemo.base.BaseActivity;
import com.hdl.newkjdemo.newkjdemo.bean.UserInfo;
import com.hdl.newkjdemo.newkjdemo.ui.ip.QueryIPActivity;
import com.hdl.newkjdemo.newkjdemo.ui.register.RegisterActivity;
import com.hdl.newkjdemo.newkjdemo.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 登录页面
 * (看例子之前看一遍下面直白的解释,看完之后再看一遍就更明白MVP模式了)
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 */
public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {


    @InjectView(R.id.et_login_userName)
    EditText etLoginUserName;
    @InjectView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.pb_progress)
    ProgressBar pbProgress;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        tvTitle.setText("用户登录");
        mLoginPresenter = new LoginPresenter(this);
    }


    @Override
    public Context getCurContext() {
        return mActivity;
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    public void showInfo(String info) {
        ToastUtils.showToast(mActivity, info);
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.showToast(mActivity, msg);
    }

    @Override
    public void toMain() {
        startActivity(new Intent(mActivity, QueryIPActivity.class));
    }

    @Override
    public void toRegister() {
        startActivity(new Intent(mActivity, RegisterActivity.class));
    }

    @Override
    public UserInfo getUserLoginInfo() {
        return new UserInfo(etLoginUserName.getText().toString().trim(), etLoginPwd.getText().toString().trim());
    }


    public void onRegister(View view) {
        toRegister();
    }

    @OnClick({R.id.iv_title_back, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.btn_login:
                mLoginPresenter.login();
                break;
        }
    }
}
