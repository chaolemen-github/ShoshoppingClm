package com.chaolemen.shoppingclm.cart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.cart.adapter.SettleAdapter;
import com.chaolemen.shoppingclm.cart.bean.CartGoods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettleActivity extends AppCompatActivity {

    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.toolbar_settle)
    Toolbar mToolbarSettle;
    @BindView(R.id.iv_settle_finish)
    ImageView mIvSettleFinish;
    @BindView(R.id.tv_settle_site_title)
    TextView mTvSettleSiteTitle;
    @BindView(R.id.tv_settle_site_text)
    TextView mTvSettleSiteText;
    @BindView(R.id.iv_settle_site)
    ImageView mIvSettleSite;
    @BindView(R.id.constraint_take)
    ConstraintLayout mConstraintTake;
    @BindView(R.id.recycler_settle)
    RecyclerView mRecyclerSettle;
    @BindView(R.id.tv_settle_count)
    TextView mTvSettleCount;
    @BindView(R.id.btn_settle_add)
    Button mBtnSettleAdd;
    private List<CartGoods> list = new ArrayList<>();
    private String count;

    String info = "app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D";

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Map<String, Object> result = (Map<String, Object>) msg.obj;

            LogUtils.e(result.toString());
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        ButterKnife.bind(this);

        list.clear();
        Intent intent = getIntent();
        list = (List<CartGoods>) intent.getSerializableExtra("list");
        count = intent.getStringExtra("count");

        getView();
    }

    private void getView() {
        mToolbarSettle.setTitle("订单详情");
        setSupportActionBar(mToolbarSettle);

        TextView childAt = (TextView) mToolbarSettle.getChildAt(0);
        childAt.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        childAt.setGravity(Gravity.CENTER);


        LogUtils.e("结算" + list);
        LogUtils.e("价格" + count);

        mTvSettleCount.setText(count);
        mRecyclerSettle.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerSettle.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        SettleAdapter settleAdapter = new SettleAdapter(list, this, R.layout.activity_settle_item);
        mRecyclerSettle.setAdapter(settleAdapter);

    }

    @OnClick({R.id.iv_settle_finish, R.id.iv_settle_site, R.id.btn_settle_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_settle_finish:
                finish();
                break;
            case R.id.iv_settle_site:
                Intent intent = new Intent(this, AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_settle_add:
                alipay();
                break;
        }
    }

    private void alipay() {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(SettleActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
}
