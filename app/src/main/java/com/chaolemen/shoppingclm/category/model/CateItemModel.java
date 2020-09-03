package com.chaolemen.shoppingclm.category.model;

import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;
import com.chaolemen.shoppingclm.category.contract.CateItemContract;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.chaolemen.shoppingclm.category.parmesan.ItemParmesan;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class CateItemModel implements CateItemContract.Model {
    @Override
    public void getDataItem(ItemParmesan itemParmesan,
                            final CateItemContract.ItemCallBack itemCallBack, LifecycleProvider lifecycleProvider) {

        //
        String toJson = JsonUtils.classToJson(itemParmesan);
        new HttpClient.Builder()
                .post()
                .setJsonBody(toJson, true)
                .setLifecycleProvider(lifecycleProvider)
                .setApiUrl("goods/getGoodsList")
                .build()
                .request(new CGHttpCallBack<List<CategoryItem>>() {
                    @Override
                    public void onError(String message, int code) {
                        itemCallBack.onFailItem(message+code);
                    }

                    @Override
                    public void cancle() {
                        itemCallBack.onCancal();
                    }

                    @Override
                    public void onSuccess(List<CategoryItem> categoryItems) {
                        itemCallBack.onSuccessItem(categoryItems);
                    }

                    @Override
                    public List<CategoryItem> convert(JsonElement result) {
                        return JsonUtils.jsonToClassList(result, CategoryItem.class);
                    }
                });
    }
}
