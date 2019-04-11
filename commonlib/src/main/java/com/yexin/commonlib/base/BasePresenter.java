package com.yexin.commonlib.base;

import android.app.Activity;

/**
 * author: zengven
 * date: 2017/6/15
 * Desc: Presenter基础接口
 */
public interface BasePresenter {
    void requestData(Activity activity, boolean isShowLoading);
}
