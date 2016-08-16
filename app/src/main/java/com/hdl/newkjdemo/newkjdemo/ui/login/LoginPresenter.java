package com.hdl.newkjdemo.newkjdemo.ui.login;

import com.hdl.newkjdemo.newkjdemo.base.OnHttpCallBack;
import com.hdl.newkjdemo.newkjdemo.bean.TokenResult;
import com.hdl.newkjdemo.newkjdemo.bean.UserInfo;

/**
 * 登录的桥梁(View和Model的桥梁)-------P通过将V拿到的数据交给M来处理  P又将处理的结果回显到V
 * 说白点就是P中new出M和V 然后通过调用它们两个的方法来完成操作
 * <p/>
 * (看例子之前看一遍下面的直白的解释,看完之后再看一遍就更明白MVP模式了)
 * --------V层   负责响应用户的交互(获取数据---->提示操作结果)
 * --------P层   传递完数据给M层处理之后,实例化回调对象,成功了就通知V层登录成功,失败了就通知V层显示错误信息
 * --------M层   对P层传递过来的userInfo进行登录(网络请求)判断,处理完成之后将处理结果回调给P层
 * Created by Administrator on 2016/7/22.
 */
public class LoginPresenter implements LoginContract.ILoginPresenter {
    private LoginContract.ILoginView mLoginView;
    private LoginContract.ILoginModel mLoginModel;


    public LoginPresenter(LoginContract.ILoginView mLoginView) {
        this.mLoginView = mLoginView;
        mLoginModel = new LoginModel();
    }

    /**
     * 登录
     */
    @Override
    public void login() {
        mLoginView.showProgress(); //显示登录进度条
        final int i = 0;
        mLoginModel.login(mLoginView.getUserLoginInfo(), new OnHttpCallBack<TokenResult>() {
            @Override
            public void onSuccessful(TokenResult tokenResult) {
                mLoginView.hideProgress();//隐藏进度条
                mLoginView.toMain();//跳转到主页面
                mLoginModel.saveUserInfo(mLoginView.getCurContext(), mLoginView.getUserLoginInfo(), tokenResult.getToken());//保存用户数据
                mLoginView.showInfo("登录成功,您的用户toekn为:" + tokenResult.getToken());//提示用户登录成功
            }

            @Override
            public void onFaild(String errorMsg) {
                mLoginView.hideProgress();//隐藏进度条
                mLoginView.showErrorMsg(errorMsg);//登录失败  显示错误信息
            }
        });

    }
}
