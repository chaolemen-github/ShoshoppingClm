package com.chaolemen.httplibrary.disposable;

import io.reactivex.disposables.Disposable;

public interface RequestManager {
    /**
     * @param tag        订阅关系的标识
     * @param disposable 订阅关系
     */
    void add(String tag, Disposable disposable);

    //移除订阅关系
    void remove(String tag);

    //取消订阅
    void cancle(String tag);

    //取消所有的订阅
    void cancleAll();
}
