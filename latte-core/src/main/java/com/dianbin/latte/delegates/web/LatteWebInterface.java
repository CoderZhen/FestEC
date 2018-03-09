package com.dianbin.latte.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Created by ZHEN on 2018/3/8.
 */

public class LatteWebInterface {
    private final WebDelegate DELEGATE;

    private LatteWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static LatteWebInterface create(WebDelegate delegate){
        return new LatteWebInterface(delegate);
    }

    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
