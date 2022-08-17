package com.tencent.tmgp.sgame.milk.utils;

public class NativeUtils {
    
    static {
        System.loadLibrary("milk");
    }
    
    public static native String stringFromJNI();
    
    public static native String GETGONGGAO();
    
    public static native String GETGENGXIN();
    
    public static native String GETGONGGAOAPI();
    
    public static native String GETGENGXINAPI();
    
}
