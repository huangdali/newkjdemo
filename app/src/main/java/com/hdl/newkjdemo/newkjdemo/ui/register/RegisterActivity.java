package com.hdl.newkjdemo.newkjdemo.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hdl.newkjdemo.newkjdemo.R;
import com.hdl.newkjdemo.newkjdemo.base.BaseActivity;
import com.hdl.newkjdemo.newkjdemo.ui.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 注册页面 暂时不实现 跟登录界面大同小异
 */
public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @InjectView(R.id.et_register_name)
    EditText etRegisterName;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.et_register_repwd)
    EditText etRegisterRepwd;
    @InjectView(R.id.et_register_address)
    EditText etRegisterAddress;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        tvTitle.setText("注册");
    }

    @OnClick({R.id.btn_register, R.id.iv_title_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                break;
            case R.id.iv_title_back:
                finish();
                break;
        }
    }

}
