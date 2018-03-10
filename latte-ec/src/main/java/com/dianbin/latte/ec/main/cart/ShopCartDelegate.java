package com.dianbin.latte.ec.main.cart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.delegates.bottom.BottomItemDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ec.pay.FastPay;
import com.dianbin.latte.ec.pay.IAlPayResultListener;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by ZHEN on 2018/3/8.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, ICartItemListener, IUpDataListener, IAlPayResultListener {
    private static final String TAG = "ShopCartDelegate-vv";

    private RecyclerView mRecyclerView;
    private ShopCartAdapter mAdapter;
    private IconTextView mIconSelectedAll;
    private AppCompatTextView mRemoveSelectedItem;
    private AppCompatTextView mClear;
    private ViewStubCompat mStubNoItem;
    private AppCompatTextView mTvTotalPrice;

    private final LatteDelegate DELEGATE_THIS = this;

    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;
    private AppCompatTextView mTvShopCartPay;


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
        mAdapter.setCartItemListener(this);
        mAdapter.setUpDataListener(this);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        //进来添加价格
        mTotalPrice = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(mTotalPrice));
        checkItemCount();
    }

    private void checkItemCount() {
        final int count = mAdapter.getItemCount();
        final boolean stubTag = (boolean) mStubNoItem.getTag();
        if (count == 0) {
            if (stubTag) {
                @SuppressLint("RestrictedApi") final View stubView = mStubNoItem.inflate();
                final AppCompatTextView tvToBuy = stubView.findViewById(R.id.tv_stub_to_buy);
                tvToBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "购物啦", Toast.LENGTH_SHORT).show();
                    }
                });
                mRecyclerView.setVisibility(View.GONE);
                mStubNoItem.setTag(false);
            }
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mStubNoItem.setTag(true);
        }
    }

    private void initView(View rootView) {
        mStubNoItem = rootView.findViewById(R.id.stub_no_item);
        mStubNoItem.setTag(true);
        mRecyclerView = rootView.findViewById(R.id.rv_shop_cart);
        mRemoveSelectedItem = rootView.findViewById(R.id.tv_top_shop_cart_remove_selected);
        mClear = rootView.findViewById(R.id.tv_top_shop_cart_clear);
        mTvTotalPrice = rootView.findViewById(R.id.tv_shop_cart_total_price);
        mTvShopCartPay = rootView.findViewById(R.id.tv_shop_cart_pay);
        mIconSelectedAll = rootView.findViewById(R.id.icon_shop_cart_select_all);
        mIconSelectedAll.setTag(0);
    }

    private void initEvent() {
        //全选事件
        mIconSelectedAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int tag = (int) mIconSelectedAll.getTag();
                if (tag == 0) {
                    mIconSelectedAll.setTextColor(ContextCompat.getColor(_mActivity, R.color.app_main));
                    mIconSelectedAll.setTag(1);
                    mAdapter.setIsSelectedAll(true);
                    //更新RecyclerView的显示状态
                    mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
                } else {
                    mIconSelectedAll.setTextColor(Color.GRAY);
                    mIconSelectedAll.setTag(0);
                    mAdapter.setIsSelectedAll(false);
                    mAdapter.notifyItemRangeChanged(0, mAdapter.getItemCount());
                }
            }
        });

        //删除数据事件
        mRemoveSelectedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<MultipleItemEntity> data = mAdapter.getData();
                //要删除的数据
                List<MultipleItemEntity> deleteEntities = new ArrayList<>();
                for (MultipleItemEntity entity : data) {
                    final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                    if (isSelected) {
                        deleteEntities.add(entity);
                    }
                }

                final int size = deleteEntities.size();
                for (int i = 0; i < size; i++) {
                    //获取要删除的Position
                    int currentPosition = deleteEntities.get(i).getField(ShopCartItemFields.POSITION);
                    final int dataCount = data.size();
                    if (currentPosition < dataCount) {
                        //删除
                        mAdapter.remove(currentPosition);
                        //更新所删除位置后面的数据
                        for (; currentPosition < dataCount - 1; currentPosition++) {
                            //获取删除位置的位置 如果删除的是position1 现在获取为position2 1已经被删了
                            int rawItemPos = data.get(currentPosition).getField(ShopCartItemFields.POSITION);
                            //更新删除位置的位置 将position2更新
                            data.get(currentPosition).setField(ShopCartItemFields.POSITION, rawItemPos - 1);
                        }
                        mAdapter.setNewData(mAdapter.getData());
                    }
                }

                checkItemCount();
            }
        });

        //清除数据事件
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getData().clear();
                mAdapter.notifyDataSetChanged();
                checkItemCount();
                if (mAdapter.getData().isEmpty()) {
                    mTvTotalPrice.setText("0");
                }
            }
        });

        //支付事件
        mTvShopCartPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createOrder();
            }
        });
    }

    //创建订单，和支付没有关系
    private void createOrder() {
        final String orderUrl = "";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        orderParams.put("userid", 12222);
        orderParams.put("amount", 0.01);
        orderParams.put("comment", "测试");
        orderParams.put("type", 1);
        orderParams.put("ordertype", 0);
        orderParams.put("isanonymous", true);
        orderParams.put("followeduser", 0);
        RestClient.builder()
                .url(orderUrl)
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, "ORDER" + response);
                        final int orderId = JSON.parseObject(response).getInteger("result");
                        FastPay.create(ShopCartDelegate.this)
                                .setPayResultListener(ShopCartDelegate.this)
                                .setOrderId(orderId)
                                .beginPayDialog();
                    }
                })
                .build()
                .put();
    }

    @Override
    public void onItemClick(double itemTotalPrice) {
        final double price = mAdapter.getTotalPrice();
        mTvTotalPrice.setText(String.valueOf(price));
    }

    @Override
    public void upData(double price) {
        mTvTotalPrice.setText(String.valueOf(price));
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
