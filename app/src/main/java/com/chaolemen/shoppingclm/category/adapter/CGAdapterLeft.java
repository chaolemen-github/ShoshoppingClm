package com.chaolemen.shoppingclm.category.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.bean.CategoryDemo;

import java.util.List;

public class CGAdapterLeft extends RecyclerView.Adapter<CGAdapterLeft.ViewHolder> {

    Context context;
    List<CategoryDemo> list;

    public CGAdapterLeft(Context context, List<CategoryDemo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_category_rl_left, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CategoryDemo demo = list.get(position);
        holder.text.setText(demo.getCategoryName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemCategoryListener != null) {
                    onClickItemCategoryListener.onClick(position, demo.getId());
                }
            }
        });

        //        如果下标和传回来的下标相等 那么确定是点击的条目 把背景设置一下颜色
        if (position==getmPosition()){
            holder.itemView.setBackgroundResource(R.color.colorGray);
        }else {
            //            否则的话就全白色初始化背景
            holder.itemView.setBackgroundResource(R.color.common_white);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_rl_left_text);
        }
    }

    //背景色点击
    private  int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    OnClickItemCategoryListener onClickItemCategoryListener;

    public void setOnClickItemCategoryListener(OnClickItemCategoryListener onClickItemCategoryListener) {
        this.onClickItemCategoryListener = onClickItemCategoryListener;
    }

    public interface OnClickItemCategoryListener {
        void onClick(int position, int id);
    }
}
