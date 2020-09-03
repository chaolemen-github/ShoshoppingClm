package com.chaolemen.mvplibrary.adapter;

/**
 * 项目名：AndroidAptDemo
 * 包名：  com.liangxq.mymvpone.adapter
 * 文件名：CommonType
 * 创建者：liangxq
 * 创建时间：2020/3/18  15:16
 * 描述：TODO
 */
public interface CommonType<DATA> {
    //根据不同的类型返回对应的布局文件
    int getTypeLayoutId(int viewType);
    //返回类型
    int getType(int positon, DATA data);
}
