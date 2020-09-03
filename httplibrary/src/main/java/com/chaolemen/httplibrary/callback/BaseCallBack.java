package com.chaolemen.httplibrary.callback;

import android.util.Log;

import com.chaolemen.httplibrary.exceptiopn.ExceptionEngine;
import com.google.gson.JsonElement;

import java.util.Observable;

/**
 * 第一步将JsonElement解析成Response<>,第二不解析出我们需要的泛型数据
 */
public abstract class BaseCallBack<T> extends BaseObserver {
    //解析成功的标志
    boolean callSuccess=true;
    @Override
    public void onNext(Object t) {
        super.onNext(t);
        Log.e("原始数据", "onNext:111"+t.toString() );
        //返回的是个json串
        T parse = parse((String) t);
//        Log.e("底层解析", "onNext:222"+parse.toString() );
        if(callSuccess&&isCodeSuccess()){
            onSuccess(parse);
        }
    }
    //将JsonElement转换为Response，并且通过子类的实现来获取data数据
    protected abstract T onConvert(String result);
    //
    public T parse(String result){
        T data=null;
        try{
            data=onConvert(result);
            callSuccess=true;
        }catch (Exception e){
            e.printStackTrace();
            callSuccess=false;
            onError("解析数据错误", ExceptionEngine.ANALYTIC_SERVER_DATA_ERROR);
        }
        return data;
    }
    //数据返回状态成功
    public abstract boolean isCodeSuccess();
    //返回获取的泛型数据
    public abstract void onSuccess (T t);
    //将我们需要的数据解析出来
    public abstract T convert(JsonElement result);

}
