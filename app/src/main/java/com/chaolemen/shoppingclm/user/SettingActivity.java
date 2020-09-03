package com.chaolemen.shoppingclm.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.app.Contents;
import com.chaolemen.shoppingclm.utils.SpUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSettingFinishTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        mSettingFinishTv = (Button) findViewById(R.id.tv_setting_finish);
        mSettingFinishTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting_finish:
                // TODO 20/08/20
                SpUtil.clear();
                SpUtil.setParam(Contents.SP_LOGIN,false);
                Intent intent = new Intent();
                setResult(300,intent);
                finish();
                break;
            default:
                break;
        }
    }
}
