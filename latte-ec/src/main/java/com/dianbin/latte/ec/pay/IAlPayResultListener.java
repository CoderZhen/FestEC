package com.dianbin.latte.ec.pay;

/**
 * Created by ZHEN on 2018/3/10.
 */

public interface IAlPayResultListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
