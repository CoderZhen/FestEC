package com.dianbin.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by ZHEN on 2018/3/5.
 */

public class MultipleViewHolder extends BaseViewHolder {

    protected MultipleViewHolder(View view) {
        super(view);
    }
    //传值
    public static MultipleViewHolder create(View view){
        return new MultipleViewHolder(view);
    }

}
