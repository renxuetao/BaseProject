package com.lenovo.video.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.view.View;

import com.lenovo.video.MainActivity;
import com.lenovo.video.R;
import com.lenovo.video.constants.Constants;
import com.lenovo.video.constants.StorageConstants;
import com.lenovo.video.listener.MyBetaPatchListener;
import com.lenovo.video.network.socket.service.BackService;
import com.lenovo.video.utils.LogUtils;
import com.lenovo.video.utils.UnCrashThread;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.beta.ui.UILifecycleListener;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.io.File;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by 47250 on 2017/12/20.
 */
public class MyApplication extends Application {

    private String TAG = "MyApplication";
    private static MyApplication myApplication;
    public ActivityManager activityManager = null;

    public synchronized static MyApplication getInstants() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e(TAG, "Myapplication onCreate");
        myApplication = this;
        //init autolayout
        AutoLayoutConifg.getInstance().useDeviceSize();
        //setting font
        setTypeface();
        //init bugly
        buglyInit();
        //init logger display
        Logger.addLogAdapter(new AndroidLogAdapter());
        //初始化服务
        mServiceIntent = new Intent(this, BackService.class);
        activityManager = ActivityManager.getScreenManager();
    }

    /**
     * bugly初始化
     */
    private void buglyInit() {
        setStrictMode();

        //自动检查更新开关
        Beta.autoCheckUpgrade = true;
        //升级检查周期设置
        Beta.upgradeCheckPeriod = 60 * 1000;
        //设置通知栏大图标
        Beta.largeIconId = R.mipmap.ic_launcher;
        //设置状态栏小图标
        Beta.smallIconId = R.mipmap.ic_launcher;
        //设置更新弹窗默认展示的banner
        Beta.defaultBannerId = R.mipmap.ic_launcher;
        //设置sd卡的Download为更新资源存储目录
        Beta.storageDir = new File(StorageConstants.downloadDirPath);
        //添加可显示弹窗的Activity
        Beta.canShowUpgradeActs.add(MainActivity.class);
        //设置自定义升级对话框UI布局
//        Beta.upgradeDialogLayoutId = R.layout.upgrade_dialog;
        //设置自定义tip弹窗UI布局
//        Beta.tipsDialogLayoutId = R.layout.tips_dialog;
        Beta.upgradeDialogLifecycleListener = new UILifecycleListener<UpgradeInfo>() {
            @Override
            public void onCreate(Context context, View view, UpgradeInfo upgradeInfo) {
                LogUtils.d(TAG, "onCreate");
            }

            @Override
            public void onStart(Context context, View view, UpgradeInfo upgradeInfo) {
                LogUtils.d(TAG, "onStart");
            }

            @Override
            public void onResume(Context context, View view, UpgradeInfo upgradeInfo) {
                LogUtils.d(TAG, "onResume");
                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:
            }

            @Override
            public void onPause(Context context, View view, UpgradeInfo upgradeInfo) {
                LogUtils.d(TAG, "onPause");
            }

            @Override
            public void onStop(Context context, View view, UpgradeInfo upgradeInfo) {
                LogUtils.d(TAG, "onStop");
            }

            @Override
            public void onDestroy(Context context, View view, UpgradeInfo upgradeInfo) {
                LogUtils.d(TAG, "onDestory");
            }

        };
        //设置是否显示消息通知
        Beta.enableNotification = true;
        //设置Wifi下自动下载
        Beta.autoDownloadOnWifi = false;
        //设置是否显示弹窗中的apk信息
        Beta.canShowApkInfo = true;


        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁
        Beta.canAutoDownloadPatch = true;
        // 设置是否提示用户重启
        //  Beta.canNotifyUserRestart = true;
        // 设置是否自动合成补丁
        Beta.canAutoPatch = true;
        //设置bugly下载监听
        Beta.betaPatchListener = new MyBetaPatchListener(this);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId 调试时，将第三个参数改为true
        Bugly.init(this, getResources().getString(R.string.bugly_app_id), Constants.isOpenBuglyDebug);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }

    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }

    //设置字体
    private void setTypeface() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/pingfang.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public boolean isBind = false;
    public Intent mServiceIntent;
    public BackService backService;
    public ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtils.d("onServiceDisconnected:", "NO!!!!!!!!!!!!");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtils.d("onServiceConnected:", "YES!");
            backService = ((BackService.XXBinder) service).getService();
            backService.initSocketConnect();
        }
    };

    public void onunbind() {
        if (isBind) {
            isBind = false;
            unbindService(conn);
            stopService(mServiceIntent);
        }
    }

    public void exit() {
        activityManager.popAllActivity();
        onunbind();
        System.exit(0);
    }
}
