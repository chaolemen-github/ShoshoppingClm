package com.chaolemen.shoppingclm.user;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.base.BaseActivity;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.utils.CameraUtils;
import com.chaolemen.shoppingclm.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalActivity extends BaseActivity {
    private static final int REQUEST_PERMISSION_CODE = 101;
    private static final int REQUEST_TAKE_PHOTO_CODE = 202;
    @BindView(R.id.tv_personal_title)
    TextView mTvPersonalTitle;
    @BindView(R.id.iv_personal_img)
    CircleImageView mIvPersonalImg;
    @BindView(R.id.iv_personal_)
    ImageView mIvPersonal;
    @BindView(R.id.constraint_personal)
    ConstraintLayout mConstraintPersonal;
    private int maxSelectNum = 9;

    /**
     * Permission check result: this is returned by {@link #checkPermission}
     * if the permission has been granted to the given package.
     */
    public static final int PERMISSION_GRANTED = 0;//授权成功

    /**
     * Permission check result: this is returned by {@link #checkPermission}
     * if the permission has not been granted to the given package.
     */
    public static final int PERMISSION_DENIED = -1;//拒绝授权
    private Uri mUri;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_personal);
//        ButterKnife.bind(this);
//        Toast.makeText(this, "个人信息", Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.constraint_personal)
    public void onViewClicked() {
        new AlertView("上传头像", null, "取消", null,
                new String[]{"拍照", "从相册中选择"},
                this, AlertView.Style.ActionSheet, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                switch (position){
                    case 0:
//                        CameraUtils.goCamera(PersonalActivity.this);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//大于Android 6.0
                            if (!checkPermission()) { //没有或没有全部授权
                                requestPermissions(); //请求权限
                            }
                        } else {
                            takePhoto();//拍照逻辑
                        }
                        takePhoto();//拍照逻辑
                        break;
                    case 1:
                        //相册
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 2);
                        break;
                }
            }
        }).show();
    }

    //检查权限
    private boolean checkPermission() {
        //是否有权限
        boolean haveCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        boolean haveWritePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return haveCameraPermission && haveWritePermission;

    }

    // 请求所需权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions() {
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }

    // 请求权限后会在这里回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:

                boolean allowAllPermission = false;

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//被拒绝授权
                        allowAllPermission = false;
                        break;
                    }
                    allowAllPermission = true;
                }

                if (allowAllPermission) {
                    takePhotoOrPickPhoto();//开始拍照或从相册选取照片
                } else {
                    Toast.makeText(this, "该功能需要授权方可使用", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void takePhotoOrPickPhoto() {

    }

    private void takePhoto() {
        // 步骤一：创建存储照片的文件
        String path = getFilesDir() + File.separator + "images" + File.separator;
        File file = new File(path, "test.jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //步骤二：Android 7.0及以上获取文件 Uri
            mUri = FileProvider.getUriForFile(this, "com.example.admin.custmerviewapplication", file);
        } else {
            //步骤三：获取文件Uri
            mUri = Uri.fromFile(file);
        }
        //步骤四：调取系统拍照
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO_CODE);
    }

    private static final String TAG = "chaolm";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Log.e("chaolm", "onActivityResult: "+data.getData() );
            Uri data1 = data.getData();
            String pathName = FileUtils.getPath(this, data1);

            //width: 263,ImageView实际比较小
            int width = mIvPersonalImg.getWidth();
            int height = mIvPersonalImg.getHeight();
            Log.e(TAG, "imageView width: "+width);

            //不采样时图片的大小:9216000
            Bitmap bitmap = BitmapFactory.decodeFile(pathName);
            Log.e(TAG, "不二次采样bitmap大小: "+bitmap.getAllocationByteCount());
            BitmapFactory.Options options = new BitmapFactory.Options();
            //设置为true,加载图片时不会获取到bitmap对象,但是可以拿到图片的宽高
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pathName,options);
            //计算采样率,对图片进行相应的缩放
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            Log.e(TAG, "outWidth: "+outWidth+",outHeight:"+outHeight);
            float widthRatio = outWidth*1.0f/width;
            float heightRatio = outHeight*1.0f/height;
            Log.e(TAG, "widthRatio: "+widthRatio+",heightRatio:"+heightRatio);
            float max = Math.max(widthRatio, heightRatio);
            //向上舍入
            int inSampleSize = (int) Math.ceil(max);
            Log.e(TAG, "inSampleSize: "+inSampleSize);
            //改为false,因为要获取采样后的图片了
            options.inJustDecodeBounds = false;
            //8
            options.inSampleSize = inSampleSize;
            Bitmap bitmap1 = BitmapFactory.decodeFile(pathName, options);
            //采样后图片大小:144000,是采样前图片的inSampleSize*inSampleSize分之一(1/64)
            Log.e(TAG, "二次采样bitmap大小: "+bitmap1.getAllocationByteCount());
            mIvPersonalImg.setImageBitmap(bitmap1);

        } else if (requestCode == 202&& resultCode==RESULT_OK){
            Uri data1 = data.getData();
            String pathName = FileUtils.getPath(this, data1);
            LogUtils.e("相机+++"+pathName);
            //width: 263,ImageView实际比较小
            int width = mIvPersonalImg.getWidth();
            int height = mIvPersonalImg.getHeight();
            Log.d(TAG, "imageView width: "+width);

            //不采样时图片的大小:9216000
            Bitmap bitmap = BitmapFactory.decodeFile(pathName);
            Log.d(TAG, "不二次采样bitmap大小: "+bitmap.getAllocationByteCount());
            BitmapFactory.Options options = new BitmapFactory.Options();
            //设置为true,加载图片时不会获取到bitmap对象,但是可以拿到图片的宽高
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pathName,options);
            //计算采样率,对图片进行相应的缩放
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            Log.d(TAG, "outWidth: "+outWidth+",outHeight:"+outHeight);
            float widthRatio = outWidth*1.0f/width;
            float heightRatio = outHeight*1.0f/height;
            Log.d(TAG, "widthRatio: "+widthRatio+",heightRatio:"+heightRatio);
            float max = Math.max(widthRatio, heightRatio);
            //向上舍入
            int inSampleSize = (int) Math.ceil(max);
            Log.d(TAG, "inSampleSize: "+inSampleSize);
            //改为false,因为要获取采样后的图片了
            options.inJustDecodeBounds = false;
            //8
            options.inSampleSize = inSampleSize;
            Bitmap bitmap1 = BitmapFactory.decodeFile(pathName, options);
            //采样后图片大小:144000,是采样前图片的inSampleSize*inSampleSize分之一(1/64)
            Log.d(TAG, "二次采样bitmap大小: "+bitmap1.getAllocationByteCount());
            mIvPersonalImg.setImageBitmap(bitmap1);

        }
    }

}
