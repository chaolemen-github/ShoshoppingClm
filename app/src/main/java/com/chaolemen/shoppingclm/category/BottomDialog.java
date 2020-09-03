package com.chaolemen.shoppingclm.category;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.app.BaseApp;
import com.chaolemen.shoppingclm.category.bean.CategoryDitail;
import com.chaolemen.shoppingclm.category.bean.DBBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ren.qinc.numberbutton.NumberButton;

 //低部弹出Dialog,动态添加控件到ViewGroup
public class BottomDialog extends BottomSheetDialog implements View.OnClickListener {
    List<Map<TagFlowLayout, List<String>>> skuStringList = new ArrayList<>();
    public NumberButton numberButton;
     private DBBean dbBean;
     CategoryDitail categoryDitails;

    public BottomDialog(final Context context, final CategoryDitail categoryDitail) {
        super(context);
        List<CategoryDitail.GoodsSkuBean> goodsSkuBeans = categoryDitail.getGoodsSku();
        categoryDitails = categoryDitail;
        setContentView(R.layout.layout_sku_pop);
        findViewById(R.id.mCloseIv).setOnClickListener(this);
        ImageView imageView = findViewById(R.id.mGoodsIconIv);
        Glide.with(context).load(categoryDitail.getGoodsDefaultIcon()).into(imageView);
        numberButton = findViewById(R.id.mSkuCountBtn);
        //数量默认选中1
        numberButton.setCurrentNumber(1);

        EditText editText = numberButton.findViewById(R.id.text_count);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EventBus.getDefault().postSticky(categoryDitail);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //父控件
        ViewGroup viewGroup = findViewById(R.id.mSkuView);
        View inflate = null;
        for (CategoryDitail.GoodsSkuBean goodsSkuBean : goodsSkuBeans) {
            Map<TagFlowLayout, List<String>> skuStringMap = new ArrayMap<>();
            //初始化一组数据
            inflate = LayoutInflater.from(BaseApp.getApp()).inflate(R.layout.layout_sku_view, null, false);
            TagFlowLayout tagFlowLayout = inflate.findViewById(R.id.mSkuContentView);
            TextView textView = inflate.findViewById(R.id.mSkuTitleTv);
            textView.setText(goodsSkuBean.getGoodsSkuTitle());
            List<String> skuContent = goodsSkuBean.getSkuContent();
            tagFlowLayout.setAdapter(new TagAdapter<String>(skuContent) {
                @Override
                public View getView(com.zhy.view.flowlayout.FlowLayout parent, int position, String s) {
                    TextView textView1 = (TextView) LayoutInflater.from(context)
                            .inflate(R.layout.layout_sku_item, parent, false);
                    textView1.setText(s);
                    return textView1;
                }
            });

            //默认选中
            tagFlowLayout.getAdapter().setSelectedList(0);
            tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    EventBus.getDefault().post(categoryDitail);
                    return true;
                }
            });

            skuStringMap.put(tagFlowLayout, skuContent);
            skuStringList.add(skuStringMap);
            //将一组数据添加到父控件当中
            viewGroup.addView(inflate);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mCloseIv:
                dismiss();
                break;
            case R.id.mAddCartBtn:
                EventBus.getDefault().postSticky(categoryDitails);
                dismiss();
                break;
        }
    }

    public List<String> getSkuEvent() {
        List<String> skuCount = new ArrayList<>();
        for (Map<TagFlowLayout, List<String>> listMap : skuStringList) {
            Set<Map.Entry<TagFlowLayout, List<String>>> entrySet = listMap.entrySet();
            for (Map.Entry<TagFlowLayout, List<String>> entry : entrySet) {
                TagFlowLayout tagFlowLayout = entry.getKey();
                List<String> value = entry.getValue();
                skuCount.add(value.get(tagFlowLayout.getSelectedList().iterator().next()));
            }
        }
        return skuCount;
    }
}
