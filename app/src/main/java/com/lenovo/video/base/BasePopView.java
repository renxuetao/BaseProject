package com.lenovo.video.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by KERWIN on 2017/11/14.
 */

public abstract class BasePopView {

    protected Context context;
    private LayoutInflater inflater;

    private PopupWindow pop;

    public BasePopView(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void init(int width, int height) {
        init(width, height, -1);
    }

    public void init(int width, int height, int animStyle) {
        init(width, height, animStyle, null);
    }

    public void init(int width, int height, int animStyle, @Nullable Drawable background) {
        pop = new PopupWindow(context);
        pop.setWidth(width);
        pop.setHeight(height);
        pop.setFocusable(isCanFocus());
        pop.setTouchable(isCanTouch());
        pop.setOutsideTouchable(isCanOutsideTouch());

        if (animStyle != -1) {
            pop.setAnimationStyle(animStyle);
        }

        if (background != null)
            pop.setBackgroundDrawable(background);
        else
            pop.setBackgroundDrawable(new ColorDrawable(0000000000));

        pop.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        View view = inflater.inflate(getLayoutID(), null);
        initView(view);
        pop.setContentView(view);
        initListener(view);
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        pop.setOnDismissListener(listener);
    }

    public void setAnimationStyle(int animStyle) {
        pop.setAnimationStyle(animStyle);
    }

    public void setBackground(Drawable background) {
        pop.setBackgroundDrawable(background);
    }

    public void showWhere(View parent, boolean isDropDown) {
        showWhere(parent, 0, 0, isDropDown);
    }

    public void showWhere(View parent, int gravity, boolean isDropDown) {
        showWhere(parent, gravity, 0, 0, isDropDown);
    }

    public void showWhere(View parent, int off_x, int off_y, boolean isDropDown) {
        showWhere(parent, Gravity.NO_GRAVITY, off_x, off_y, isDropDown);
    }

    public void showWhere(View parent, int gravity, int off_x, int off_y, boolean isDropDown) {
        if (isDropDown) {
            pop.showAsDropDown(parent, off_x, off_y, gravity);
        } else {
            pop.showAtLocation(parent, gravity, off_x, off_y);
        }

        initData();
    }

    protected void upDataPop() {
        upData();
        pop.update();
    }

    public void close() {
        if (isOpen()) {
            pop.dismiss();
        }
    }

    public boolean isOpen() {
        if (pop == null)
            return false;
        return pop.isShowing();
    }

    protected abstract int getLayoutID();

    protected abstract boolean isCanFocus();

    protected abstract boolean isCanTouch();

    protected abstract boolean isCanOutsideTouch();

    protected abstract void initView(View view);

    protected abstract void initListener(View view);

    protected abstract void initData();

    protected abstract void upData();
}
