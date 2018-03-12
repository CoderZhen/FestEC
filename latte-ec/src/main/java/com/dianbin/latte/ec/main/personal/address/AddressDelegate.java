package com.dianbin.latte.ec.main.personal.address;

import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * Created by ZHEN on 2018/3/12.
 */

public class AddressDelegate extends LatteDelegate implements ISuccess{

    private RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
        RestClient.builder()
                .url("address.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    private void initView() {
        mRecyclerView = $(R.id.rv_address);
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data = new AddressDataConverter().setJsonData(response).convert();
        final AddressAdapter addressAdapter = new AddressAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
