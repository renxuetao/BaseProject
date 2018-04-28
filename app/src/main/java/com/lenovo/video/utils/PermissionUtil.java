package com.lenovo.video.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.lenovo.video.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {

    public static final int PERMISSION_RESULT = 1;
    private static final String[] permission = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    public static void requestPermission(boolean isShow, Context context, PermissionCallBack callBack) {
        if (permission.length != 0) {
            List<String> permissionsList = new ArrayList<>();
            for (String perm : permission) {
                if (perm.equals(Manifest.permission.READ_PHONE_STATE)) {
                    if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                        permissionsList.add(perm);
                    } else {
                        String DeviceId = PublicUtil.getDeviceId(context);
                        if (null != DeviceId) {
                            Constants.HOST_PARA.put("deviceId", DeviceId);
                        }
                    }
                } else {
                    if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                        permissionsList.add(perm);
                    }
                }

            }
            if (permissionsList.size() > 0) {
                if (isShow) {
                    LogUtils.i("PermissionUtil", "isRequestPerms: " + SpUtil.getBoolean("isRequestPerms", true));
                    if (SpUtil.getBoolean("isRequestPerms", true)) {
                        requestPerms(permissionsList, callBack);
                        SpUtil.putBoolean("isRequestPerms", false);
                    }
                } else {
                    SpUtil.putBoolean("isRequestPerms", true);
                }
            }
        }
    }

    private static void requestPerms(List<String> permissionsList, PermissionCallBack callBack) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (callBack != null)
                callBack.onRequest(permissionsList.toArray(new String[permissionsList.size()]), PERMISSION_RESULT);
        }
    }

    public interface PermissionCallBack {
        void onRequest(@NonNull String[] permissions, int requestCode);
    }
}
