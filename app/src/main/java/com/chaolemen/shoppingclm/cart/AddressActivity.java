package com.chaolemen.shoppingclm.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaolemen.shoppingclm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_address)
    Toolbar mToolbarAddress;
    @BindView(R.id.iv_address_finish)
    ImageView mIvAddressFinish;
    @BindView(R.id.tv_address_add)
    TextView mTvAddressAdd;
    @BindView(R.id.recycler_address)
    RecyclerView mRecyclerAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mToolbarAddress.setTitle("收货地址");
        TextView childAt = (TextView) mToolbarAddress.getChildAt(0);
        childAt.getLayoutParams().width= LinearLayout.LayoutParams.MATCH_PARENT;
        childAt.setGravity(Gravity.CENTER);


    }

    @OnClick({R.id.iv_address_finish, R.id.tv_address_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_address_finish:
                finish();
                break;
            case R.id.tv_address_add:
                Intent intent = new Intent(this, NewAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
