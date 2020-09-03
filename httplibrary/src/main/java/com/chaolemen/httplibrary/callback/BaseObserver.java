package com.chaolemen.httplibrary.callback;

import android.text.TextUtils;
import android.util.Log;


import com.chaolemen.httplibrary.HttpGlobalConfig;
import com.chaolemen.httplibrary.disposable.RequestManagerIml;
import com.chaolemen.httplibrary.exceptiopn.ApiException;
import com.chaolemen.httplibrary.exceptiopn.ExceptionEngine;
import com.chaolemen.httplibrary.utils.ThreadUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver implements Observer{
    String tag;
    @Override
    public void onSubscribe(Disposable d) {
        if(!TextUtils.isEmpty(tag)){
            RequestManagerIml.getInstance().add(tag,d);
        }
    }

    @Override
    public void onNext(Object t) {
        Log.e("原始数据", "onNext:000"+t.toString() );
        if(!TextUtils.isEmpty(tag)){
            RequestManagerIml.getInstance().remove(tag);
        }
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            ApiException apiException= (ApiException) e;
            onError(apiException.getMsg(),apiException.getCode());
        }else{
            onError("未知异常", ExceptionEngine.UN_KNOWN_ERROR);
        }
        if(!TextUtils.isEmpty(tag)){
            RequestManagerIml.getInstance().remove(tag);
        }
    }

    @Override
    public void onComplete() {
        if(!RequestManagerIml.getInstance().isDispose(tag)){
            RequestManagerIml.getInstance().cancle(tag);
        }
    }

    //回调错错误信息
     public abstract void onError(String message, int code);


     public abstract void cancle();


    //网络请求取消
    public void canclend(){
        if(!ThreadUtils.isMainThread()){
            HttpGlobalConfig.getInsance().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    cancle();
                }
            });
        }
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
