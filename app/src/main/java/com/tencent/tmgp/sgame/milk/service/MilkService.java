package com.tencent.tmgp.sgame.milk.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.tencent.tmgp.sgame.milk.draw.SurfaceView;
import com.tencent.tmgp.sgame.milk.utils.Milk;


public class MilkService extends Service {
    public static boolean mIsDrawing;
    private final String TAG = "MilkService";
    private WindowManager Milk_WM;
    private WindowManager.LayoutParams MilkParams;
    private android.view.SurfaceView milk_sv;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: 创建服务");//创建服务
        MilkFloatingWindow();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand：开始服务");//开始服务
        return super.onStartCommand(intent, flags, startId);

    }

    private void MilkFloatingWindow() {
        milk_sv = new android.view.SurfaceView(getApplicationContext());
        milk_sv.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        milk_sv.setZOrderOnTop(true);
        milk_sv.getHolder().addCallback(new SurfaceHolder.Callback() {
            private SurfaceView milk;

            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                mIsDrawing = true;
                milk = new SurfaceView(getApplication(), surfaceHolder);
                milk.start();

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                synchronized (this) {
                    mIsDrawing = false;
                }



            }


        });

        Milk_WM = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        MilkParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            //Android 8.0及以后使用
            MilkParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            MilkParams.flags = Milk.AdaptationPermissions(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        } else {
            //Android 8.0以前使用
            MilkParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
            MilkParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        }
        MilkParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        MilkParams.gravity = Gravity.START | Gravity.TOP;
        MilkParams.format = PixelFormat.RGBA_8888;
        Milk_WM.addView(milk_sv, MilkParams);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy：已销毁");//销毁服务
        if (milk_sv != null) {
            Milk_WM.removeView(milk_sv);
        }
    }


}
