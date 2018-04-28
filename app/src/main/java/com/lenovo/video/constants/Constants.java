package com.lenovo.video.constants;

import android.os.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 47250 on 2017/9/8.
 */
public class Constants {
    private static String TAG = "Constants";

    public static String appId = "lenovo-video";
    public static String appChannel = "2";
    public static String source = "aiqiyi";

    public static String pid = "1";//1是正式版本 pre测试版本
    //    public static String pid = "pre";//1是正式版本 pre测试版本

    public static boolean isLog = true;//log输出开关
    public static boolean isOpenBigDataTest = false;//是否开启大数据测试
    public static boolean isOpenBuglyDebug = true;//是否开启bugly的debug

    /**
     * 正式接口分发
     */
//    public static final String HOST = "http://padepg.api.epg.lenovo.com.cn/";
//    public static final String HOST =  "https://pad-urls.vgs.lenovo.com.cn/";
    /**
     *
     * 测试接口分发
     */
//    public static final String HOST = "http://padepg.test.epg.lenovo.com.cn/";
    public static final String HOST = "https://pad-urls-dev.vgs.lenovo.com.cn";
    /**
     * 分发参数
     */
    public static Map<String, String> HOST_PARA = new HashMap<>();
    /**
     * EPG系统
     */
    public static String HOST_EPG = "http://epgapi1.test.epg.lenovo.com.cn/";
    /**
     * 搜索系统
     */
    public static String HOST_SEARCH = "http://epgapi1.test.epg.lenovo.com.cn/";
    /**
     * 账号系统
     */
    public static String HOST_BSS = "http://service.test.bss.lenovo.com.cn/";
    /**
     * 统计系统
     */
    public static String HOST_BIGDATA = "http://data.test.epg.lenovo.com.cn/";
    /**
     * 测试统计系统
     */
    public static String HOST_BIGDATA_TEST = "https://video-collect-dev.vgs.lenovo.com.cn/";
    /**
     * 隐私权政策默认值
     */
    public static String PRIVATETERMS = "http://www.lenovo.com.cn/public/privacy.html";
    /**
     * 使用条款默认值
     */
    public static String USETERMS = "http://appserver.lenovo.com.cn/Public/public_bottom/legal.shtml";

    public static String SPKEY_HOSTURL = "spkey_hosturl";

    public static String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    public static String appDirPath = sdcardPath + "/LenovoVideo";
}
