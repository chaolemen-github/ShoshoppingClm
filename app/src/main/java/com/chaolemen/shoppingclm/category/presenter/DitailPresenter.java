package com.chaolemen.shoppingclm.category.presenter;

import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.shoppingclm.category.bean.CategoryDitail;
import com.chaolemen.shoppingclm.category.contract.DitailContract;

public class DitailPresenter extends BasePresenter<DitailContract.View> implements DitailContract.Presenter {
    @Override
    public void getDataDiatil(DitailPresenter ditailPresenter) {

    }

    @Override
    public void onSuccess(CategoryDitail categoryDitail) {

    }

    @Override
    public void onFail(String msg, int code) {

    }

    @Override
    public void onCancal() {

    }
}
