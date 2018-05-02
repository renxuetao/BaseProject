package com.lenovo.video.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
import com.lenovo.video.R;
import com.lenovo.video.activity.base.BaseActivity;
import com.lenovo.video.app.MyApplication;
import com.lenovo.video.fragment.FullVideoFragment;
import com.lenovo.video.fragment.HotVideoFragment;
import com.lenovo.video.fragment.LiveVideoFragment;
import com.lenovo.video.fragment.MyFragment;
import com.lenovo.video.fragment.VipVideoFragment;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketResponsePacket;

import java.lang.ref.WeakReference;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager = null;
    private FragmentTransaction transaction = null;
    private FullVideoFragment fullVideoFragment;
    private HotVideoFragment hotVideoFragment;
    private LiveVideoFragment liveVideoFragment;
    private MyFragment myFragment;
    private VipVideoFragment vipVideoFragment;

    /**
     * 按钮图片
     */
    @BindView(R.id.tab_icon_1)
    ImageView tab_icon_1;
    @BindView(R.id.tab_icon_2)
    ImageView tab_icon_2;
    @BindView(R.id.tab_icon_3)
    ImageView tab_icon_3;
    @BindView(R.id.tab_icon_4)
    ImageView tab_icon_4;
    @BindView(R.id.tab_icon_5)
    ImageView tab_icon_5;

    @BindView(R.id.linearLayout_tab_1)
    View tab_view_1;
    @BindView(R.id.linearLayout_tab_2)
    View tab_view_2;
    @BindView(R.id.linearLayout_tab_3)
    View tab_view_3;
    @BindView(R.id.linearLayout_tab_4)
    View tab_view_4;
    @BindView(R.id.linearLayout_tab_5)
    View tab_view_5;
    @BindView(R.id.tab_menu)
    public LinearLayout tab_menu = null;

    public int tab_index = -1;
    private MyHandler myHandler = null;


    /**
     * 弱引用
     */
    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivityReference;

        public MyHandler(MainActivity activity) {
            mActivityReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivityReference.get();
            if (activity != null) {
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initTools() {
        myHandler = new MyHandler(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            Fragment f1 = fragmentManager.getFragment(savedInstanceState, FullVideoFragment.class.getName());
            if(f1 != null){
                fullVideoFragment = (FullVideoFragment)f1;
            }
            Fragment f2 = fragmentManager.getFragment(savedInstanceState, HotVideoFragment.class.getName());
            if(f1 != null){
                hotVideoFragment = (HotVideoFragment)f1;
            }
            Fragment f3 = fragmentManager.getFragment(savedInstanceState, VipVideoFragment.class.getName());
            if(f1 != null){
                vipVideoFragment = (VipVideoFragment)f1;
            }
            Fragment f4 = fragmentManager.getFragment(savedInstanceState, LiveVideoFragment.class.getName());
            if(f1 != null){
                liveVideoFragment = (LiveVideoFragment)f1;
            }
            Fragment f5 = fragmentManager.getFragment(savedInstanceState, MyFragment.class.getName());
            if(f1 != null){
                myFragment = (MyFragment)f1;
            }
            MyApplication.getInstants().isBind = MyApplication.getInstants().bindService(MyApplication.getInstants().mServiceIntent, MyApplication.getInstants().conn, BIND_AUTO_CREATE);
        } else {

        }

        setTabSelection(0);

        tab_view_1.setOnClickListener(this);
        tab_view_2.setOnClickListener(this);
        tab_view_3.setOnClickListener(this);
        tab_view_4.setOnClickListener(this);
        tab_view_5.setOnClickListener(this);

    }

    @Override
    protected void initStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void getMessage(SocketClient client, @NonNull SocketResponsePacket responsePacket) {

    }

    public void setTabSelection(int index) {
        // 开启一个Fragment事务
        transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        // 清除
        clearBackground();
        //tab_index 默认值改为-1， 因为在比系统杀死，重新回到此界面的时候，tabFragment_1.reset()里面的auto为空，会崩掉，
        tab_index = index;
        switch (index) {
            case 0:
                tab_icon_1.setBackgroundResource(R.mipmap.ic_launcher); //message
                if (fullVideoFragment == null) {
                    // 如果Fragment为空，则创建一个并添加到界面上
                    fullVideoFragment = new FullVideoFragment();
                    transaction.add(R.id.content, fullVideoFragment);
                } else {
                    // 如果Fragment不为空，则直接将它显示出来
                    transaction.show(fullVideoFragment);
                }
                break;
            case 1:
                tab_icon_2.setBackgroundResource(R.mipmap.ic_launcher); //refresh
                if (hotVideoFragment == null) {
                    // 如果Fragment为空，则创建一个并添加到界面上
                    hotVideoFragment = new HotVideoFragment();
                    transaction.add(R.id.content, hotVideoFragment);
                } else {
                    // 如果Fragment不为空，则直接将它显示出来
                    transaction.show(hotVideoFragment);
                }
                break;
            case 2:
                tab_icon_3.setBackgroundResource(R.mipmap.ic_launcher); //Groups
                if (vipVideoFragment == null) {
                    // 如果Fragment为空，则创建一个并添加到界面上
                    vipVideoFragment = new VipVideoFragment();
                    transaction.add(R.id.content, vipVideoFragment);
                } else {
                    // 如果Fragment不为空，则直接将它显示出来
                    transaction.show(vipVideoFragment);
                }
                break;
            case 3:
                tab_icon_4.setBackgroundResource(R.mipmap.ic_launcher); //me
                if (liveVideoFragment == null) {
                    // 如果Fragment为空，则创建一个并添加到界面上
                    liveVideoFragment = new LiveVideoFragment();
                    transaction.add(R.id.content, liveVideoFragment);
                } else {
                    // 如果Fragment不为空，则直接将它显示出来
                    transaction.show(liveVideoFragment);
                }
                break;
            case 4://Game
                tab_icon_5.setBackgroundResource(R.mipmap.ic_launcher);
                if (myFragment == null) {
                    // 如果Fragment为空，则创建一个并添加到界面上
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                } else {
                    // 如果Fragment不为空，则直接将它显示出来
                    transaction.show(myFragment);
                }
                break;
            default:
                break;
        }
        transaction.commitAllowingStateLoss/*commit*/();
    }

    /**
     * 隐藏Fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fullVideoFragment != null) {
            transaction.hide(fullVideoFragment);
        }
        if (hotVideoFragment != null) {
            transaction.hide(hotVideoFragment);
        }
        if (vipVideoFragment != null) {
            transaction.hide(vipVideoFragment);
        }
        if (liveVideoFragment != null) {
            transaction.hide(liveVideoFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearLayout_tab_1:
                tab_menu.clearAnimation();
                tab_menu.setVisibility(View.VISIBLE);
                setTabSelection(0);
                fullVideoFragment.setUserVisibleHint(true);
                break;
            case R.id.linearLayout_tab_2:
                tab_menu.clearAnimation();
                tab_menu.setVisibility(View.VISIBLE);
                setTabSelection(1);
                hotVideoFragment.setUserVisibleHint(true);
                break;
            case R.id.linearLayout_tab_3:
                tab_menu.clearAnimation();
                tab_menu.setVisibility(View.VISIBLE);
                setTabSelection(2);
                vipVideoFragment.setUserVisibleHint(true);
                break;
            case R.id.linearLayout_tab_4:
                tab_menu.clearAnimation();
                tab_menu.setVisibility(View.VISIBLE);
                setTabSelection(3);
                liveVideoFragment.setUserVisibleHint(true);
                break;
            case R.id.linearLayout_tab_5:
                tab_menu.clearAnimation();
                tab_menu.setVisibility(View.VISIBLE);
                setTabSelection(4);
                myFragment.setUserVisibleHint(true);
                break;
            default:
                break;
        }
    }

    /**
     * 清除点击效果
     */
    private void clearBackground() {
        tab_icon_1.setBackgroundResource(R.mipmap.ic_launcher);
        tab_icon_2.setBackgroundResource(R.mipmap.ic_launcher);
        tab_icon_3.setBackgroundResource(R.mipmap.ic_launcher);
        tab_icon_4.setBackgroundResource(R.mipmap.ic_launcher);
        tab_icon_5.setBackgroundResource(R.mipmap.ic_launcher);
    }
}