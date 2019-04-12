package com.yexin.commonlib.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * author: zengven
 * date: 2017/6/16 15:14
 * desc: 轮播图 适配器基类
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected List<T> mList;
    private OnItemClickListener mOnItemClickListener;

    public void setData(List<T> list) {
        if (list == null) {
            return;
        }
        mList = list;
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (mList == null) {
            setData(list);
            return;
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return mList;
    }

    public void clear() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    protected T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return null != mList ? mList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final T t = getItem(position);
        View view = getView(container.getContext(), t, position);
        if (mOnItemClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(t, position);
                }
            });
        }
        container.addView(view);
        return view;
    }

    public abstract View getView(Context context, T t, int position);


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(T t, int postion);
    }
}