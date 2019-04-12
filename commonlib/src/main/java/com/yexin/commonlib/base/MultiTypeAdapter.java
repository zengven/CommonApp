package com.yexin.commonlib.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author yuhushuan@le.com
 * @ClassName: MultiTypeAdapter
 * @Description: 多类型适配器
 * @date 2017/3/17 20:34
 */
public abstract class MultiTypeAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> mList;

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

    public void clear() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 删除
     *
     * @param position
     */
    public void removeItem(int position) {
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        mList.remove(position);
    }

    /**
     * 删除
     *
     * @param position
     */
    public void removeItemData(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mList == null || mList.isEmpty();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(getItem(position));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(viewType, parent, false);
        return createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        final T t = getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(v, position, t);
                }
            }
        });
        holder.onBind(t);
    }

    protected abstract BaseViewHolder createViewHolder(int viewType, View itemView);

    /**
     * @param t
     * @return 必须是布局文件的layout id
     */
    public abstract int getViewType(T t);


    public OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T t);
    }

}
