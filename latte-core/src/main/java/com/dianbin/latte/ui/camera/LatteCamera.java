package com.dianbin.latte.ui.camera;

import android.net.Uri;

import com.dianbin.latte.delegates.PermissionCheckerDelegate;
import com.dianbin.latte.util.file.FileUtil;


/**
 * Created by ZHEN on 2018/3/10.
 * 照相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile(){
        return Uri.parse(FileUtil.createFile("crop_image"
                ,FileUtil.getFileNameByTime("IMG","jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
