package com.chaolemen.shoppingclm.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chaolemen.httplibrary.callback.BaseCallBack;
import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.base.BaseMvpFragment;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.cart.SettleActivity;
import com.chaolemen.shoppingclm.cart.adapter.CartAdapter;
import com.chaolemen.shoppingclm.cart.bean.CartGoods;
import com.chaolemen.shoppingclm.category.bean.UpDateTotalPrice;
import com.chaolemen.shoppingclm.category.contract.ShoppingContract;
import com.chaolemen.shoppingclm.category.presenter.ShoppingPresenter;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.chaolemen.shoppingclm.view.HeaderView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseMvpFragment<ShoppingContract.View, ShoppingPresenter> implements ShoppingContract.View {


    @BindView(R.id.cartHv)
    HeaderView cartHv;
    @BindView(R.id.recycler_cart)
    RecyclerView cartRv;
    @BindView(R.id.mAllCheckedCb)
    CheckBox mAllCheckedCb;
    @BindView(R.id.mTotalPriceTv)
    TextView mTotalPriceTv;
    @BindView(R.id.mSettleAccountsBtn)
    Button mSettleAccountsBtn;
    @BindView(R.id.mDeleteBtn)
    Button mDeleteBtn;

    //编辑和完成的标识
    public static boolean isCheck = false;


    private boolean isCheckSingle = true;
    List<CartGoods> goods = new ArrayList<>();
    List<CartGoods> list = new ArrayList<>();
    int totalCount = 0;
    private CartAdapter cartAdapter;
    private TextView mRightTv;

    public CartFragment() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected ShoppingPresenter initPresenter() {
        return new ShoppingPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        mPresenter.getDataShopping();
        cartAdapter = new CartAdapter(goods, getActivity());
        cartRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartRv.setAdapter(cartAdapter);
        mRightTv = cartHv.findViewById(R.id.mRightTv);
        mTotalPriceTv.setVisibility(View.VISIBLE);
        mTotalPriceTv.setText("合计￥0.0");

        //状态   编辑/完成
        mRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCheck) {
                    mRightTv.setText("完成");
                    isCheck = true;
                    mDeleteBtn.setVisibility(View.VISIBLE);
                    mSettleAccountsBtn.setVisibility(View.VISIBLE);
                    mTotalPriceTv.setVisibility(View.GONE);
                } else {
                    mRightTv.setText("编辑");
                    mTotalPriceTv.setVisibility(View.VISIBLE);
                    mTotalPriceTv.setText("￥0.0");
                    //删除按钮
                    mDeleteBtn.setVisibility(View.GONE);
                    //结算按钮
                    mSettleAccountsBtn.setVisibility(View.VISIBLE);
                    isCheck = false;

                    for (CartGoods good : goods) {
                        if (good.isCheck()) {
                            totalCount += Integer.parseInt(good.getGoodsPrice()) * good.getGoodsCount();
                        }
                    }
                    mTotalPriceTv.setText("合计￥" + totalCount);
                }
            }
        });

        //选中
        mAllCheckedCb.setChecked(false);
        mAllCheckedCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckAll) {
                Log.e("chaolm", "onCheckedChanged: " + isCheckAll);
                if (isCheckAll) {
                    for (CartGoods good : goods) {
                        good.setCheck(true);
                    }

                } else {
                    if (isCheckSingle) {
                        for (CartGoods good : goods) {
                            good.setCheck(false);

                        }
                    }

                }
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        // 刷新操作
                        cartAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        //跳转结算
        mSettleAccountsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettleActivity.class);
                intent.putExtra("list", (Serializable) list);
                intent.putExtra("count", mTotalPriceTv.getText().toString().trim());

                startActivity(intent);
            }
        });

        //删除操作
        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                for (CartGoods good : goods) {
                    if (good.isCheck()) {
                        int id = good.getId();
                        hashMap.put("cartIdList", id);
                    }
                }

                String toJson = JsonUtils.classToJson(hashMap);
                new HttpClient.Builder()
                        .post()
                        .setApiUrl("cart/delete")
                        .setJsonBody(toJson, true)
                        .build()
                        .request(new BaseCallBack() {
                            private int status;
                            private JSONObject jsonObject;

                            @Override
                            protected Object onConvert(String result) {
                                try {
                                    jsonObject = new JSONObject(result);
                                    status = jsonObject.getInt("status");
                                    if (status == 0) {
                                        result = "删除成功";
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                return result;
                            }

                            @Override
                            public boolean isCodeSuccess() {
                                return status == 0;
                            }

                            @Override
                            public void onSuccess(Object o) {
                                LogUtils.e(o.toString());

                                goods.clear();
                                mPresenter.getDataShopping();
                            }

                            @Override
                            public Object convert(JsonElement result) {
                                return null;
                            }

                            @Override
                            public void onError(String message, int code) {

                            }

                            @Override
                            public void cancle() {

                            }
                        });
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setTotalPrice(UpDateTotalPrice upDateTotalPrice) {
        int totalPrice = 0;
        int total = 0;
        list.clear();
        for (CartGoods good : goods) {
            if (good.isCheck()) {
                total += 1;
                totalPrice += Integer.parseInt(good.getGoodsPrice()) * good.getGoodsCount();
                list.add(good);
                Log.e("chaolm", "setTotalPrice: " + good.getGoodsPrice() + "\n" + good.getGoodsCount());
            }
        }
        Log.e("chaolm", "setTotalPrice: " + goods.size() + "\n" + total);
        if (total == goods.size()) {
            isCheckSingle = true;
            mAllCheckedCb.setChecked(true);
        } else {
            isCheckSingle = false;
            mAllCheckedCb.setChecked(false);
        }
        mTotalPriceTv.setText("合计:￥" + totalPrice);
    }

    @Override
    public void onSuccess(List<CartGoods> goods1) {
        this.goods.addAll(goods1);
        cartAdapter.setNewData(this.goods);
        Log.e("chaolm", "showCartGoodList: " + goods.toString());
    }

    @Override
    public void onFailItem(String error) {
        LogUtils.e(error);
    }

    @Override
    public void onCancal() {

    }
}
