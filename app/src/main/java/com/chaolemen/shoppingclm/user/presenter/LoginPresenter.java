package com.chaolemen.shoppingclm.user.presenter;

import com.chaolemen.mvplibrary.model.ModelFractory;
import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.shoppingclm.user.bean.LoginBean;
import com.chaolemen.shoppingclm.user.contract.LoginContract;
import com.chaolemen.shoppingclm.user.model.LoginModel;
import com.chaolemen.shoppingclm.user.parmesan.LoginParmesan;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    @Override
    public void getDataLogin(LoginParmesan loginParmesan) {
        LoginModel loginModel = ModelFractory.createModel(LoginModel.class);
        loginModel.getDataLogin(loginParmesan,this,getLifecycle());
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        mView.onSuccess(loginBean);
    }

    @Override
    public void onFail(String error) {
        mView.onFailItem(error);
    }

    @Override
    public void onCancal() {
        mView.onCancal();
    }
}
