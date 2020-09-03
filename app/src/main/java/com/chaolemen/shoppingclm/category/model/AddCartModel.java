package com.chaolemen.shoppingclm.category.model;

import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.shoppingclm.category.contract.AddCartContract;
import com.chaolemen.shoppingclm.category.parmesan.AddCartPrams;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.chaolemen.shoppingclm.utils.SpUtil;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;

public class AddCartModel implements AddCartContract.Model {
    @Override
    public void getDataAddCart(AddCartPrams addCartPrams, AddCartContract.AddCartCallBack addCartCallBack, LifecycleProvider lifecycleProvider) {
        String toJson = JsonUtils.classToJson(addCartPrams);
        HashMap<String, Object> heardmap = new HashMap<>();
        Integer param = (Integer) SpUtil.getParam("token", 0);
        heardmap.put("token",param);
        new HttpClient.Builder().setJsonBody(toJson, true)
                .setApiUrl("cart/add")
                .setHeadres(heardmap)
                .post()
                .setLifecycleProvider(lifecycleProvider)
                .build().request(new CGHttpCallBack<Integer>() {
            @Override
            public void onError(String message, int code) {
                addCartCallBack.onFail(message + code);
            }

            @Override
            public void cancle() {
                addCartCallBack.onCancel();
            }

            @Override
            public void onSuccess(Integer integer) {
                addCartCallBack.onSuccessAddCart(String.valueOf(integer));
            }

            @Override
            public Integer convert(JsonElement result) {
                return result.getAsNumber().intValue();
            }
        });
    }
}
