package com.chaolemen.shoppingclm.category.contract;

import com.chaolemen.mvplibrary.model.BaseModel;
import com.chaolemen.mvplibrary.view.BaseView;
import com.chaolemen.shoppingclm.category.bean.CategoryDitail;
import com.chaolemen.shoppingclm.category.presenter.DitailPresenter;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface DitailContract {
    interface Model extends BaseModel {
        void getDataDiatil(DitailPresenter ditailPresenter, DiatilCallBack diatilCallBack, LifecycleProvider lifecycleProvider);
    }

    interface View extends BaseView {
        void onSuccess(CategoryDitail categoryDitail);
    }

    interface Presenter extends DiatilCallBack{
        void getDataDiatil(DitailPresenter ditailPresenter);
    }

    interface DiatilCallBack{
        void onSuccess(CategoryDitail categoryDitail);
        void onFail(String msg,int code);
        void onCancal();
    }
}
