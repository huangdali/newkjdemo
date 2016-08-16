package com.hdl.newkjdemo.newkjdemo.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hdl.newkjdemo.newkjdemo.R;
import com.hdl.newkjdemo.newkjdemo.base.BaseActivity;
import com.hdl.newkjdemo.newkjdemo.ui.ip.QueryIPActivity;
import com.hdl.newkjdemo.newkjdemo.ui.login.LoginActivity;
import com.hdl.newkjdemo.newkjdemo.ui.movie.MovieActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.iv_title_back)
    ImageView ivTitleBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        tvTitle.setText("Demo介绍");
        ivTitleBack.setVisibility(View.GONE);
    }

    @OnClick({R.id.btn_main_login, R.id.btn_main_queryIp, R.id.btn_main_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_main_queryIp:
                startActivity(new Intent(this, QueryIPActivity.class));
                break;
            case R.id.btn_main_movie:
                startActivity(new Intent(this, MovieActivity.class));
                break;
        }
    }

}
