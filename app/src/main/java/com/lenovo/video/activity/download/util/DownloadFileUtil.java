package com.lenovo.video.activity.download.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;

import java.io.File;

/**
 * Created by renxuetao on 2018/4/30.
 */

public class DownloadFileUtil {

    public static void calcProgressToView(ProgressBar progressBar, long offset, long total) {
        final float percent = (float) offset / total;
        progressBar.setProgress((int) (percent * progressBar.getMax()));
    }


    public static File getParentFile(@NonNull Context context) {
        final File externalSaveDir = context.getExternalCacheDir();
        if (externalSaveDir == null) {
            return context.getCacheDir();
        } else {
            return externalSaveDir;
        }
    }
}
