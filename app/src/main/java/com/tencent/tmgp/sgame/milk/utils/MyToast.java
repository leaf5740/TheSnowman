package com.tencent.tmgp.sgame.milk.utils;


import android.content.Context;
import android.widget.Toast;


public final class MyToast {
    public static boolean isShow = true;
    public static Toast toast;

    private MyToast() {
    }

    //短时间显示Toast
    public static void showShort(Context context, CharSequence message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    //短时间显示Toast
    public static void showShort(Context context, int message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            }
            toast.show();
        }
    }

    //长时间显示Toast
    public static void showLong(Context context, CharSequence message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

    //长时间显示Toast
    public static void showLong(Context context, int message) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            } else {
                toast.cancel();//关闭吐司显示
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            }
            toast.show();
        }
    }

    //自定义显示Toast时间
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow) {
            Toast.makeText(context, message, duration).show();
        }
    }

    //自定义显示Toast时间
    public static void show(Context context, int message, int duration) {
        if (isShow) {
            Toast.makeText(context, message, duration).show();
        }
    }
}
