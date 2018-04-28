package com.lenovo.video.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        initStatusBar();
        initPresenter();
        initData();
        initView(savedInstanceState);
        initTools();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 初始化layout
     * @return
     */
    protected abstract int getLayoutId();
    /**
     * 初始化沉浸式布局
     */
    protected abstract void initStatusBar();
    /**
     * 初始化view
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);
    /**
     * 初始化数据
     */
    protected abstract void initData();
    /**
     * 初始化P层
     */
    protected abstract void initPresenter();
    /**
     * 初始化kits
     */
    protected abstract void initTools();

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
