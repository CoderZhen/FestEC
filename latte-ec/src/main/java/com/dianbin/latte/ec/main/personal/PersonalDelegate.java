package com.dianbin.latte.ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dianbin.latte.delegates.bottom.BottomItemDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ec.main.personal.address.AddressDelegate;
import com.dianbin.latte.ec.main.personal.list.ListAdapter;
import com.dianbin.latte.ec.main.personal.list.ListBean;
import com.dianbin.latte.ec.main.personal.list.ListItemType;
import com.dianbin.latte.ec.main.personal.order.OrderListDelegate;
import com.dianbin.latte.ec.main.personal.profile.UserProfileDelegate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZHEN on 2018/3/10.
 */

public class PersonalDelegate extends BottomItemDelegate {

    private RecyclerView mRvSettings;

    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;
    private AppCompatTextView mTvAllOrder;
    private ImageView mCircleImageView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    private void startOrderListByType() {
        final OrderListDelegate delegate = new OrderListDelegate();
        delegate.setArguments(mArgs);
        getParentDelegate().getSupportDelegate().start(delegate);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setDelegate(new AddressDelegate())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRvSettings.setAdapter(adapter);
        mRvSettings.addOnItemTouchListener(new PersonalClickListener(this));

        initEvent();
    }

    private void initEvent(){
        mTvAllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArgs.putString(ORDER_TYPE,"all");
                startOrderListByType();
            }
        });

        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentDelegate().getSupportDelegate().start(new UserProfileDelegate());
            }
        });
    }

    private void initView() {
        mRvSettings = $(R.id.rv_personal_setting);
        mTvAllOrder = $(R.id.tv_all_order);
        mCircleImageView = $(R.id.img_user_avatar);
    }
}
