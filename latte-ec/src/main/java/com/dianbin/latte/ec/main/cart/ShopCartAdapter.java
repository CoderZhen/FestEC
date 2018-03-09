package com.dianbin.latte.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dianbin.latte.app.Latte;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ui.recycler.MultipleFields;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;
import com.dianbin.latte.ui.recycler.MultipleRecyclerAdapter;
import com.dianbin.latte.ui.recycler.MultipleViewHolder;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by ZHEN on 2018/3/8.
 */

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;

    private final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll){
        this.mIsSelectedAll = isSelectedAll;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                Log.d(TAG, "convert: 执行了");
                //先取值
                final int id = entity.getField(MultipleFields.ID);
                final String thumb = entity.getField(MultipleFields.IMAGE_URL);
                final String title = entity.getField(ShopCartItemFields.TITLE);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final double price = entity.getField(ShopCartItemFields.PRICE);
                final int count = entity.getField(ShopCartItemFields.COUNT);

                //取出所有控件
                final AppCompatImageView imageThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconSelected = holder.getView(R.id.icon_item_shop_cart);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imageThumb);

                //在左侧勾渲染之前改变全选与否状态
                entity.setField(ShopCartItemFields.IS_SELECTED,mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);

                //根据数据状态显示左侧勾
                if (isSelected) {
                    iconSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconSelected.setTextColor(Color.GRAY);
                }
                //添加点击事件
                iconSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
