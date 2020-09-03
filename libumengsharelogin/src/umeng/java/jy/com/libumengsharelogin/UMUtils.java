package jy.com.libumengsharelogin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.PlatformConfig;

import java.util.List;

public class UMUtils {


    public static void initUmeng(Context context) {

        //你的um  appkey
        UMConfigure.init(context, "5ddb8e7f4ca3574480000d56", "umeng",
                UMConfigure.DEVICE_TYPE_PHONE, "");
        // 下面所有ID 都得替换成公司相应创建应用时获得的id

        //第一个参数表示 AppID ，第二：AppSecret
        PlatformConfig.setWeixin("wx622eab3cdad0b38f", "af572cb1f7ef95b5b58c875146dc2c0f");

        //QQ//第一个参数表示 APP ID ，第二：APP KEY
        // PlatformConfig.setQQZone("", "");
        // 新浪微博 // 第一个参数表示 APP ID ，第二：App Secret，第三： 回调 地址
        // PlatformConfig.setSinaWeibo("","","");
        //是否启动um日志
        UMConfigure.setLogEnabled(false);

        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(context);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                //Log.i("umeng", "注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                //Log.e("umeng", "注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });
    }

    /**
     * 检测是否安装支付宝
     *
     * @param context
     * @return
     */
    public static boolean isAliPayInstalled(Context context) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }

    /**
     * 检测是否安装微信
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0 ; i < pinfo.size() ; i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0 ; i < pinfo.size() ; i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
}
