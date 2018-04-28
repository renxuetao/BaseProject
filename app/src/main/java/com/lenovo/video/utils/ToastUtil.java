package com.lenovo.video.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * toast工具类
 */
public class ToastUtil {
    public static final Handler handler = new Handler(Looper.getMainLooper());
    private static Toast toast;

    public static void show(final Context context, final CharSequence string) {
        doToast(context, string, -1);
    }

    public static void show(final Context context, final int stringId) {
        doToast(context, null, stringId);
    }

    private static void doToast(final Context context, @Nullable final CharSequence string, final int stringId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    if (toast == null) {
                        toast = new Toast(context);
                    }
                    if (string != null) {
                        toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
                    } else if (stringId != -1) {
                        toast = Toast.makeText(context, context.getResources().getText(stringId), Toast.LENGTH_SHORT);
                    } else {
                        return;
                    }
                    toast.show();
                } catch (Exception e) {
                }
            }
        };
        handler.post(runnable);
    }
}
