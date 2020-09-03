package com.chaolemen.shoppingclm.cart.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import com.chaolemen.mvplibrary.adapter.BaseAdapter;
import com.chaolemen.mvplibrary.adapter.BaseViewHolder;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.cart.bean.CartGoods;
import com.chaolemen.shoppingclm.category.bean.UpDateTotalPrice;
import com.chaolemen.shoppingclm.fragments.CartFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ren.qinc.numberbutton.NumberButton;


public class CartAdapter extends BaseAdapter<CartGoods> {

    public CartAdapter(List<CartGoods> datas, Context context) {
        super(datas, context, R.layout.cart_item);
    }

    @Override
    protected void bindData(BaseViewHolder holder, final CartGoods cartGoods) {
        String goodsDesc = cartGoods.getGoodsDesc();
        holder.setImageViewContent(R.id.mGoodsIconIv,cartGoods.getGoodsIcon());
        holder.setTextViewContent(R.id.mGoodsDescTv,goodsDesc);
        holder.setTextViewContent(R.id.mGoodsSkuTv,cartGoods.getGoodsSku());
        holder.setTextViewContent(R.id.mGoodsPriceTv,cartGoods.getGoodsPrice());
        NumberButton numberButton = holder.getView(R.id.mGoodsCountBtn);
        CheckBox checkBox = holder.getView(R.id.mCheckedCb);

        //全选
        if(cartGoods.isCheck()){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

        //单选
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
               if(isCheck){
                   cartGoods.setCheck(true);
               }else{
                   cartGoods.setCheck(false);
               }
               if(!CartFragment.isCheck){
                   EventBus.getDefault().postSticky(new UpDateTotalPrice());
               }
            }
        });

        //输入框
        EditText numberEditText = numberButton.findViewById(R.id.text_count);
        if(numberEditText.getTag() instanceof TextWatcher){
            numberEditText.removeTextChangedListener((TextWatcher) numberEditText.getTag());
        }
        numberEditText.setText(cartGoods.getGoodsCount()+"");
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cartGoods.setGoodsCount(Integer.parseInt(s.toString()));
                if(!CartFragment.isCheck){
                    EventBus.getDefault().postSticky(new UpDateTotalPrice());
                }
            }
        };
        numberEditText.addTextChangedListener(watcher);
        numberEditText.setTag(watcher);
    }
}
