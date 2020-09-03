package com.chaolemen.shoppingclm.category.fragments;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaolemen.httplibrary.client.HttpClient;
import com.chaolemen.httplibrary.utils.JsonUtils;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.base.BaseMvpFragment;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.BottomDialog;
import com.chaolemen.shoppingclm.category.bean.CategoryDitail;
import com.chaolemen.shoppingclm.category.bean.DBBean;
import com.chaolemen.shoppingclm.category.bean.UpDateCartCount;
import com.chaolemen.shoppingclm.category.contract.AddCartContract;
import com.chaolemen.shoppingclm.category.data.DBHolper;
import com.chaolemen.shoppingclm.category.parmesan.AddCartPrams;
import com.chaolemen.shoppingclm.category.parmesan.DetailParmesan;
import com.chaolemen.shoppingclm.category.presenter.AddCartPresenter;
import com.chaolemen.shoppingclm.net.CGHttpCallBack;
import com.chaolemen.shoppingclm.utils.GlideImageLoader;
import com.chaolemen.shoppingclm.utils.SpUtil;
import com.google.gson.JsonElement;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ren.qinc.numberbutton.NumberButton;

import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class DitailFragment extends Fragment {
    @BindView(R.id.tv_ditail_xuan)
    TextView mTvDitailXuan;
    @BindView(R.id.iv_ditail_more)
    ImageView mIvDitailMore;
    private int position;

    @BindView(R.id.banner_ditail)
    Banner bannerDitail;
    @BindView(R.id.tv_ditail_title)
    TextView tvDitailTitle;
    @BindView(R.id.tv_ditail_money)
    TextView tvDitailMoney;
    @BindView(R.id.tv_ditail_select)
    TextView tvDitailSelect;
    Unbinder unbinder;
    //    private String[] mNames;
    private CategoryDitail beans;
    private int money;
    private int code = 1;
    private String pei;
    private String ban;
    private int id = -1;
    private BottomDialog bottomDialog;
    private DBBean dbBean;
    CategoryDitail goodsDetail;

    public DitailFragment(int position) {
        // Required empty public constructor
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ditail, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initDataDetail();
        return view;
    }


    private void initDataDetail() {
        DetailParmesan detailParmesan = new DetailParmesan();
        detailParmesan.setGoodsId(position);
        String toJson = JsonUtils.classToJson(detailParmesan);

        new HttpClient.Builder()
                .setApiUrl("goods/getGoodsDetail")
                .post()
                .setJsonBody(toJson, true)
                .build()
                .request(new CGHttpCallBack<CategoryDitail>() {
                    @Override
                    public void onError(String message, int code) {
                        LogUtils.e(message + code);
                    }

                    @Override
                    public void cancle() {

                    }

                    @Override
                    public void onSuccess(CategoryDitail categoryDitail) {
                        LogUtils.e("详情" + categoryDitail.toString());
                        goodsDetail = categoryDitail;
                        initViewDetail(categoryDitail);

                    }

                    @Override
                    public CategoryDitail convert(JsonElement result) {
                        return JsonUtils.jsonToClass(result, CategoryDitail.class);
                    }
                });

    }

    private void initViewDetail(CategoryDitail bean) {

        tvDitailTitle.setText(bean.getGoodsDesc() + "");
        String price = bean.getGoodsDefaultPrice();
        int parseInt = Integer.parseInt(price);
        money = parseInt / 100;
        tvDitailMoney.setText("￥" + money + ".00");
        tvDitailSelect.setText(bean.getGoodsDefaultSku() + "");

        ArrayList<String> images = new ArrayList<>();
        String goodsBanner = bean.getGoodsBanner();
        String[] split = goodsBanner.split(",");
        for (int i = 0; i < split.length; i++) {
            images.add(split[i]);
        }

        //设置图片加载器
        bannerDitail.setImageLoader(new GlideImageLoader());
        //设置图片集合
        bannerDitail.setImages(images);
        //设置轮播时间
        bannerDitail.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        bannerDitail.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        bannerDitail.start();

        EventBus.getDefault().postSticky(bean);

        beans = bean;
    }

    @OnClick({R.id.tv_ditail_xuan, R.id.tv_ditail_select, R.id.iv_ditail_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ditail_xuan:
                break;
            case R.id.tv_ditail_select:
                bottomDialog = new BottomDialog(getActivity(), beans);
                bottomDialog.show();
                break;
            case R.id.iv_ditail_more:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventBusSku(CategoryDitail categoryDitail) {
        List<String> skuEvent = bottomDialog.getSkuEvent();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < skuEvent.size(); i++) {
            stringBuilder.append(skuEvent.get(i));
            if (i != skuEvent.size() - 1) {
                stringBuilder.append(",");
            }
        }
        int number = bottomDialog.numberButton.getNumber();
        String skuString = stringBuilder.toString() + "," + number + "件";
        tvDitailSelect.setText(skuString);


        AddCartPrams addCartPrams = new AddCartPrams();
        addCartPrams.setGoodsDesc(this.goodsDetail.getGoodsDesc());
        addCartPrams.setGoodsIcon(goodsDetail.getGoodsDefaultIcon());
        addCartPrams.setGoodsPrice(Integer.parseInt(goodsDetail.getGoodsDefaultPrice()));
        addCartPrams.setGoodsId(goodsDetail.getId());
        addCartPrams.setGoodsCount(number);
        addCartPrams.setGoodsSku(skuString);

        //上传到服务器
        String toJson = JsonUtils.classToJson(addCartPrams);
        HashMap<String, Object> heardmap = new HashMap<>();
        Integer param = (Integer) SpUtil.getParam("token", 0);
        heardmap.put("token",param);
        new HttpClient.Builder()
                .setJsonBody(toJson, true)
                .setApiUrl("cart/add")
                .setHeadres(heardmap)
                .post()
                .build().request(new CGHttpCallBack<Integer>() {
            @Override
            public void onError(String message, int code) {

            }

            @Override
            public void cancle() {

            }

            @Override
            public void onSuccess(Integer integer) {
                String count = String.valueOf(integer);
                SpUtil.setParam("cartCount",Integer.parseInt(count));
                LogUtils.e("上传"+Integer.parseInt(count));
                UpDateCartCount upDateCartCount = new UpDateCartCount();
                upDateCartCount.setCartCount(Integer.parseInt(count));
                EventBus.getDefault().postSticky(upDateCartCount);
            }

            @Override
            public Integer convert(JsonElement result) {
                return result.getAsNumber().intValue();
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
