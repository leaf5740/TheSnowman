# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#混淆等级
-optimizationpasses 5
#混淆时不使用大小写混淆类名
-dontusemixedcaseclassnames
#不忽略指定library非公共类得classes
-dontskipnonpubliclibraryclasses
#关闭优化
-dontoptimize
#忽略警告
-ignorewarnings
#关闭预校验
-dontpreverify
#混淆过程打印详细信息
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-useuniqueclassmembernames
-keepattributes Signature


-obfuscationdictionary ../bnx.txt
-classobfuscationdictionary ../bnx.txt
-packageobfuscationdictionary ../bnx.txt




-keep class **.R$* {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}



-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

-keep class android.view.** { *; }


