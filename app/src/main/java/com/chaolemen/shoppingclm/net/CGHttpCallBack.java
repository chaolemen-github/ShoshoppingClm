package com.chaolemen.shoppingclm.net;

import android.util.Log;

import com.chaolemen.httplibrary.callback.BaseCallBack;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.shoppingclm.category.bean.CategoryRespoens;
import com.chaolemen.shoppingclm.utils.SpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public
abstract
class CGHttpCallBack<T> extends BaseCallBack<T> {
    CategoryRespoens categoryRespoens;

    @Override
    protected T onConvert(String result) {
        T t = null;
        LogUtils.e("callback" + result);
        categoryRespoens = new Gson().fromJson(result, CategoryRespoens.class);
        LogUtils.e("callback1" + categoryRespoens.toString());
        int errorCode = categoryRespoens.getStatus();
        String message = categoryRespoens.getMessage();
        JsonElement data = categoryRespoens.getData();
        switch (errorCode) {
            case -1:
                onError(message, errorCode);
                SpUtil.clear();
                break;
            default:
                if (isCodeSuccess()) {
                    t = convert(data);
                }
                break;
        }
        Log.e("clm", "onConvert: " + t.toString());
        return t;
    }

    @Override
    public boolean isCodeSuccess() {
        if (categoryRespoens != null) {
            return categoryRespoens.getStatus() == 0;
        }
        return false;
    }


}
