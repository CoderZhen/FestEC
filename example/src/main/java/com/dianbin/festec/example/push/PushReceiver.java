package com.dianbin.festec.example.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ZHEN on 2018/3/12.
 */

public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "PushReceiver-vv";


    @Override
    public void onReceive(Context context, Intent intent) {
       /* final Bundle bundle = intent.getExtras();
        final Set<String> keys = bundle.keySet();
        final JSONObject json = new JSONObject();
        for (String key : keys) {
            final Object val = bundle.get(key);
            json.put(key, val);
        }

        Log.d(TAG, json.toJSONString());

        final String pushAction = intent.getAction();
        if (pushAction.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
            //处理接受到的信息
            onReceivedMessage(bundle);
        } else if (pushAction.equals(JPushInterface.ACTION_NOTIFICATION_OPENED)) {
            //打开相应的Notification
            onOpenNotification(context,bundle);
        }*/
    }

    /*private void onReceivedMessage(Bundle bundle){
        final String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        final String msgId = bundle.getString(JPushInterface.EXTRA_MSG_ID);
        final int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
        final String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final String alert = bundle.getString(JPushInterface.EXTRA_ALERT);
    }

    private void onOpenNotification(Context context,Bundle bundle){
        final String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
        final Bundle openActivityBundle = new Bundle();
        final Intent intent = new Intent(context, ExampleActivity.class);
        intent.putExtras(openActivityBundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ContextCompat.startActivity(context,intent,null);
    }*/
}
