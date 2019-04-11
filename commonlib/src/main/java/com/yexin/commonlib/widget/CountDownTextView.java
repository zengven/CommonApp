package com.yexin.commonlib.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import com.yexin.commonlib.R;

/**
 * author: zengven
 * date: 2017/7/13 12:37
 * desc: 倒计时textview
 */
public class CountDownTextView extends AppCompatTextView implements Runnable {

    private boolean mIsCountDown = false; //是否倒计时
    private int mCountDownTime = 4;//倒计时默认4S

    public CountDownTextView(Context context) {
        super(context);
    }

    public CountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置倒计时时长
     *
     * @param time
     */
    public void setCountDownTime(int time) {
        this.mCountDownTime = time;
    }

    /**
     * 是否正在运行
     *
     * @return
     */
    public boolean isRun() {
        return mIsCountDown;
    }

    public void start() {
        this.mIsCountDown = true;
        run();
    }

    public void stop() {
        this.mIsCountDown = false;
    }

    @Override
    public void run() {
        if (mIsCountDown) {
            computeTime();
            String format = String.format(String.valueOf(getContext().getText(R.string.skip_time)), mCountDownTime);
            int index = format.indexOf(String.valueOf(mCountDownTime));
            SpannableStringBuilder builder = new SpannableStringBuilder(format);
            builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_FECE54)), index, index + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            this.setText(builder);
            postDelayed(this, 1000);
        } else {
            removeCallbacks(null);
        }
    }

    private void computeTime() {
        mCountDownTime--;
        if (mCountDownTime == 0) {
            stop();
            if (mOnCountDownEndListener != null) {
                mOnCountDownEndListener.OnCountDownEnd();
            }
        }
    }

    private OnCountDownEndListener mOnCountDownEndListener;

    public interface OnCountDownEndListener {
        void OnCountDownEnd();
    }

    public void setOnCountDownEndListener(OnCountDownEndListener listener) {
        this.mOnCountDownEndListener = listener;
    }
}
