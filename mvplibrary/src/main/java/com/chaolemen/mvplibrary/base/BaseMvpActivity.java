package com.chaolemen.mvplibrary.base;

import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.mvplibrary.view.BaseView;

public abstract class BaseMvpActivity<V extends BaseView,P extends BasePresenter<V>> extends BaseActivity {

    public P mPresenter;

    //初始化视图布局
    @Override
    protected void initEvent() {
        mPresenter=initPresenter();
        if (mPresenter!=null){
            //绑定view
            mPresenter.attachView((V) this);
        }
    }

    //创建presenter的方法
    protected abstract P initPresenter();

    //销毁回调
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.destroyView();
            mPresenter=null;
        }
    }
}
