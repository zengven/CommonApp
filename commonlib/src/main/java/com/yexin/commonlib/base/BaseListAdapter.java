package com.yexin.commonlib.base;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * author: zengven
 * date: 2017/7/5 17:27
 * desc: base listview adapter
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    private final Object mLock = new Object();

    protected List<T> mList;

    public BaseListAdapter() {
    }

    public void setData(List<T> list) {
        synchronized (mLock) {
            if (list == null || list.isEmpty())
                return;
            mList = list;
        }
        notifyDataSetChanged();
    }

    public void addData(List<T> list) {
        synchronized (mLock) {
            if (list == null || list.isEmpty())
                return;
            if (mList == null) {
                setData(list);
                return;
            }
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
