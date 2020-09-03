package com.chaolemen.shoppingclm.user;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaolemen.mvplibrary.base.BaseFragment;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.app.Contents;
import com.chaolemen.shoppingclm.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment {


    @BindView(R.id.iv_user)
    ImageView ivUser;
    @BindView(R.id.tv_user_login)
    TextView tvUserLogin;
    @BindView(R.id.tv_user_pay)
    TextView tvUserPay;
    @BindView(R.id.tv_user_confirm)
    TextView tvUserConfirm;
    @BindView(R.id.tv_user_completed)
    TextView tvUserCompleted;
    @BindView(R.id.tv_user_order)
    TextView tvUserOrder;
    @BindView(R.id.iv_user_add)
    ImageView ivUserAdd;
    @BindView(R.id.iv_user_share)
    ImageView ivUserShare;
    @BindView(R.id.iv_user_setting)
    ImageView ivUserSetting;
    Unbinder unbinder;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initData() {

        String user = (String) SpUtil.getParam("user", "");
        if (!TextUtils.isEmpty(user)) {
            tvUserLogin.setText(user);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBus(String user){
        SpUtil.setParam("user",user);
        tvUserLogin.setText(user);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_user, R.id.tv_user_login, R.id.tv_user_pay, R.id.tv_user_confirm,
            R.id.tv_user_completed, R.id.tv_user_order, R.id.iv_user_add, R.id.iv_user_share,
            R.id.iv_user_setting,R.id.cons_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user:
                Toast.makeText(getActivity(), "头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_user_login:
                /**
                 * 判断cookie
                 * 空 跳转到登录页面
                 * 不空 跳转到个人信息
                 */
                String cookie = (String) SpUtil.getParam(Contents.SP_COOKIE, "");
                if (TextUtils.isEmpty(cookie)) {
                    Intent intent = new Intent(getActivity(), LoginUserActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), PersonalActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_user_pay:
                Toast.makeText(getActivity(), tvUserPay.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_user_confirm:
                Toast.makeText(getActivity(), tvUserConfirm.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_user_completed:
                Toast.makeText(getActivity(), tvUserCompleted.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_user_order:
                Toast.makeText(getActivity(), tvUserOrder.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_user_add:
                Toast.makeText(getActivity(), "收货管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_user_share:
                Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_user_setting:
//                Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cons_setting:
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent1,100);
                Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 设置界面的回调
     * 清空cookie，用户名
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * setting页面
         * 清空cookie
         */
        if (requestCode==100&&resultCode==300){
            String user = (String) SpUtil.getParam("user", "");
            if (!TextUtils.isEmpty(user)) {
                tvUserLogin.setText(user);
            } else {
                tvUserLogin.setText("登录/注册");
            }
        }
    }
}
