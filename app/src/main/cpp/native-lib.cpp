#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tencent_tmgp_sgame_milk_utils_NativeUtils_stringFromJNI(JNIEnv *env, jclass clazz) {
    // TODO: implement stringFromJNI()
    return env->NewStringUTF("@by Milk\n@开源日期:2021.11.1\n@Email loc1234@qq.com");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tencent_tmgp_sgame_milk_utils_NativeUtils_GETGONGGAO(JNIEnv *env, jclass clazz) {
    // TODO: implement GETGONGGAO()
    return env->NewStringUTF("(⌯꒪꒫꒪)੭ु⁾⁾公告好像获取失败了");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tencent_tmgp_sgame_milk_utils_NativeUtils_GETGENGXIN(JNIEnv *env, jclass clazz) {
    // TODO: implement GETGENGXIN()
    return env->NewStringUTF("2.0");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tencent_tmgp_sgame_milk_utils_NativeUtils_GETGONGGAOAPI(JNIEnv *env, jclass clazz) {
    // TODO: implement GETGONGGAOAPI()
    return env->NewStringUTF("https://w.eydata.net/86120b4d8025c719wg");
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_tencent_tmgp_sgame_milk_utils_NativeUtils_GETGENGXINAPI(JNIEnv *env, jclass clazz) {
    // TODO: implement GETGENGXINAPI()
    return env->NewStringUTF("https://w.eydata.net/47035EC0C40B7Ewq");
}