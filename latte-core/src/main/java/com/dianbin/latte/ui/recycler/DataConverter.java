package com.dianbin.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by ZHEN on 2018/3/4.
 * JSON数据转换规则
 */

public abstract class DataConverter {

    public abstract ArrayList<MultipleItemEntity> convert();

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData = null;

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData(){
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

}
