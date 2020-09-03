package com.chaolemen.mvplibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaolemen.mvplibrary.utils.ImageLoad;


/**
 * 项目名：AndroidAptDemo
 * 包名：  com.liangxq.mymvpone.adapter
 * 文件名：BaseViewHolder
 * 创建者：liangxq
 * 创建时间：2020/3/18  9:09
 * 描述：TODO
 */
public class BaseViewHolder extends RecyclerView.ViewHolder{
    private View itemRootView;
    private Context context;
    private SparseArray sparseArray=new SparseArray<>();
    public BaseViewHolder(View itemView,Context context) {
        super(itemView);
        itemRootView=itemView;
        this.context=context;
    }

    public <T extends View> T getView(int viewId){
        if(sparseArray.get(viewId)==null){
            View viewById = itemRootView.findViewById(viewId);
            sparseArray.put(viewId,viewById);
        }
        return (T) sparseArray.get(viewId);
    }


    public BaseViewHolder setTextViewContent(int textViewId, final String text, final OnItemClickListener onItemClickListener){
        TextView textView=getView(textViewId);
        if(onItemClickListener!=null){
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(text);
                }
            });
        }
        textView.setText(text);
        return this;
    }

    public BaseViewHolder setTextViewContent(int textViewId, final String text ){
        TextView textView=getView(textViewId);
        textView.setText(text);
        return this;
    }
    public BaseViewHolder setImageViewContent(int textViewId, final String path, final OnItemClickListener onItemClickListener){
        ImageView imageView=getView(textViewId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(path);
            }
        });
        ImageLoad imageLoad=new ImageLoad(context);
        imageLoad.loadImage(imageView,path);
        return this;
    }

    public BaseViewHolder setImageViewContent(int textViewId, final String path){
        ImageView imageView=getView(textViewId);
        ImageLoad imageLoad=new ImageLoad(context);
        imageLoad.loadImage(imageView,path);
        return this;
    }


    interface OnItemClickListener{
        void onItemClick(String itemString);
    }
}
