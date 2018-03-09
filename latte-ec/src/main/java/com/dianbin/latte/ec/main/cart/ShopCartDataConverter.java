package com.dianbin.latte.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianbin.latte.ui.recycler.DataConverter;
import com.dianbin.latte.ui.recycler.ItemType;
import com.dianbin.latte.ui.recycler.MultipleFields;
import com.dianbin.latte.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by ZHEN on 2018/3/8.
 */

public class ShopCartDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final int id = data.getInteger("id");
            final double price = data.getInteger("price");
            final int count = data.getInteger("count");
            final String thumb = data.getString("thumb");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.COUNT, count)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.IS_SELECTED,false)
                    .build();

            dataList.add(entity);
        }

        return dataList;
    }
}
