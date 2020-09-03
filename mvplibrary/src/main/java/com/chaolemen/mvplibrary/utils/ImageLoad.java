package com.chaolemen.mvplibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * 项目名：AndroidAptDemo
 * 包名：  com.liangxq.mymvpone.adapter
 * 文件名：ImageLoad
 * 创建者：liangxq
 * 创建时间：2020/3/18  10:00
 * 描述：TODO
 */
public class ImageLoad {
    private Context context;
    public ImageLoad(Context context) {
        this.context = context;
    }
    public void loadImage(ImageView imageView,String path){
        Glide.with(context).load(path).into(imageView);
    }
}
