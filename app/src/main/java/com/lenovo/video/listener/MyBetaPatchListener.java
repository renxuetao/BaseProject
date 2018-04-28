package com.lenovo.video.listener;

import android.content.Context;

import com.tencent.bugly.beta.interfaces.BetaPatchListener;

/**
 * Created by 47250 on 2018/3/21.
 */
public class MyBetaPatchListener implements BetaPatchListener {

    private String TAG = "MyBetaPatchListener";
    private Context mContext;

    public MyBetaPatchListener(Context context) {
        this.mContext = context;

    }

    @Override
    public void onPatchReceived(String patchFile) {
//        Toast.makeText(mContext, "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadReceived(long savedLength, long totalLength) {
//        Toast.makeText(mContext,
//                String.format(Locale.getDefault(), "%s %d%%",
//                        Beta.strNotificationDownloading,
//                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
//                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadSuccess(String msg) {
//        Toast.makeText(mContext, "补丁下载成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadFailure(String msg) {
//        Toast.makeText(mContext, "补丁下载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApplySuccess(String msg) {
//        Toast.makeText(mContext, "补丁应用成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onApplyFailure(String msg) {
//        Toast.makeText(mContext, "补丁应用失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPatchRollback() {
//        Toast.makeText(mContext, "补丁回滚", Toast.LENGTH_SHORT).show();
    }
}
