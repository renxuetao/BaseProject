package com.lenovo.video.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.lenovo.video.MainActivity;
import com.lenovo.video.R;
import com.lenovo.video.base.BaseFragment;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by renxuetao on 2018/4/28.
 */

public class FullVideoFragment extends BaseFragment {

    private MainActivity activity;

    @BindView(R.id.indicator)
    TabPageIndicator indicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.full_frag_activity;
    }

    @Override
    protected void initStatusBar() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //如果我们要对ViewPager设置监听，用indicator设置就行了
        indicator.setOnPageChangeListener(this);
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

    @Override
    public void onPageSelected(int arg0) {
        Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
}
