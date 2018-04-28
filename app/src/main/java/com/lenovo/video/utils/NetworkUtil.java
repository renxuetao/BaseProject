package com.lenovo.video.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lenovo.video.R;

public class NetworkUtil {
    /**
     * 没有连接网络
     */
    private static final String NETWORK_NONE = "";
    /**
     * 移动网络
     */
    private static final String NETWORK_MOBILE = "您正在使用移动网络访问数据";


    public static String getNetState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return context.getResources().getString(R.string.sig_hint);
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }

    /**
     * 判断是否联网
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        NetworkInfo info = getNetworkInfos(context);
        if (info == null || !info.isConnected()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否联系WiFi
     *
     * @param context
     * @return
     */
    public static boolean isConnectedByWifi(Context context) {
        NetworkInfo info = getNetworkInfos(context);
        return info != null
                && info.isConnected()
                && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    private static NetworkInfo getNetworkInfos(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connManager.getActiveNetworkInfo();
    }

    /**
     * 判断是否是移动网络
     *
     * @param context
     * @return
     */
    public static boolean is4G(Context context) {
        NetworkInfo networkInfo = getNetworkInfos(context);
        return networkInfo != null && networkInfo.isConnected()
                && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }


}
