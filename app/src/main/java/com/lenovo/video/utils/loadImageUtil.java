package com.lenovo.video.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 47250 on 2017/12/28.
 */
public class loadImageUtil {
    public static void showImage(Context mContext, String mPath, ImageView view) {
//        if (view != null && null != mPath && !mPath.isEmpty()) {
//            Picasso.with(mContext)
//                    .load(mPath)
//                    .into(view);
//        }
        if (view != null && null != mPath ) {
         //   Picasso.with(mContext).load(mPath).placeholder(mContext.getResources().getDrawable(R.mipmap.default_icon)).into(view);
            Glide.with(mContext)
                    .load(mPath)
                    .into(view);

        }
    }
}
