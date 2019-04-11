package com.yexin.commonlib.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

/**
 * author: zengven
 * date: 2018/8/20 15:54
 * desc: 可悬浮移动view
 */

public class FloatingLayout extends FrameLayout {

    public static final String KEY_LEFT = "param_left";
    public static final String KEY_TOP = "param_top";

    private int Mmargin = 0;//边距距离 px
    private LayoutParams mLayoutParams;
    private int mScaledTouchSlop; //移动最小触发距离

    private float mDownX, mDownY;
    private int mParamX, mParamY;
    private boolean mIsIntercepted = false;

    public FloatingLayout(@NonNull Context context) {
        super(context);
        initView();
    }

    public FloatingLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FloatingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FloatingLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        setClickable(true);
        mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIsIntercepted = false;
                mDownX = ev.getRawX();
                mDownY = ev.getRawY();
                mParamX = mLayoutParams.leftMargin;
                mParamY = mLayoutParams.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getRawX();
                float moveY = ev.getRawY();
                mIsIntercepted = Math.abs(moveX - mDownX) > mScaledTouchSlop || Math.abs(moveY - mDownY) > mScaledTouchSlop;//移动范围超过最小触发距离
                break;
            case MotionEvent.ACTION_UP:
                mIsIntercepted = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                mIsIntercepted = false;
                break;
        }
        return mIsIntercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {  //onTouchEvent  dispatchTouchEvent
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getRawX();
                mDownY = event.getRawY();
                mParamX = mLayoutParams.leftMargin;
                mParamY = mLayoutParams.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - mDownX;
                float dy = event.getRawY() - mDownY;
                mLayoutParams.leftMargin = clampViewPositionHorizontal(mParamX, (int) dx);
                mLayoutParams.topMargin = clampViewPositionVertical(mParamY, (int) dy);
                requestLayout();
                return true;
            case MotionEvent.ACTION_UP:
                float secondX = event.getRawX();
                float distance = secondX - mDownX;
                if (Math.abs(distance) >= 1) {
                    animateLeft(mLayoutParams.leftMargin);
                    return true; //消费事件
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void animateLeft(int left) {
        setLayerType(View.LAYER_TYPE_HARDWARE, null);
        int parentWidth = ((ViewGroup) getParent()).getWidth();
        ValueAnimator valueAnimator = ValueAnimator.ofInt();
        if (left < (parentWidth - getWidth()) / 2) {
            valueAnimator.setIntValues(left, Mmargin);
        } else {
            valueAnimator.setIntValues(left, parentWidth - getWidth() - Mmargin);
        }
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Object animatedValue = animation.getAnimatedValue();
                mLayoutParams.leftMargin = (int) animatedValue;
                requestLayout();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setLayerType(View.LAYER_TYPE_NONE, null);
            }
        });
        valueAnimator.start();
    }

    private int clampViewPositionHorizontal(int left, int dx) {
        int newLeft = left + dx;
        if (newLeft < Mmargin) {
            newLeft = Mmargin;
        }
        int parentWidth = ((ViewGroup) getParent()).getWidth();
        if (newLeft > (parentWidth - getWidth() - Mmargin)) {
            newLeft = parentWidth - getWidth() - Mmargin;
        }
        return newLeft;
    }

    private int clampViewPositionVertical(int top, int dy) {
        int newTop = top + dy;
        if (newTop < Mmargin) {
            newTop = Mmargin;
        }
        int parentHeight = ((ViewGroup) getParent()).getHeight();
        if (newTop > (parentHeight - getHeight()) - Mmargin) {
            newTop = parentHeight - getHeight() - Mmargin;
        }
        return newTop;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLayoutParams = (FrameLayout.LayoutParams) getLayoutParams();
    }

    /**
     * 设置边距
     *
     * @param margin px
     */
    public void setMargin(int margin) {
        this.Mmargin = margin;
        invalidate();
    }
}
