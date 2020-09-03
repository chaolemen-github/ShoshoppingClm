package com.chaolemen.shoppingclm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;


import java.io.File;

import retrofit2.http.Url;

/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.utils
 * 文件名：CameraUtils
 * 创建者：liangxq
 * 创建时间：2020/8/28  15:30
 * 描述：TODO
 */
public class CameraUtils {

    public static File goCamera(Activity context) {
       File cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri=null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //第二个参数为 包名.fileprovider
           uri = FileProvider.getUriForFile(context, "com.chaolemen.shoppingclm.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        context.startActivityForResult(intent, 1);

        return cameraSavePath;
    }
}
