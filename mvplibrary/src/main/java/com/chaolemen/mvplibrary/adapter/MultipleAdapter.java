package com.chaolemen.mvplibrary.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * 项目名：AndroidAptDemo
 * 包名：  com.liangxq.mymvpone.adapter
 * 文件名：MultipleAdapter
 * 创建者：liangxq
 * 创建时间：2020/3/18  15:00
 * 描述：TODO
 */
public abstract class MultipleAdapter<DATA> extends BaseAdapter<DATA>{
    private CommonType<DATA> commonType;
    public MultipleAdapter(List<DATA> datas, Context context, int itemlayoutId,CommonType commonType) {
        super(datas, context, itemlayoutId);
        if(itemlayoutId==0){
            this.commonType=commonType;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return commonType.getType(position, datas.get(position));
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int typeLayoutId = commonType.getTypeLayoutId(viewType);
        return new BaseViewHolder(layoutInflater.inflate(typeLayoutId,parent,false),context);
    }
}
