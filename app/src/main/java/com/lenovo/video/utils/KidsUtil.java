package com.lenovo.video.utils;

import android.content.Context;
import android.os.UserManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wanggl8 on 2018/3/27.
 */

public class KidsUtil {
    public static boolean isKids(Context context) {
        UserManager userManager = (UserManager) context.getSystemService(Context.USER_SERVICE);
        try {
            Class<?> userHandle = Class.forName("android.os.UserHandle");
            Method myUserId = userHandle.getMethod("myUserId", null);
            int id = (Integer) myUserId.invoke(null, null);
            Class<?> um = Class.forName("android.os.UserManager");
            Method getUserInfo = um.getMethod("getUserInfo", int.class);
            Object obj = getUserInfo.invoke(userManager, id);
            Class<?> info = obj.getClass();
            Method isKid = info.getMethod("isKid", null);
            boolean kid = (boolean) isKid.invoke(obj, null);
            LogUtils.d("KidsUtil", "user id:" + id + ",kids:" + kid);
            return kid;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }


}
