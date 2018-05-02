package com.lenovo.video.activity.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lenovo.video.R;
import com.lenovo.video.activity.MainActivity;
import com.lenovo.video.activity.base.BaseActivity;
import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketResponsePacket;

import java.lang.ref.WeakReference;

public class SplashActivity extends BaseActivity {

	private static final int OPEN_MAIN = 0x00001;
	private static final int OPEN_GUIDE = 0x00002;
	private static final int OPEN_LOGIN = 0x00003;
	private static final long OPEN_TIME = 5 * 1000;

	private MyHandler myHandler = null;

	private static class MyHandler extends Handler {
		private final WeakReference<SplashActivity> mActivityReference;

		public MyHandler(SplashActivity activity) {
			mActivityReference = new WeakReference<SplashActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			SplashActivity activity = mActivityReference.get();
			if (activity != null) {
				switch (msg.what){
					case OPEN_MAIN:
						activity.startActivity(MainActivity.class);
						activity.finish();
						break;

					case OPEN_GUIDE:
						break;

					case OPEN_LOGIN:
						break;

					default:
				}
			}
		}
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initStatusBar() {

	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		myHandler.sendEmptyMessageDelayed(OPEN_MAIN, OPEN_TIME);
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
	protected void getMessage(SocketClient client, @NonNull SocketResponsePacket responsePacket) {

	}
}
