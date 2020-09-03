package com.chaolemen.shoppingclm.category.model;

import android.util.Log;

import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.model.BaseModel;
import com.chaolemen.shoppingclm.category.bean.CategoryDemo;
import com.chaolemen.shoppingclm.category.bean.CategoryList;
import com.chaolemen.shoppingclm.category.parmesan.CategoryPramse;
import com.chaolemen.shoppingclm.category.contract.CategoryContract;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;

public class CategoryModel implements BaseModel, CategoryContract.Model {

    @Override
    public void getData(int parentId, final CategoryContract.CallBack callBack, LifecycleProvider lifecycle) {
        Log.e("liangxq", "getData: 进来了！！");
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        new HttpClient.Builder()
                .post()
                .setApiUrl("category/getCategory")
//                .setHeadres(headerMap)
                .setLifecycleProvider(lifecycle)
                .setJsonBody("{\"parentId\":\"0\"}", true)
                .build()
                .request(new CGHttpCallBack<List<CategoryDemo>>() {
                    @Override
                    public void onError(String message, int code) {
                        LogUtils.e(code + message);
                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    public void onSuccess(List<CategoryDemo> categoryDemos) {
                        callBack.onSuccess(categoryDemos);
                    }

                    @Override
                    public List<CategoryDemo> convert(JsonElement result) {
                        return JsonUtils.jsonToClassList(result, CategoryDemo.class);
                    }
                });
    }

    @Override
    public void getDataComputer(int parentId, final CategoryContract.CallBack callBack, LifecycleProvider lifecycle) {
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        CategoryPramse pramse = new CategoryPramse();
        pramse.setParentId(parentId);
        String toJson = JsonUtils.classToJson(pramse);
        new HttpClient.Builder()
                .post()
                .setApiUrl("category/getCategory")
                .setHeadres(headerMap)
                .setLifecycleProvider(lifecycle)
                .setJsonBody(toJson, true)
                .build()
                .request(new CGHttpCallBack<List<CategoryList>>() {
                    @Override
                    public void onError(String message, int code) {
                        LogUtils.e(code + message);
                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    public void onSuccess(List<CategoryList> categoryDemos) {
                        callBack.onSuccessComputer(categoryDemos);
                    }

                    @Override
                    public List<CategoryList> convert(JsonElement result) {
                        return JsonUtils.jsonToClassList(result, CategoryList.class);
                    }
                });
    }
}
