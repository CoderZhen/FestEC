package com.dianbin.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.dianbin.latte.delegates.bottom.BottomItemDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.ui.recycler.MultipleFields;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;
import com.dianbin.latte.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by ZHEN on 2018/3/3.
 */

public class IndexDelegate extends BottomItemDelegate {


    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private IconTextView mIconScan;
    private AppCompatEditText mSearchView;

    private SwipeRefreshLayout mRefreshLayout;
    private RefreshHandler mRefreshHandler = null;


    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        mRefreshHandler = RefreshHandler.create(mRefreshLayout,mRecyclerView,new IndexDataConverter());

    }

    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.rv_index);
        mRefreshLayout = rootView.findViewById(R.id.srl_index);
        mToolbar = rootView.findViewById(R.id.tb_index);
        mIconScan = rootView.findViewById(R.id.icon_index_scan);
        mSearchView = rootView.findViewById(R.id.et_search_view);
    }
}
