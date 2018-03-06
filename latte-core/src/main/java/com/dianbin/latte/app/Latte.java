package com.dianbin.latte.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by ZHEN on 2018/2/27.
 */

public final class Latte {

    public static Configurator init(Context context) {
        // FIXME: 2018/2/28 getConfiguration().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        Configurator.getInstance().getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    /*//第一种获取配置的方式 （需要强转）
    public static HashMap<Object, Object> getConfiguration() {
        return getConfigurator().getLatteConfigs();
    }*/

    //第二种获取配置的方式 （不用强转）
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return (Context) getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    //获取Configurator单例
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }



    public static Handler getHandler(){
        return getConfiguration(ConfigKeys.HANDLER);
    }
}
