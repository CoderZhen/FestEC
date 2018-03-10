package com.dianbin.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.IFailure;
import com.dianbin.latte.net.callback.ISuccess;
import com.dianbin.latte.util.log.LatteLogger;

import java.util.logging.Logger;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Created by ZHEN on 2018/2/27.
 * 测试用的，现在没用了
 */

public class ExampleDelegate extends LatteDelegate {
    private static final String TAG = "ExampleDelegate-vv";

    @Override
    public Object setLayout() {
        return R.layout.delegate_exmaple;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://192.168.0.106/RestServer/api/user_profile.php")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d(TAG, response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .get();
    }
}
