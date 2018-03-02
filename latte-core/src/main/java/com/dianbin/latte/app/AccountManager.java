package com.dianbin.latte.app;

import com.dianbin.latte.util.storage.LattePreference;

/**
 * Created by ZHEN on 2018/3/2.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    //保存用户登录信息，登录后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
