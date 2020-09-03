package com.chaolemen.shoppingclm.user.interceptor;

import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.shoppingclm.app.Contents;
import com.chaolemen.shoppingclm.utils.SpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        List<String> headers = response.headers("Set-Cookie");
        LogUtils.e("111111111"+headers);

        if (headers.size()!=0){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < headers.size(); i++) {
                String s = headers.get(i);
                String substring = s.substring(0, s.indexOf(";"));
                stringBuilder.append(substring);
                if (!(i==headers.size()-1)){
                    stringBuilder.append(";");
                }
            }

            SpUtil.setParam(Contents.SP_COOKIE,stringBuilder.toString());
        }

//        if (headers!=null){
//            //将cookie保存到SP内存中
//            SpUtil.setParam("cookie",headers);
//        }
        return response;
    }
}
