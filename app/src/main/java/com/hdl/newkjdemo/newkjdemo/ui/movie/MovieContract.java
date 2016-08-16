package com.hdl.newkjdemo.newkjdemo.ui.movie;

import android.content.Context;

import com.hdl.newkjdemo.newkjdemo.base.OnHttpCallBack;
import com.hdl.newkjdemo.newkjdemo.bean.Movies;
import com.hdl.newkjdemo.newkjdemo.bean.TokenResult;
import com.hdl.newkjdemo.newkjdemo.bean.UserInfo;

import java.util.List;

/**
 * 获取豆瓣top250的关联类---------这里主要是抽出MVP中三层的接口,好处就是改需求很方便,使得代码结构更加清晰
 * Created by HDL on 2016/7/22.
 */
public class MovieContract {
    /**
     * V视图层
     */
    interface IMovieView {
        void showBottom(int lastIndex);

        Context getCurContext();//获取上下文对象

        void showProgress();//显示进度条

        void hideProgress();//隐藏进度条

        void showData(List<Movies.SubjectsBean> movies);//显示数据到View上

        void showInfo(String info);//提示用户,提升友好交互

    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IMoviePresenter {
        void getMovie();//获取数据

        void loadMoreMovie();//加载更多
    }

    /**
     * M逻辑(业务)处理层
     */
    interface IMovieModel {
        void getMovie(int start, int count, OnHttpCallBack<Movies> callBack);//获取信息
    }
}
