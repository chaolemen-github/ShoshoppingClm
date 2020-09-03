package com.chaolemen.shoppingclm.user.contract;

import com.chaolemen.mvplibrary.model.BaseModel;
import com.chaolemen.mvplibrary.view.BaseView;
import com.chaolemen.shoppingclm.user.bean.LoginBean;
import com.chaolemen.shoppingclm.user.parmesan.LoginParmesan;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface LoginContract {
    interface Model extends BaseModel {
        void getDataLogin(LoginParmesan loginParmesan, LoginCallBack loginCallBack, LifecycleProvider lifecycleProvider);
    }

    interface View extends BaseView {
        void onSuccess(LoginBean loginBean);
    }

    interface Presenter extends LoginCallBack{
        void getDataLogin(LoginParmesan loginParmesan);
    }

    interface LoginCallBack{
        void onSuccess(LoginBean loginBean);
        void onFail(String error);
        void onCancal();
    }
}
