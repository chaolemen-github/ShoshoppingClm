package com.chaolemen.shoppingclm.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.bean.CategoryRespoens;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.chaolemen.shoppingclm.user.parmesan.RegisterParmesan;
import com.chaolemen.shoppingclm.utils.SpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mRegisterToolbar;
    private ImageView mRegisterFinishIv;
    private EditText mRegisterUserEt;
    private TextView mRegisterYanzhengTv;
    private EditText mRegisterVerifyEt;
    private EditText mRegisterPassEt;
    private EditText mRegisterNewPassEt;
    private LinearLayout mRegisterLinear;
    private Button mRegisterBtn;

    boolean isChick;
    private String user;
    private String pass;
    private String newPass;
    private String verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
//        initData();
    }

    private void initView() {
        mRegisterToolbar = (Toolbar) findViewById(R.id.toolbar_register);
        mRegisterFinishIv = (ImageView) findViewById(R.id.iv_register_finish);
        mRegisterFinishIv.setOnClickListener(this);
        mRegisterUserEt = (EditText) findViewById(R.id.et_register_user);
        mRegisterYanzhengTv = (TextView) findViewById(R.id.tv_register_yanzheng);
        mRegisterVerifyEt = (EditText) findViewById(R.id.et_register_verify);
        mRegisterPassEt = (EditText) findViewById(R.id.et_register_pass);
        mRegisterNewPassEt = (EditText) findViewById(R.id.et_register_newPass);
        mRegisterLinear = (LinearLayout) findViewById(R.id.linear_register);
        mRegisterBtn = (Button) findViewById(R.id.btn_register);
        mRegisterBtn.setOnClickListener(this);

        mRegisterToolbar.setTitle("注册");

        TextView childAt = (TextView) mRegisterToolbar.getChildAt(0);
        childAt.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        childAt.setGravity(Gravity.CENTER);


    }

    private void initRegister() {
        user = mRegisterUserEt.getText().toString().trim();
        pass = mRegisterPassEt.getText().toString().trim();
        newPass = mRegisterNewPassEt.getText().toString().trim();
        verify = mRegisterVerifyEt.getText().toString().trim();

        if (!TextUtils.isEmpty(user)) {
            if (!TextUtils.isEmpty(verify)) {
                if (!TextUtils.isEmpty(pass)) {
                    if (!TextUtils.isEmpty(newPass)) {
                        if (pass.equals(newPass)) {

//                            initData();
                            initUpLoad();
                        } else {
                            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "重复不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void initUpLoad() {
        RegisterParmesan registerParmesan = new RegisterParmesan();
        registerParmesan.setMobile(user);
        registerParmesan.setPwd(pass);
        registerParmesan.setVerifyCode(verify);
        String toJson = JsonUtils.classToJson(registerParmesan);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , toJson);

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://169.254.189.205:8080/kotlin/userCenter/register")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtils.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        final CategoryRespoens bean = JsonUtils.jsonToClass(string, CategoryRespoens.class);
                        int status = bean.getStatus();
                        if (status==0){
                            LogUtils.e("成功");
                            Intent intent = new Intent();
                            intent.putExtra("user", user);
                            setResult(300, intent);
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_register_finish:
                // TODO 20/08/19
                finish();
                break;
            case R.id.btn_register:
                // TODO 20/08/19
                    initRegister();
                break;
            case R.id.tv_register_yanzheng:
                // TODO 20/08/19
                break;
            default:
                break;
        }
    }
}
