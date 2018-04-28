package com.lenovo.video.app;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ActivityManager {

	private static Stack<Activity> activityStack;
	private static ActivityManager instance;

	private ActivityManager() {
	}

	public static ActivityManager getScreenManager() {
		if (instance == null) {
			instance = new ActivityManager();
		}
		return instance;
	}

	// 退出栈顶Activity
	public void popActivity(Activity activity) {
		if (activity != null) {
			// 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
			activity.finish();
			activityStack.remove(activity);
			activity = null;
		}
	}

	// 获得当前栈顶Activity
	public Activity currentActivity() {
		Activity activity = null;
		if (!activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	// 将当前Activity推入栈中
	public void pushActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		Activity curActivity = currentActivity();
//		if (curActivity != null && UpdateDialogActivity.class.isInstance(curActivity)) {
//			popActivity(curActivity);
//			activityStack.add(activity);
//			Intent updateDialogIntent = new Intent(activity, UpdateDialogActivity.class);
//			updateDialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			updateDialogIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//			activity.startActivity(updateDialogIntent);
//		} else {
			activityStack.add(activity);
//		}
	}

	// 退出栈中所有Activity
	public void popAllActivityExceptOne(Class cls) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(cls)) {
				break;
			}
			popActivity(activity);
		}
	}

	public void popAllActivity() {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			popActivity(activity);
		}
	}

	// 退出特定Activity
	public void popActivity(Class cls) {
		List delList = new ArrayList();
		for (Iterator it = activityStack.iterator(); it.hasNext();) {
			Activity activity = (Activity) it.next();
			if (activity != null && activity.getClass().equals(cls)) {
				activity.finish();
				delList.add(activity);
			}
		}
		activityStack.removeAll(delList);
	}

	public boolean isContainActivity(Class cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				if (activity != null) {
					return true;
				}
			}
		}
		return false;
	}
}
