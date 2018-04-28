package com.lenovo.video.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by KERWIN on 2017-12-07.
 */

public class KeyboardUtil {


    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        LogUtils.d("");
        if (imm.isActive() && activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    public static void hideKeyboard2(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        LogUtils.d("imm.isActive() = " + imm);
        if (imm != null) {
            LogUtils.d("imm.isActive() = " + imm.isActive());
            if (imm.isActive()) {
                editText.requestFocus();
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }

        }
    }
}
