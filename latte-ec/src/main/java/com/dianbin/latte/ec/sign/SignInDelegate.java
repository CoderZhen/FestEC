package com.dianbin.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;

import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.net.RestClient;
import com.dianbin.latte.net.callback.ISuccess;
import com.joanzapata.iconify.widget.IconTextView;

/**
 * Created by ZHEN on 2018/2/28.
 */

public class SignInDelegate extends LatteDelegate {

    private AppCompatEditText mEmail, mPassword;
    private AppCompatButton mBtnSignIn;
    private AppCompatTextView mSignUp;
    private IconTextView mWeiXin;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
        eventListener();
    }

    private void eventListener() {

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()) {
                    RestClient.builder()
                            .url("http://192.168.0.106/RestServer/api/user_profile.php")
                            .params("email", mEmail.getText().toString())
                            .params("password", mPassword.getText().toString())
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    SignHandler.onSignIn(response, mISignListener);
                                }
                            })
                            .build()
                            .post();
                }
            }
        });

        //跳转注册
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().start(new SignUpDelegate());
            }
        });

    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }

    private void initView(View rootView) {
        mEmail = rootView.findViewById(R.id.edit_sign_in_email);
        mPassword = rootView.findViewById(R.id.edit_sign_in_password);
        mBtnSignIn = rootView.findViewById(R.id.btn_sign_in);
        mWeiXin = rootView.findViewById(R.id.icon_sign_in_wechat);
        mSignUp = rootView.findViewById(R.id.tv_link_sign_up);
    }
}
