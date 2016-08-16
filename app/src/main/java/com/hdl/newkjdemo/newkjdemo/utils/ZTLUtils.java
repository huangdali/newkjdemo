package com.hdl.newkjdemo.newkjdemo.utils;

import android.app.Activity;
import android.os.Build;
import android.view.WindowManager;

import com.hdl.newkjdemo.newkjdemo.R;


/**
 * Created by Administrator on 2016/6/23.
 */
public class ZTLUtils {
    Activity activity;

    public ZTLUtils(Activity activity) {
        this.activity = activity;
    }

    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            this.activity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            this.activity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemStatusManager tintManager = new SystemStatusManager(this.activity);
            tintManager.setStatusBarTintEnabled(true);
            // 设置状态栏的颜色
            tintManager.setStatusBarTintResource(R.color.colorPrimary);
            this.activity.getWindow().getDecorView().setFitsSystemWindows(true);
        }
    }
}
