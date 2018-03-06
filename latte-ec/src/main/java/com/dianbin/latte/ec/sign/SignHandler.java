package com.dianbin.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianbin.latte.app.AccountManager;
import com.dianbin.latte.ec.database.DatabaseManager;
import com.dianbin.latte.ec.database.UserProfile;

/**
 * Created by ZHEN on 2018/3/1.
 */

public class SignHandler {

    public static  void onSignIn(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功 标记登录过
        AccountManager.setSignState(true);
        //回调给ExampleActivity
        signListener.onSignInSuccess();
    }

    public static  void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

}
