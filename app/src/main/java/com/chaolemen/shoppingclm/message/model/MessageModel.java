package com.chaolemen.shoppingclm.message.model;

import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.shoppingclm.message.bean.MessageBean;
import com.chaolemen.shoppingclm.message.contract.MessageContract;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;
import java.util.List;

public class MessageModel implements MessageContract.Model {

    @Override
    public void getDataMsg(String token, final MessageContract.MsgCallBack msgCallBack, LifecycleProvider lifecycleProvider) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", token);

        new HttpClient.Builder()
                .setApiUrl("msg/getList")
                .post()
                .setHeadres(hashMap)
                .build()
                .request(new CGHttpCallBack<List<MessageBean>>() {
                    @Override
                    public void onError(String message, int code) {
                        msgCallBack.onFail(message, code);
                    }

                    @Override
                    public void cancle() {
                        msgCallBack.onCancel();
                    }

                    @Override
                    public void onSuccess(List<MessageBean> messageBeans) {
                        msgCallBack.onSuccess(messageBeans);
                    }

                    @Override
                    public List<MessageBean> convert(JsonElement result) {
                        return JsonUtils.jsonToClassList(result, MessageBean.class);
                    }
                });
    }
}
