package com.lenovo.video.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Gravity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by 47250 on 2018/1/20.
 */
public class ImageUtils {
    public static void loadHeadImage(final Context context, String url, final ImageView photo, final boolean isBord) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(photo) {
            @Override
            protected void setResource(Bitmap resource) {
                if (isBord == true) {
                    Drawable image = setRoundImageWithBorder(context, resource);
                    photo.setImageDrawable(image);
                } else {
                    RoundedBitmapDrawable drawable = setNoBordRoundImageWithBorder(context, resource);
                    photo.setImageDrawable(drawable);
                }
            }
        });
    }

    public static void loadHeadImage2(final Context context, int url, final ImageView photo, final boolean isBord) {
        Glide.with(context).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(photo) {
            @Override
            protected void setResource(Bitmap resource) {
                if (isBord == true) {
                    Drawable image = setRoundImageWithBorder(context, resource);
                    photo.setImageDrawable(image);
                } else {
                    RoundedBitmapDrawable drawable = setNoBordRoundImageWithBorder(context, resource);
                    photo.setImageDrawable(drawable);
                }
            }
        });
    }

    /**
     * 设置RoundedBitmapDrawableFactory
     * 其方法:
     * setCircular  是否是圆形
     * setAlpha 透明度
     * setAntiAlias抗锯齿
     * setDither 防抖动
     *
     * @param context
     * @param bitmap
     * @return
     */
    private static Drawable setRoundImageWithBorder(Context context, Bitmap bitmap) {
        //获取原图宽高
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        //边框宽度 pixel设置20像素
        int borderWidthHalf = 4;
        int bitmapSquareWidth = Math.min(bitmapWidth, bitmapHeight);
        int newBitmapSquareWidth = bitmapSquareWidth + borderWidthHalf;
        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth, newBitmapSquareWidth, Bitmap.Config.ARGB_8888);
        //裁剪后图像, X,Y除以2进行一个中心裁剪
        Canvas canvas = new Canvas(roundedBitmap);
        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;
        canvas.drawBitmap(bitmap, x / 2, y / 2, null);
        //添加图片边框
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidthHalf);
        paint.setColor(Color.WHITE);  //设置边框为白色
        canvas.drawCircle(canvas.getWidth() / 2, canvas.getWidth() / 2, newBitmapSquareWidth / 2, paint);


        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), roundedBitmap);
        roundedBitmapDrawable.setGravity(Gravity.CENTER);
        roundedBitmapDrawable.setCircular(true);    //设置为原形图片
        return roundedBitmapDrawable;
    }

    /**
     * 不包含白色边框
     *
     * @param context
     * @return
     */
    private static RoundedBitmapDrawable setNoBordRoundImageWithBorder(Context context, Bitmap bitmap) {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);    //设置为原形图片
        return roundedBitmapDrawable;
    }
}