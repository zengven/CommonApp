package com.yexin.commonlib.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Arrays;

/**
 * author: zengven
 * date: 2017/6/21 17:08
 * desc: view 工具类
 */
public class ViewUtil {

    /**
     * reset view size, the width is screen width
     *
     * @param view
     * @param ratio 宽高比
     */
    public static void resetSize(View view, float ratio) {
        resetSize(view, DisplayUtil.getScreenWidth(view.getContext()), ratio);
    }

    /**
     * reset view size
     *
     * @param view
     * @param width new width
     * @param ratio 宽高比
     */
    public static void resetSize(View view, int width, float ratio) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = (int) (width / ratio);
        view.setLayoutParams(layoutParams);
    }

    /**
     * reset view size
     *
     * @param view
     * @param height new height
     */
    public static void resetSize(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }

    /**
     * 自动在没行末尾加上换行符,用于处理中英文排版参差不齐现象
     *
     * @param textView
     * @param content
     * @return
     */
    public static String autoSplitText(TextView textView, String content) {
        final Paint tvPaint = textView.getPaint(); //paint，包含字体等信息
        final float tvWidth = textView.getWidth() - textView.getPaddingLeft() - textView.getPaddingRight(); //控件可用宽度
        //将原始文本按行拆分
        String[] rawTextLines = content.replaceAll("\r", "").split("\n");
        StringBuilder sbNewText = new StringBuilder();
        for (String rawTextLine : rawTextLines) {
            if (tvPaint.measureText(rawTextLine) <= tvWidth) {
                //如果整行宽度在控件可用宽度之内，就不处理了
                sbNewText.append(rawTextLine);
            } else {
                //如果整行宽度超过控件可用宽度，则按字符测量，在超过可用宽度的前一个字符处手动换行
                float lineWidth = 0;
                for (int cnt = 0; cnt != rawTextLine.length(); ++cnt) {
                    char ch = rawTextLine.charAt(cnt);
                    lineWidth += tvPaint.measureText(String.valueOf(ch));
                    if (lineWidth <= tvWidth) {
                        sbNewText.append(ch);
                    } else {
                        sbNewText.append("\n");
                        lineWidth = 0;
                        --cnt;
                    }
                }
            }
            sbNewText.append("\n");
        }
        //把结尾多余的\n去掉
        if (!content.endsWith("\n")) {
            sbNewText.deleteCharAt(sbNewText.length() - 1);
        }
        return sbNewText.toString();
    }

    /**
     * 获取resid获取原生Drawable
     *
     * @param context
     * @param resid
     * @return
     */
    public Drawable getDrawableFromAttr(Context context, int resid) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(resid, typedValue, true);
        int[] attribute = new int[]{resid};
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
        return typedArray.getDrawable(0);
    }

    /**
     * 设置textview filter
     *
     * @param textView
     * @param inputFilters
     */
    public static void setFilters(TextView textView, InputFilter... inputFilters) {
        if (inputFilters.length <= 0) {
            return;
        }
        InputFilter[] editFilters = textView.getFilters();
        InputFilter[] newFilters = Arrays.copyOf(editFilters, editFilters.length + inputFilters.length);
        System.arraycopy(inputFilters, 0, newFilters, editFilters.length, inputFilters.length);
        textView.setFilters(newFilters);
    }

    /**
     * 批量给view设置ClickListener
     *
     * @param listener
     * @param views
     */
    public static void setClickListener(View.OnClickListener listener, View... views) {
        if (listener == null || views.length <= 0) {
            return;
        }
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 查找最合适的viewgroup
     *
     * @param view
     * @return
     */
    public static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);
        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }
}
