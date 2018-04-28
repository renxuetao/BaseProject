package com.lenovo.video.utils;


import com.lenovo.video.constants.Constants;
import com.orhanobut.logger.Logger;

public class LogUtils {

    private static final String TAG = "LauncherVideo=====";

    public static void e(String tag, Object message) {
        if (Constants.isLog)
            Logger.e(TAG + tag, message + "");
    }

    public static void e(Object message) {
        if (Constants.isLog)
            Logger.e(TAG, message + "");
    }

    public static void d(String tag, Object message) {
        if (Constants.isLog)
            Logger.d(TAG + tag, message + "");
    }

    public static void d(Object message) {
        if (Constants.isLog)
            Logger.d(TAG, message + "");
    }

    public static void i(String tag, Object message) {
        if (Constants.isLog)
            Logger.i(TAG + tag, message + "");
    }

    public static void i(Object message) {
        if (Constants.isLog)
            Logger.i(TAG, message + "");
    }
}