package com.wapchief.imitationlol;

import android.content.Context;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/6/21.
 */

public class FragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MainBean> data=new ArrayList<MainBean>();

    public FragmentAdapter(List<MainBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    private Context context;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fragment, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
//                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tvTitle,tvContent,tvTime;
        public ItemViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.item_img);
            tvTitle = (TextView) view.findViewById(R.id.item_title);
            tvContent = (TextView) view.findViewById(R.id.item_content);
            tvTime = (TextView) view.findViewById(R.id.item_time);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
