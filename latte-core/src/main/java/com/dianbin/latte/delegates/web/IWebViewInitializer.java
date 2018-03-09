package com.dianbin.latte.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by ZHEN on 2018/3/8.
 */

public interface IWebViewInitializer {
    WebView initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();
}
