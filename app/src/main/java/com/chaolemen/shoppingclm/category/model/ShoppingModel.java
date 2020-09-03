package com.chaolemen.shoppingclm.category.model;

import android.util.Log;

import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.shoppingclm.cart.bean.CartGoods;
import com.chaolemen.shoppingclm.category.contract.ShoppingContract;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.chaolemen.shoppingclm.utils.SpUtil;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;

public class ShoppingModel implements ShoppingContract.Model {
    @Override
    public void getDataShopping(final ShoppingContract.ShoppingCallBack shoppingCallBack, LifecycleProvider lifecycleProvider) {

        HashMap<String, Object> tokenmap = new HashMap<>();
        Integer token = (Integer) SpUtil.getParam("token", 0);
        tokenmap.put("token", token);
        new HttpClient.Builder()
                .setJsonBody("{}",true)
                .setHeadres(tokenmap)
                .setApiUrl("cart/getList")
                .post().build().request(new CGHttpCallBack<List<CartGoods>>() {
            @Override
            public void onError(String message, int code) {
                Log.e("liangxq", "onError: "+message );
                shoppingCallBack.onFail(message+code);
            }

            @Override
            public void cancle() {

            }

            @Override
            public void onSuccess(List<CartGoods> goods) {
                shoppingCallBack.onSuccess(goods);
            }

            @Override
            public List<CartGoods> convert(JsonElement result) {
                return JsonUtils.jsonToClassList(result,CartGoods.class);
            }
        });
    }
}
