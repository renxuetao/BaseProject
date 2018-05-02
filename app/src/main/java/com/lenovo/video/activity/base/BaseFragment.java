package com.lenovo.video.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by renxuetao on 2018/5/1.
 */
public abstract class BaseFragment extends Fragment implements ViewPager.OnPageChangeListener {

    View contextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contextView = inflater.inflate(getLayoutId(), container, false);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this, contextView);

        initStatusBar();
        initPresenter();
        initData();
        initTools();
        initView(savedInstanceState);
        return contextView;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Subscribe
    public void onEvent(String expand) {

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
        /**
         * 取消绑定服务
         */
        Unbinder bind = ButterKnife.bind(this, contextView);
        bind.unbind();
    }

    @Override
    public void onPageSelected(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
}
