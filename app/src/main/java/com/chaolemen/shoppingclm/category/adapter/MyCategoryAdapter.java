package com.chaolemen.shoppingclm.category.adapter;



import android.content.Context;

import com.chaolemen.mvplibrary.adapter.BaseAdapter;
import com.chaolemen.mvplibrary.adapter.BaseViewHolder;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;

import java.util.List;

/**
 * 详情页面的适配器
 */
public class MyCategoryAdapter extends BaseAdapter<CategoryItem> {

    public MyCategoryAdapter(List<CategoryItem> datas, Context context, int itemlayoutId) {
        super(datas, context, itemlayoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, CategoryItem categoryItem) {
        holder.setImageViewContent(R.id.iv_cate_item_img,categoryItem.getGoodsDefaultIcon());
        holder.setTextViewContent(R.id.tv_cate_item_text,categoryItem.getGoodsDesc());
        holder.setTextViewContent(R.id.tv_cate_item_money,"￥"+categoryItem.getGoodsDefaultPrice());
        holder.setTextViewContent(R.id.tv_cate_item_Sales,"销量"+categoryItem.getGoodsSalesCount());
        holder.setTextViewContent(R.id.tv_cate_item_Stock,"库存"+categoryItem.getGoodsStockCount());
    }
}
