package com.lenovo.video.utils;


import com.lenovo.video.constants.Constants;
import com.orhanobut.logger.Logger;

public class LogUtils {

    private static final String TAG = "VideoPhone:";

    public static void e(String tag, Object message) {
        if (Constants.isLog) {
//            Logger.e(TAG + tag, message + "");
            Logger.e(tag + ":" + message + "");
        }
    }

    public static void e(Object message) {
        if (Constants.isLog) {
            Logger.e(message + "");
//        Logger.e(TAG, message + "");
        }
    }

    public static void d(String tag, Object message) {
        if (Constants.isLog) {
            Logger.d(tag + ":" + message + "");
//        Logger.d(TAG + tag, message + "");
        }
    }

    public static void d(Object message) {
        if (Constants.isLog) {
            Logger.d(message + "");
//        Logger.d(TAG, message + "");
        }
    }

    public static void i(String tag, Object message) {
        if (Constants.isLog) {
            Logger.i(tag + ":" + message + "");
//        Logger.i(TAG + tag, message + "");
        }
    }

    public static void i(Object message) {
        if (Constants.isLog) {
            Logger.i(message + "");
//        Logger.i(TAG, message + "");
        }
    }
}