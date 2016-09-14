package com.mota.wp.mota.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MyView extends View{
    private static final String TAG = MyView.class.getSimpleName();
    private int mCount = 0;
    private Bitmap bodyBitmap;
    private Context context;
    // 屏幕的高度和宽度
    private int screenHeight;
    private int screenWidth;
    // 每个小格子的高度和宽度
    private int eachHeight;
    private int eachWidth;

    // 这个构造函数是在xml里面引用的
    public MyView(Context context, AttributeSet attrSet)
    {
        super(context,attrSet);
        // TODO Auto-generated constructor stub
        this.context = context;

        // 获得屏幕的高和宽
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        eachHeight = screenHeight / 32;
        eachWidth = screenWidth / 20;
        // 初始化图片
//        bodyBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.body);
//        this.initSnack();
    }

    public MyView(Context context) {
        super(context);
        Log.i(TAG, "否早2");
    }


    @Override
    public void onDraw(Canvas canvas){
        Log.i(TAG, "onDraw");
        if (mCount < 100) {
            mCount++;
        }else{
            mCount = 0;
        }
        // 绘图
        Paint paint = new Paint();
//        canvas.drawBitmap(bodyBitmap, p_x, p_y, paint);
        // 绘图
//        canvas.drawRect(this.getLeft(), this.getTop(), this.getRight(),
//                this.getBottom(), paint);
        Log.i(TAG,"drawRect count:"+mCount);
    }


}