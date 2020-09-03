package com.chaolemen.shoppingclm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.shoppingclm.category.MyButterKnife;

public class Main2Activity extends Activity {

//    @MyButterKnife(R.layout.activity_main2)
//    View view;
    @MyButterKnife(R.id.btn_main2)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("自定义ButterKnife");
            }
        });
    }
}
