package com.tencent.tmgp.sgame.milk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;


/**
 * @by MIlk
 * @2021.10.06
 * @QQ 718243104
 * @Email loc1234@qq.com
 * @自定义ViewPager 禁止左右滑动 ,取消切换动画
 */

public class MyMilkViewPager extends ViewPager {
    private boolean issliding = false;//滑动
    private boolean isanimation = false;//动画

    public MyMilkViewPager(Context context) {
        super(context);
    }

    public MyMilkViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSliding(boolean sliding) {
        this.issliding = sliding;
    }

    public void setAnimation(boolean animation) {
        this.isanimation = animation;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (issliding) {
            return super.onTouchEvent(arg0);
        } else {
            return false;
        }

    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);

    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, isanimation);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (issliding) {
            return super.onInterceptTouchEvent(arg0);
        } else {
            return false;
        }
    }

}