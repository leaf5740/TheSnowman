package com.tencent.tmgp.sgame.milk.draw;

import android.graphics.Bitmap;

public class Drawing {
    public static float px, py;
    public static Boolean Gn1 = false, Gn2 = false, Gn3 = false, Gn4 = false, Gn5 = false, Gn6 = false;
    public static int[] itemcolor = {0xFF009688, 0xFF03DAC5, 0xFF009688, 0xFFFF0000, 0xFFFFFF00};
    public static int 小地图调上下 = 0, 小地图调左右 = 0;
    public static int 方框调上下 = 0, 方框调左右 = 0;
    //小头像
    public static Bitmap[] bitmaptx = new Bitmap[999];

    public static float getPx() {
        return px;
    }

    public static float getPy() {
        return py;
    }

    //小地图绘制
    public static Boolean getGn1() {
        return Gn1;
    }

    //方框绘制
    public static Boolean getGn2() {
        return Gn2;
    }

    //射线绘制
    public static Boolean getGn3() {
        return Gn3;
    }

    //血量
    public static Boolean getGn4() {
        return Gn4;
    }

    //大招CD
    public static Boolean getGn5() {
        return Gn5;
    }

    //召唤师CD
    public static Boolean getGn6() {
        return Gn6;
    }

    //方框颜色
    public static int getColor1() {
        return itemcolor[0];
    }

    //射线颜色
    public static int getColor2() {
        return itemcolor[1];
    }

    //血量颜色
    public static int getColor3() {
        return itemcolor[2];
    }

    //大招颜色
    public static int getColor4() {
        return itemcolor[3];
    }

    //技能颜色
    public static int getColor5() {
        return itemcolor[4];
    }

    public static int get小地图调上下() {
        return 小地图调上下;
    }

    public static int get小地图调左右() {
        return 小地图调左右;
    }

    public static int get方框调上下() {
        return 方框调上下;
    }

    public static int get方框调左右() {
        return 方框调左右;
    }

    public static Bitmap get图片头像(int txid) {
        return bitmaptx[txid];
    }

}
