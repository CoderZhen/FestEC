package com.dianbin.latte.util.callback;

import java.util.WeakHashMap;

/**
 * Created by ZHEN on 2018/3/11.
 */

public class CallbackManager {

    private static final WeakHashMap<Object,IGlobalCallback> CALLBACKS = new WeakHashMap<>();

    private static class Holder{
        private static final CallbackManager INSTANCE = new CallbackManager();
    }

    public static CallbackManager getInstance(){
        return Holder.INSTANCE;
    }

    public CallbackManager addCallback(Object tag,IGlobalCallback callback){
        CALLBACKS.put(tag, callback);
        return this;
    }

    public IGlobalCallback getCallback(Object tag){
        //返回接口
        return CALLBACKS.get(tag);
    }

}
