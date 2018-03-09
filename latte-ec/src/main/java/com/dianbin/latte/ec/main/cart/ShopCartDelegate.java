package com.dianbin.latte.ec.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dianbin.latte.delegates.bottom.BottomItemDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by ZHEN on 2018/3/8.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess{

    private RecyclerView mRecyclerView;
    private ShopCartAdapter mAdapter;
    private IconTextView mIconSelectedAll;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        initEvent();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> data =
                new ShopCartDataConverter().setJsonData(response).convert();
        mAdapter = new ShopCartAdapter(data);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.rv_shop_cart);
        mIconSelectedAll = rootView.findViewById(R.id.icon_shop_cart_select_all);
        mIconSelectedAll.setTag(0);
    }

    private void initEvent(){
        mIconSelectedAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int tag = (int) mIconSelectedAll.getTag();
                if (tag == 0) {
                    mIconSelectedAll.setTextColor(ContextCompat.getColor(_mActivity, R.color.app_main));
                    mIconSelectedAll.setTag(1);
                    mAdapter.setIsSelectedAll(true);
                    //更新RecyclerView的显示状态
                    mAdapter.notifyDataSetChanged();
                } else {
                    mIconSelectedAll.setTextColor(Color.GRAY);
                    mIconSelectedAll.setTag(0);
                    mAdapter.setIsSelectedAll(false);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
