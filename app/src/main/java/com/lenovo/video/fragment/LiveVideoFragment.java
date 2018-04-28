package com.lenovo.video.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lenovo.video.MainActivity;
import com.lenovo.video.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by renxuetao on 2018/4/28.
 */

public class LiveVideoFragment extends BaseFragment {

    private MainActivity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initTools() {
        ButterKnife.bind(activity);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
