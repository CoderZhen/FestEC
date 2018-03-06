package com.dianbin.latte.wechat.templates;

import com.dianbin.latte.wechat.BaseWXEntryActivity;
import com.dianbin.latte.wechat.LatteWeChat;

/**
 * Created by ZHEN on 2018/3/3.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
