package com.chaolemen.shoppingclm.category.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.bean.CategoryList;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_0 = 0;
    private static final int TYPE_1 = 1;

    Context context;
    List<CategoryList> list;

    public CategoryListAdapter(Context context, List<CategoryList> list) {
        this.context = context;
        this.list = list;
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_category_rl_right, parent, false);
//        return new ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//
//        final CategoryList bean = list.get(position);
//        holder.title.setText(bean.getCategoryName());
//        Glide.with(context).load(bean.getCategoryIcon()).into(holder.img);
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onClickCategoryItemListener!=null){
//                    onClickCategoryItemListener.onClick(bean.getId());
//                }
//            }
//        });
//    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        switch (viewType) {
//            case TYPE_0:
//                View inflate = LayoutInflater.from(context).inflate(R.layout.fragment_category_rl_right0, parent, false);
//                return new ViewHolder0(inflate);
//            default:
                View inflate1 = LayoutInflater.from(context).inflate(R.layout.fragment_category_rl_right, parent, false);
                return new ViewHolder1(inflate1);
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

//        int viewType = getItemViewType(position);
//        switch (viewType) {
//            case TYPE_0:
//
//                break;
//            default:
                ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                final CategoryList bean = list.get(position);
                viewHolder1.title.setText(bean.getCategoryName());
                Glide.with(context).load(bean.getCategoryIcon()).into(viewHolder1.img);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onClickCategoryItemListener != null) {
                            onClickCategoryItemListener.onClick(bean.getParentId(),bean.getId(),position);
                        }
                    }
                });
//                break;
//        }
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position==0){
//            return TYPE_0;
//        } else {
//            return TYPE_1;
//        }
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {
        public ViewHolder0(View itemView) {
            super(itemView);
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public ViewHolder1(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_rl_img);
            title = itemView.findViewById(R.id.tv_rl_title);
        }
    }

    OnClickCategoryItemListener onClickCategoryItemListener;

    public void setOnClickCategoryItemListener(OnClickCategoryItemListener onClickCategoryItemListener) {
        this.onClickCategoryItemListener = onClickCategoryItemListener;
    }

    public interface OnClickCategoryItemListener {
        void onClick(int parentId,int id,int position);
    }
}
