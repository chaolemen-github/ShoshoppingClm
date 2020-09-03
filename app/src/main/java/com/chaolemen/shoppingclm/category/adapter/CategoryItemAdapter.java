package com.chaolemen.shoppingclm.category.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.ViewHolder> {

    Context context;
    List<CategoryItem> list;

    public CategoryItemAdapter(Context context, List<CategoryItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_category_item, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryItem categoryItem = list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cate_item_img)
        ImageView ivCateItemImg;
        @BindView(R.id.tv_cate_item_text)
        TextView tvCateItemText;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
