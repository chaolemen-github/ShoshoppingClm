package com.chaolemen.shoppingclm.cart.adapter;

import android.content.Context;

import com.chaolemen.mvplibrary.adapter.BaseAdapter;
import com.chaolemen.mvplibrary.adapter.BaseViewHolder;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.cart.bean.CartGoods;

import java.util.List;

public class SettleAdapter extends BaseAdapter<CartGoods> {

    public SettleAdapter(List<CartGoods> datas, Context context, int itemlayoutId) {
        super(datas, context, itemlayoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, CartGoods cartGoods) {

        holder.setImageViewContent(R.id.iv_settle_item_img,cartGoods.getGoodsIcon());
        holder.setTextViewContent(R.id.tv_settle_item_title,cartGoods.getGoodsDesc());
        holder.setTextViewContent(R.id.tv_settle_item_text,cartGoods.getGoodsSku());
        holder.setTextViewContent(R.id.tv_settle_item_money,cartGoods.getGoodsPrice());
        holder.setTextViewContent(R.id.tv_settle_item_count,"x"+cartGoods.getGoodsCount());

    }
}
