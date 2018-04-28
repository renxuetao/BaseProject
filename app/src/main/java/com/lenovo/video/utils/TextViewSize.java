package com.lenovo.video.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

/**
 * 小数点前后显示字体不同
 */

public class TextViewSize {
    public static void setSize(String amount, Context context, TextView textView, int sp) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(amount);
        if (!amount.isEmpty() && amount.contains(".")) {
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(sp2px(context, sp)), amount.indexOf("."), amount.length()
                    , Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(spannableStringBuilder);
        }
    }

    private static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
