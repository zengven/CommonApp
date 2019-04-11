package com.yexin.commonlib.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.yexin.commonlib.utils.PreferencesUtil;


/**
 * author: zengven
 * date: 2018/3/26 11:21
 * desc: 可拖拽浮动view
 */
public class FloatingTextView extends AppCompatTextView {

    public static final String KEY_LEFT = "param_left";
    public static final String KEY_TOP = "param_top";

    private FrameLayout.LayoutParams mLayoutParams;

    public FloatingTextView(Context context) {
        super(context);
        initView(context);
    }

    public FloatingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FloatingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setClickable(true);
    }

    int mDownX, mDownY;
    int paramX, paramY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {  //onTouchEvent  dispatchTouchEvent
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = (int) event.getRawX();
                mDownY = (int) event.getRawY();
                paramX = mLayoutParams.leftMargin;
                paramY = mLayoutParams.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - mDownX;
                int dy = (int) event.getRawY() - mDownY;
                mLayoutParams.leftMargin = clampViewPositionHorizontal(paramX, dx);
                mLayoutParams.topMargin = clampViewPositionVertical(paramY, dy);
                requestLayout();
                return true; //消费移动事件
            case MotionEvent.ACTION_UP:
                int secondX = (int) event.getRawX();
                int distance = secondX - mDownX;
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
            valueAnimator.setIntValues(left, 0);
        } else {
            valueAnimator.setIntValues(left, parentWidth - getWidth());
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
                PreferencesUtil.getInstance(getContext()).putInt(KEY_LEFT, mLayoutParams.leftMargin);
                PreferencesUtil.getInstance(getContext()).putInt(KEY_TOP, mLayoutParams.topMargin);
            }
        });
        valueAnimator.start();
    }

    private int clampViewPositionHorizontal(int left, int dx) {
        int newLeft = left + dx;
        if (newLeft < 0) {
            newLeft = 0;
        }
        int parentWidth = ((ViewGroup) getParent()).getWidth();
        if (newLeft > (parentWidth - getWidth())) {
            newLeft = parentWidth - getWidth();
        }
        return newLeft;
    }

    private int clampViewPositionVertical(int top, int dy) {
        int newTop = top + dy;
        if (newTop < 0) {
            newTop = 0;
        }
        int parentHeight = ((ViewGroup) getParent()).getHeight();
        if (newTop > (parentHeight - getHeight())) {
            newTop = parentHeight - getHeight();
        }
        return newTop;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLayoutParams = (FrameLayout.LayoutParams) getLayoutParams();
    }
}
