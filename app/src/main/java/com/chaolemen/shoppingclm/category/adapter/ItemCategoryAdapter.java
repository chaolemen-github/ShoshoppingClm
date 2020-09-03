package com.chaolemen.shoppingclm.category.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.app.BaseApp;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;

import java.util.List;

public class ItemCategoryAdapter extends BaseQuickAdapter<CategoryItem, BaseViewHolder> {
    public ItemCategoryAdapter(int layoutResId, @Nullable List<CategoryItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CategoryItem item) {
        ImageView img = helper.getView(R.id.iv_cate_item_img);
        TextView desc = helper.getView(R.id.tv_cate_item_text);
        TextView money = helper.getView(R.id.tv_cate_item_money);
        TextView sales = helper.getView(R.id.tv_cate_item_Sales);
        TextView stock = helper.getView(R.id.tv_cate_item_Stock);


        Glide.with(BaseApp.getApp()).load(item.getGoodsDefaultIcon()).into(img);
        desc.setText(item.getGoodsDesc());
        money.setText("￥"+item.getGoodsDefaultPrice());
        sales.setText("销量"+item.getGoodsSalesCount());
        stock.setText("库存"+item.getGoodsStockCount());
    }
}
