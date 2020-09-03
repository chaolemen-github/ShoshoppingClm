package com.chaolemen.shoppingclm.app;

import android.app.Application;
import android.content.Context;

import com.chaolemen.httplibrary.HttpGlobalConfig;
import com.chaolemen.shoppingclm.user.interceptor.CookieInterceptor;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cn.jpush.android.api.JPushInterface;
import okhttp3.Interceptor;

public class BaseApp extends Application {

    private static BaseApp app;


    public static BaseApp getApp() {
        return app;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
//        MultiDex.install(this);
        app = this;
//        initBugly();//收集线上Bug
//        initLeakCanary();//初始化内存泄露检测工具
        initCategory();
//        initUm();//初始化友盟
    }

    private void initCategory() {
        /**
         * 添加拦截器
         * 获取请求头里的cookie
         */
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new CookieInterceptor());

        HttpGlobalConfig.getInsance()
                .setBaseUrl("http://169.254.189.205:8080/kotlin/")
                .setTimeout(10)
                .setAppKey(Contents.JPUSHREGISTID,JPushInterface.getRegistrationID(this))
                .setInterceptors(interceptors)
                .setTimeUnit(TimeUnit.SECONDS)
                .setShowLog(true)
                .initReady(this);
    }

//    //初始化友盟
//    private void initUm() {
//        UMConfigure.init(this
//                , "5f2c02d8d30932215475ad2a"
//                , "UMCon"
//                , UMConfigure.DEVICE_TYPE_PHONE
//                , "");
//
//        UMConfigure.setLogEnabled(true);
//    }

    //初始化内存泄露检测工具
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for
            // heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    //收集线上Bug
    private void initBugly() {
//        CrashReport.initCrashReport(getApplicationContext(), "14fdd7625c", true);
        // CrashReport.testJavaCrash(); //测试
    }
}
