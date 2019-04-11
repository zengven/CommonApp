package com.yexin.commonlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * author: zengven
 * date: 2019/1/28 14:53
 * desc: TODO
 */

public abstract class BaseTitleActivity extends BaseActivity implements View.OnClickListener {

    protected TextView mTvTitle;
    protected ImageView mIvBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
//        LinearLayout parentLayout = new LinearLayout(getApplicationContext());
//        parentLayout.setOrientation(LinearLayout.VERTICAL);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        View titleView = getLayoutInflater().inflate(R.layout.view_common_title, null);
//        parentLayout.addView(titleView, layoutParams);
//        View contentView = getLayoutInflater().inflate(layoutResID, null);
//        parentLayout.addView(contentView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        super.setContentView(parentLayout);
//        initTitle(titleView);
    }

    protected void initTitle(View titleView) {
//        mIvBack = titleView.findViewById(R.id.iv_back);
//        mTvTitle = titleView.findViewById(R.id.tv_title);
//        mIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int vId = v.getId();
        if (vId == mIvBack.getId()) {
            finishAfterTransition();
        }
    }
}
