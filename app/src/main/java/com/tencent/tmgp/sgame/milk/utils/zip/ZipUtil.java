package com.tencent.tmgp.sgame.milk.utils.zip;



import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.File;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/**
 * @author rzq
 * @function  封装一个解压，压缩工具类
 */

 
public class ZipUtil {
    private static final String password = "Milk";

    public static void unZipFileWithProgress(final File zipFile, final String filePath, final Handler handler,
                                             final boolean isDeleteZip) throws ZipException {
        ZipFile zFile = new ZipFile(zipFile);
        zFile.setFileNameCharset("GBK");

        if (!zFile.isValidZipFile()) { //
            throw new ZipException("exception!");
        }
        File destDir = new File(filePath); // ��ѹĿ¼
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdir();
        }
        if (zFile.isEncrypted()) {
            zFile.setPassword(password); // 设置解压密码
        }

        final ProgressMonitor progressMonitor = zFile.getProgressMonitor();
        Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run() {
                    Bundle bundle = null;
                    Message msg = null;
                    try {
                        int precentDone = 0;
                        if (handler == null) {
                            return;
                        }
                        handler.sendEmptyMessage(CompressStatus.START);
                        while (true) {
                            // 每隔50ms,发送一个解压进度出去
                            Thread.sleep(50);
                            precentDone = progressMonitor.getPercentDone();
                            bundle = new Bundle();
                            bundle.putInt(CompressStatus.PERCENT, precentDone);
                            msg = new Message();
                            msg.what = CompressStatus.HANDLING;
                            msg.setData(bundle);
                            handler.sendMessage(msg); //通过 Handler将进度扔出去
                            if (precentDone >= 100) {
                                break;
                            }
                        }
                        handler.sendEmptyMessage(CompressStatus.COMPLETED);
                    } catch (InterruptedException e) {
                        bundle = new Bundle();
                        bundle.putString(CompressStatus.ERROR_COM, e.getMessage());
                        msg = new Message();
                        msg.what = CompressStatus.ERROR;
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        e.printStackTrace();
                    } finally {
                        if (isDeleteZip) {
                            zipFile.delete();//将原压缩文件删除
                        }
                    }
                }
            });
        thread.start();
        zFile.setRunInThread(true); //true 在子线程中进行解压 , false主线程中解压
        zFile.extractAll(filePath); //将压缩文件解压到filePath中...
    }
}

