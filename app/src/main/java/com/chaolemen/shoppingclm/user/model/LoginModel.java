package com.chaolemen.shoppingclm.user.model;

import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.shoppingclm.app.Contents;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.chaolemen.shoppingclm.user.bean.LoginBean;
import com.chaolemen.shoppingclm.user.contract.LoginContract;
import com.chaolemen.shoppingclm.user.parmesan.LoginParmesan;
import com.chaolemen.shoppingclm.utils.SpUtil;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.HashMap;

public class LoginModel implements LoginContract.Model {
    @Override
    public void getDataLogin(LoginParmesan loginParmesan, final LoginContract.LoginCallBack loginCallBack, LifecycleProvider lifecycleProvider) {

        String toJson = JsonUtils.classToJson(loginParmesan);
        HashMap<String, Object> map = new HashMap<>();
        String param = (String) SpUtil.getParam(Contents.SP_TOKEN, "");
        map.put("token",param);
        new HttpClient.Builder()
                .setApiUrl("userCenter/login")
                .post()
                .setJsonBody(toJson, true)
                .setLifecycleProvider(lifecycleProvider)
//                .setHeadres(map)
                .build()
                .request(new CGHttpCallBack<LoginBean>() {
                    @Override
                    public void onError(String message, int code) {
                        loginCallBack.onFail(message + code);
                    }

                    @Override
                    public void cancle() {
                        loginCallBack.onCancal();
                    }

                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        loginCallBack.onSuccess(loginBean);
                    }

                    @Override
                    public LoginBean convert(JsonElement result) {
                        return JsonUtils.jsonToClass(result, LoginBean.class);
                    }
                });
    }
}
