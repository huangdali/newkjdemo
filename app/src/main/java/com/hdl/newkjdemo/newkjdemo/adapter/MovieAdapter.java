package com.hdl.newkjdemo.newkjdemo.adapter;

import android.content.Context;

import com.hdl.newkjdemo.newkjdemo.bean.Movies;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class MovieAdapter extends CommonAdapter<Movies.SubjectsBean> {
    public MovieAdapter(Context context, int layoutId, List<Movies.SubjectsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, Movies.SubjectsBean subjectsBean, int position) {
    }
}
