package com.chaolemen.shoppingclm.fragments;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.chaolemen.mvplibrary.base.BaseFragment;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.adapter.HomeRecyclerAdapter;
import com.chaolemen.shoppingclm.adapter.VpAdapter;
import com.google.zxing.client.android.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    //banner图片
    String HOME_BANNER_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876108368&di=cd9725c81901f6d7499edd76cf2e68e5&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F37%2F20%2F80Q58PICe3W_1024.jpg";
    String HOME_BANNER_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502882008108&di=d0cf4a8536aefa5df791716c1053ca66&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e9495812c7f9a84a0d304fbc135b.jpg";
    String HOME_BANNER_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876281925&di=f33e7ef8be268e90ffbffd315f5fb0a3&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F013e1b57d2731c0000018c1beeca11.jpg%40900w_1l_2o_100sh.jpg";
    String HOME_BANNER_FOUR = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503471047&di=679d7a6c499f59d1b0dcd56b62a9aa6c&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.90sheji.com%2Fdianpu_cover%2F11%2F14%2F64%2F55%2F94ibannercsn_1200.jpg";

    //折扣图片
    String HOME_DISCOUNT_ONE = "https://img14.360buyimg.com/n0/jfs/t3157/231/5756125504/98807/97ab361d/588084a1N06efb01d.jpg";
    String HOME_DISCOUNT_TWO = "https://img10.360buyimg.com/n7/jfs/t5905/106/1120548052/61075/6eafa3a5/592f8f7bN763e3d30.jpg";
    String HOME_DISCOUNT_THREE = "https://img10.360buyimg.com/n7/jfs/t5584/99/6135095454/371625/423b9ba5/59681d91N915995a7.jpg";
    String HOME_DISCOUNT_FOUR = "https://img11.360buyimg.com/n7/jfs/t4447/301/1238553109/193354/13c7e995/58db19a7N25101fe4.jpg";
    String HOME_DISCOUNT_FIVE = "https://img14.360buyimg.com/n1/s190x190_jfs/t7525/189/155179632/395056/e200017f/598fb8a4N7800dee6.jpg";

    //话题图片
    String HOME_TOPIC_ONE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881866380&di=d252e1e8dd3a5a836fe360b02531f917&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01f5ce56e112ef6ac72531cb37bec4.png%40900w_1l_2o_100sh.jpg";
    String HOME_TOPIC_TWO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502881904494&di=7a16834200a70469e1d3b6a4ab04c514&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F010d11554baebf000001bf721352ac.jpg";
    String HOME_TOPIC_THREE = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502876222250&di=aa3290c84822ba5570f19cb76e1012af&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0146d25768b5a10000018c1b00cf27.jpg%40900w_1l_2o_100sh.jpg";
    String HOME_TOPIC_FOUR = "http://img.zcool.cn/community/01796c58772f66a801219c77e4fc04.png@1280w_1l_2o_100sh.png";
    String HOME_TOPIC_FIVE = "http://img.zcool.cn/community/0154805791ffeb0000012e7edba495.jpg@900w_1l_2o_100sh.jpg";
    /*
        初始化主题
     */
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @BindView(R.id.fl_home_banner)
    Banner flHomeBanner;


    @BindView(R.id.tv_home_special)
    TextView tvHomeSpecial;
    @BindView(R.id.recycler_home)
    RecyclerView recyclerHome;
    @BindView(R.id.ViewFlipper)
    ViewFlipper mViewFlipper;
    @BindView(R.id.con)
    ConstraintLayout mConstraintLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.mScanIv)
    ImageView mMScanIv;



    private int pagerWidth;
    private List<ImageView> imageViews;
    private final int PERMS_REQUEST_CODE=202;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

        String[] permissions=new String[]{Manifest.permission.
                WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            requestPermissions(permissions,PERMS_REQUEST_CODE);
        }

        initBanner();//banner轮播图
        initRecycler();//特价水平滑动
        initTopicPagerContainer();//画廊
        initViewFlipper();//公告
    }

    private void initTopicPagerContainer() {
//话题
        List<String> list = new ArrayList<>();
//        list.add(HOME_TOPIC_ONE);
//        list.add(HOME_TOPIC_TWO);
//        list.add(HOME_TOPIC_THREE);
        list.add(HOME_TOPIC_FOUR);
        list.add(HOME_TOPIC_FIVE);
        list.add(HOME_TOPIC_FOUR);
        list.add(HOME_TOPIC_FIVE);
        list.add(HOME_TOPIC_FOUR);

        VpAdapter vpAdapter = new VpAdapter(getActivity(), list);
        viewPager.setAdapter(vpAdapter);
        //动画
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {


                page.setPivotX(0);//X轴
                if (position < -1 || position > 1) {
                    page.setRotationY(0);
                } else {
                    if (position < 0) {
                        page.setPivotX(page.getWidth());
                        page.setRotationY((position) * 30);
                        page.setScaleX(1 + (position / 2));
                    } else {
                        page.setPivotX(0);
                        page.setRotationY(position * 30);
                        page.setScaleX(1 - (position / 2));
                    }
                }
            }
        });
    }


    //recyclerView水平滑动--特价
    private void initRecycler() {
        //获得线性布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //设置为水平滑动
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerHome.setLayoutManager(linearLayoutManager);

        List<String> list = new ArrayList<>();
        list.add(HOME_DISCOUNT_ONE);
        list.add(HOME_DISCOUNT_TWO);
        list.add(HOME_DISCOUNT_THREE);
        list.add(HOME_DISCOUNT_FOUR);
        list.add(HOME_DISCOUNT_FIVE);

        HomeRecyclerAdapter recyclerAdapter = new HomeRecyclerAdapter(getActivity(), list);
        recyclerHome.setAdapter(recyclerAdapter);
    }

    //banner轮播图
    private void initBanner() {

        ArrayList<String> images = new ArrayList<>();
        images.add(HOME_BANNER_ONE);
        images.add(HOME_BANNER_TWO);
        images.add(HOME_BANNER_THREE);
        images.add(HOME_BANNER_FOUR);

        //设置banner样式
//        flHomeBanner.setBannerStyle(BannerConfig.LEFT);

        //设置图片加载器
        flHomeBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        flHomeBanner.setImages(images);
        //设置轮播时间
        flHomeBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        flHomeBanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        flHomeBanner.start();
    }



    @OnClick(R.id.mScanIv)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, 0);
    }


    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            return new ImageView(context);
        }
    }


    public TextView buildNewsView(String text) {
        TextView textView = new TextView(getActivity());

        textView.setText(text);

        textView.setLayoutParams(mConstraintLayout.getLayoutParams());
        return textView;
    }


    public void setData(List<String> list) {
        for (String text : list) {
            mViewFlipper.addView(buildNewsView(text));
        }
        mViewFlipper.startFlipping();
    }

    String text = "夏日炎炎,第一波福利还有30秒到达战场,新用户立领1000元优惠券";
    String[] split = text.split(",");
    List<String> strings = Arrays.asList(split[0], split[1], split[2]);

    private void initViewFlipper() {
        mViewFlipper.setInAnimation(getActivity(), R.anim.news_bottom_in);
        mViewFlipper.setOutAnimation(getActivity(), R.anim.news_bottom_out);

        setData(strings);
    }


}
