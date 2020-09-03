package com.chaolemen.mvplibrary.base;

import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.mvplibrary.view.BaseView;

public abstract class BaseMvpFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseFragment {

    public P mPresenter;

    //重写baseFragment的方法
    @Override
    protected void initData() {
        //创建一个创建presenter对象的方法
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);//绑定view
        }
    }

    protected abstract P initPresenter();

    //销毁，解除绑定
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.destroyView();
            mPresenter = null;
        }
    }
}
