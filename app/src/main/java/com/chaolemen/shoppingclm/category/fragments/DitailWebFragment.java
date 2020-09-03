package com.chaolemen.shoppingclm.category.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.base.BaseFragment;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.bean.CategoryDitail;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DitailWebFragment extends Fragment {

   private static final String IMG1 = "https://img20.360buyimg.com/vc/jfs/t3034/151/748569500/226790/d6cd86a2/57b15612N81dc489d.jpg";
   private static final String IMG2 = "https://img20.360buyimg.com/vc/jfs/t2683/60/4222930118/169462/233c7678/57b15616N1e285f09.jpg";

    @BindView(R.id.iv_web_img1)
    ImageView ivWebImg1;
    @BindView(R.id.iv_web_img2)
    ImageView ivWebImg2;
    Unbinder unbinder;

    public DitailWebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ditail_web, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void initView(CategoryDitail bean) {
        LogUtils.e("详情图片");
        Glide.with(getActivity()).load(bean.getGoodsDetailOne()).into(ivWebImg1);
        Glide.with(getActivity()).load(bean.getGoodsDetailTwo()).into(ivWebImg2);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
