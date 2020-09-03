package com.chaolemen.mvplibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.mvplibrary.view.BaseView;
import com.trello.rxlifecycle2.components.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends RxFragment {

    public Activity mActivity;
    public Context context;
    private View root;
    private Unbinder unbinder;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
        this.context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (root==null) {
            root = inflater.inflate(getLayoutId(), container, false);
        }
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder!=null){
            unbinder.unbind();
        }
        if (root!=null){
            ((ViewGroup)root.getParent()).removeView(root);
        }
    }
}
