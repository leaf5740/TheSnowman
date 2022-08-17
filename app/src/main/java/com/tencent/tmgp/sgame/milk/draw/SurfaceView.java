package com.tencent.tmgp.sgame.milk.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;

import com.tencent.tmgp.sgame.milk.service.MilkService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SurfaceView extends Thread {
    final String TAG = "SurfaceView";
    final SurfaceHolder mSurfaceHolder;
    Context mCotext;
    float px = Drawing.getPy();
    float py = Drawing.getPx();

    float id = 0;//英雄ID
    float yxhp = 0;//英雄血量
    float jncd = 0;//技能cd
    float dtx = 0;//地图x
    float dty = 0;//地图y
    float fx = 0;//方框x
    float fy = 0;//方框y
    float fw = 0;//方框w
    float buff = 0;//buff冷却
    float hc = 0;//回城
    float dzcd = 0;//大招cd
    float MapAlgorithm = 0;//小地图算法

    Paint paint1 = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();
    Paint paint4 = new Paint();
    Paint paint5 = new Paint();
    Paint paint6 = new Paint();
    Paint paint7 = new Paint();
    Paint paint8 = new Paint();
    Paint FPS = new Paint();

    Canvas canvas = null;

    double framesPerSecond;
    double lastTime;
    double fps;
    String str;

    public SurfaceView(Context context, SurfaceHolder milk) {
        this.mCotext = context;
        this.mSurfaceHolder = milk;
        initView();

    }

    //小地图算法
    private float Milk_MapAlgorithm() {
        float MapAlgorithm = 0;
        if (px > py) {
            MapAlgorithm = (float) (py * 0.314814815 / 2 / 50000);
        } else if (py > px) {
            MapAlgorithm = (float) (px * 0.314814815 / 2 / 50000);
        }
        return MapAlgorithm;
    }

    private void initView() {
        //绘制文本
        paint1.setColor(0xFFFF0000);
        paint1.setTextSize(30);
        paint1.setAntiAlias(true);
        paint1.setDither(true);
        //小地图头像
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(2);
        paint2.setAntiAlias(true);
        paint2.setDither(true);
        //方框
        paint3.setColor(Drawing.getColor1());
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(3f);
        paint3.setAntiAlias(true);
        paint3.setDither(true);
        //射线
        paint4.setColor(Drawing.getColor2());
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(1.5f);
        paint4.setAntiAlias(true);
        paint4.setDither(true);
        //英雄大招CD
        paint5.setColor(Drawing.getColor4());
        paint5.setTextSize(25);
        paint5.setAntiAlias(true);
        paint5.setDither(true);
        //血量
        paint6.setColor(Drawing.getColor3());
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeWidth(6f);
        paint6.setAntiAlias(true);
        paint6.setDither(true);
        //血量框
        paint7.setColor(0xFFFFFFFF);
        paint7.setStyle(Paint.Style.STROKE);
        paint7.setStrokeWidth(6f);
        paint7.setAntiAlias(true);
        paint7.setDither(true);

        //技能CD
        paint8.setColor(Drawing.getColor5());
        paint8.setTextSize(25);
        paint8.setAntiAlias(true);
        paint8.setDither(true);

        FPS.setAntiAlias(true);
        FPS.setColor(Color.parseColor("#F44336"));
        FPS.setTypeface(Typeface.DEFAULT_BOLD);
        FPS.setTextSize(30);

    }

    private void CalculateFrameRate() {
        double currentTime = System.currentTimeMillis();
        ++framesPerSecond;
        if (currentTime - lastTime > 1000) {
            Log.e("FPS", "绘制帧率：" + framesPerSecond);
            fps = framesPerSecond;
            lastTime = currentTime;
            framesPerSecond = 0;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void Draw() {
        int xdttsx = Drawing.get小地图调上下();
        int xdttzy = Drawing.get小地图调左右();
        int fktsx = Drawing.get方框调上下();
        int fktzy = Drawing.get方框调左右();
        MapAlgorithm = Milk_MapAlgorithm();//小地图算法

        CalculateFrameRate();
        canvas.drawText("帧率:" + (int) fps + " FPS", 900, 30, FPS);

        File file = new File("/storage/emulated/0/.MyData.log");
        if (file == null) {
            return;
        }
        WriteData(file);

        //String str="108,100,0,39545,42529,3200.46,-834.08,61.52,0,0,0;133,100,0,41805,44973,3210.40,-842.79,60.30,8,0,0;195,100,0,42279,38969,3323.90,-820.75,63.38,8,0,0;119,100,0,40809,46357,3166.58,-847.57,59.64,8,0,0;128,100,0,45890,37258,3439.56,-814.05,64.32,8,0,0;";
        if (str != "") {
            String[] str1 = str.split(";");
            for (int i = 0; i < str1.length; i++) {
                String[] str2 = str1[i].split(",");
                try {
                    id = Float.parseFloat(str2[0]);//英雄ID
                    yxhp = Float.parseFloat(str2[1]);//英雄血量
                    jncd = Float.parseFloat(str2[2]);//技能cd
                    dtx = Float.parseFloat(str2[3]);//地图x
                    dty = Float.parseFloat(str2[4]);//地图y
                    fx = Float.parseFloat(str2[5]);//方框x
                    fy = Float.parseFloat(str2[6]);//方框y
                    fw = Float.parseFloat(str2[7]);//方框w
                    buff = Float.parseFloat(str2[8]);//buff冷却
                    hc = Float.parseFloat(str2[9]);//回城状态
                    dzcd = Float.parseFloat(str2[10]);//大招冷却

                } catch (Exception e) {
                    e.printStackTrace();
                }
                int ID = (int) id;
                Bitmap xdttxbit = Drawing.get图片头像(ID);
                if (xdttxbit != null) {
                    //屏幕小头像
                    canvas.drawBitmap(xdttxbit, 900 + 80 * i, 50, paint2);

                } else {
                    //屏幕小头像
                    canvas.drawBitmap(Drawing.get图片头像(0), 900 + 80 * i, 50, paint2);

                }
                if (yxhp != 0) {
                    //小地图头像
                    if (Drawing.getGn1()) {
                        if (xdttxbit != null) {
                            //小地图头像
                            canvas.drawBitmap(xdttxbit, dtx * MapAlgorithm + 150 + xdttzy, dty * MapAlgorithm * -1 + 150 + xdttsx, paint2);

                        } else {
                            //小地图头像
                            canvas.drawBitmap(Drawing.get图片头像(0), dtx * MapAlgorithm + 245 + xdttzy, dty * MapAlgorithm - 1 + 150 + xdttsx, paint2);

                        }
                    }
                    if (Drawing.getGn4()) {
                        //英雄血量
                        canvas.drawRoundRect(885 + 80 * i, 50, 885 + 80 * i, 100, 10, 10, paint6);
                        canvas.drawRoundRect(885 + 80 * i, 50, 885 + 80 * i, 100 - yxhp / 2, 10, 10, paint7);
                    }

                    if (fx != 0 && fy != 0) {
                        if (Drawing.getGn3()) {
                            //射线圆点
                            canvas.drawCircle(px / 2, py / 2, 3, paint4);
                            //射线
                            canvas.drawLine(px / 2, py / 2, fx + fktzy, fy - 150 / 2 + fktsx, paint4);
                        }
                        if (Drawing.getGn2()) {
                            //圆角方框
                            canvas.drawRoundRect(fx - 40 + fktzy, fy + fktsx, fx + 40 + fktzy, fy - 150 + fktsx, 0, 0, paint3);
                            /*
                            //敌人方框头像
                            if (xdttxbit != null) {
                                //屏幕小头像
                                canvas.drawBitmap(xdttxbit, fx + fktzy, fy - 150 / 2 + fktsx, paint2);

                            } else {
                                //屏幕小头像
                                canvas.drawBitmap(Drawing.get图片头像(0), fx + fktzy, fy - 150 / 2 + fktsx, paint2);

                            }*/

                        }
                    }
                }
                if (Drawing.getGn5()) {
                    //大招cd
                    if (dzcd == 0) {
                        canvas.drawText("大招:", 900 - 80, 180, paint5);
                        canvas.drawText("●", 906 + 80 * i, 180, paint5);

                    } else if (dzcd > 0) {
                        canvas.drawText("大招:", 900 - 80, 180, paint5);
                        canvas.drawText("" + (int) dzcd, 906 + 80 * i, 180, paint5);

                    }
                }

                if (Drawing.getGn6()) {
                    //技能cd
                    if (jncd == 0) {
                        canvas.drawText("技能:", 900 - 80, 130, paint8);
                        canvas.drawText("●", 906 + 80 * i, 130, paint8);

                    } else if (jncd > 0) {
                        canvas.drawText("技能:", 900 - 80, 130, paint8);
                        canvas.drawText("" + (int) jncd, 906 + 80 * i, 130, paint8);

                    }
                }

            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void WriteData(File file) {
        String str01 = "";
        if (!file.isDirectory()) {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream, StandardCharsets.UTF_8);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line = "";
                    while ((line = buffreader.readLine()) != null) {
                        str01 += line;
                    }
                    instream.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (str01 != "") {
            str = str01;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                   // if (canvas != null) {
                        //canvas = mSurfaceHolder.lockCanvas();
                        canvas = mSurfaceHolder.lockHardwareCanvas();

                        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

                        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        // if (Milk.isHorizontalScreen(mCotext)) {
                        Draw();
                        //   }
                        //sleep(100);
                 //   }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
