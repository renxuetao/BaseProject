package com.lenovo.video.network.socket.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.lenovo.video.network.socket.SocketConnectUtil;
import com.lenovo.video.utils.LogUtils;

public class BackService extends Service {
	
	private IBinder mBinder = new XXBinder();
	private SocketConnectUtil socketConnectUtil = null;

	public class XXBinder extends Binder {
		public BackService getService() {
			return BackService.this;
		}
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

	}

	@Override  
	public int onStartCommand(Intent intent, int flags, int startId) {
		//执行文件的下载或者播放等操作
		/*
		 * 这里返回状态有三个值，分别是:
		 * 1、START_STICKY：当服务进程在运行时被杀死，系统将会把它置为started状态，但是不保存其传递的Intent对象，之后，系统会尝试重新创建服务;
		 * 2、START_NOT_STICKY：当服务进程在运行时被杀死，并且没有新的Intent对象传递过来的话，系统将会把它置为started状态，
		 *   但是系统不会重新创建服务，直到startService(Intent intent)方法再次被调用;
		 * 3、START_REDELIVER_INTENT：当服务进程在运行时被杀死，它将会在隔一段时间后自动创建，并且最后一个传递的Intent对象将会再次传递过来。
		 */
		if(intent == null){
			initSocketConnect();
		}else{
			LogUtils.d("intent is not null", "");
		}
		return START_STICKY;  
	}

	//初始化长连接
	public void initSocketConnect(){
		if(socketConnectUtil == null){
			socketConnectUtil = new SocketConnectUtil();
		}
		socketConnectUtil.getSocketClient();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
