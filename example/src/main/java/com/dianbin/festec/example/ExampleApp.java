package com.dianbin.festec.example;

import android.app.Application;

import com.dianbin.latte.app.Latte;
import com.dianbin.latte.ec.database.DatabaseManager;
import com.dianbin.latte.ec.icon.FontEcModule;
import com.dianbin.latte.net.interceptors.DebugInterceptor;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by ZHEN on 2018/2/27.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://192.168.0.106/RestServer/api/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSeccret("")
                .configure();
//        initStetho();
        DatabaseManager.getInstance().init(this);
    }

//    private void initStetho(){
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                .build()
//        );
//    }
}
