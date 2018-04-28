package com.lenovo.video.constants;

import android.os.Environment;

/**
 * Created by 47250 on 2017/9/8.
 */
public class StorageConstants {

    public static String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
    public static String appDirPath = sdcardPath + "/LenovoVideo";
    public static String downloadDirPath = appDirPath + "/download";

}
