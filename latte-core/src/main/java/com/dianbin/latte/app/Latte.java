package com.dianbin.latte.app;

import android.content.Context;

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

    //第一种获取配置的方式 （需要强转）
    public static HashMap<Object, Object> getConfiguration() {
        return getConfigurator().getLatteConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfiguration().get(ConfigKeys.APPLICATION_CONTEXT);
    }

    //获取Configurator单例
    private static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    //第二种获取配置的方式 （不用强转）
    public static <T> T getHashMapData(Object key) {
        return getConfigurator().getConfiguration(key);
    }
}
