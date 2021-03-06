package com.dianbin.latte.ec.main.index;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.delegates.bottom.BottomItemDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ec.main.EcBottomDelegate;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.ui.recycler.BaseDecoration;
import com.dianbin.latte.ui.recycler.MultipleFields;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;
import com.dianbin.latte.ui.refresh.RefreshHandler;
import com.dianbin.latte.util.callback.CallbackManager;
import com.dianbin.latte.util.callback.CallbackType;
import com.dianbin.latte.util.callback.IGlobalCallback;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by ZHEN on 2018/3/3.
 * 首页Delegate
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

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        //分割线
        mRecyclerView.addItemDecoration
                (BaseDecoration.create(ContextCompat.getColor(_mActivity, R.color.app_background), 5));

        //Item点击事件
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
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
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
        initEvent();
        CallbackManager.getInstance().addCallback(CallbackType.ON_SCAN, new IGlobalCallback<String>() {
            @Override
            public void executeCallback(String args) {
                Toast.makeText(_mActivity, args, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initEvent() {
        mIconScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫描二维码
                startScanWithCheck(IndexDelegate.this.getParentDelegate());
            }
        });
    }

    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.rv_index);
        mRefreshLayout = rootView.findViewById(R.id.srl_index);
        mToolbar = rootView.findViewById(R.id.tb_index);
        mIconScan = rootView.findViewById(R.id.icon_index_scan);
        mSearchView = rootView.findViewById(R.id.et_search_view);
        mIconScan = $(R.id.icon_index_scan);
    }
}
