package com.dianbin.latte.delegates.web.client;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dianbin.latte.delegates.web.WebDelegate;
import com.dianbin.latte.delegates.web.route.Router;

/**
 * Created by ZHEN on 2018/3/8.
 */

public class WebViewClientImpl extends WebViewClient{
    private static final String TAG = "WebViewClientImpl-vv";

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d(TAG, url);
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }
}
