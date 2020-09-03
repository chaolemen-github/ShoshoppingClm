package com.chaolemen.shoppingclm.category.presenter;

import com.chaolemen.mvplibrary.model.ModelFractory;
import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.shoppingclm.category.contract.AddCartContract;
import com.chaolemen.shoppingclm.category.model.AddCartModel;
import com.chaolemen.shoppingclm.category.parmesan.AddCartPrams;

public class AddCartPresenter extends BasePresenter<AddCartContract.View> implements AddCartContract.Presenter {
    @Override
    public void getDataAddCart(AddCartPrams addCartPrams) {
        AddCartModel addCartModel = ModelFractory.createModel(AddCartModel.class);
        addCartModel.getDataAddCart(addCartPrams,this,getLifecycle());
    }

    @Override
    public void onSuccessAddCart(String count) {
        mView.onSuccessAddCart(count);
    }

    @Override
    public void onFail(String error) {
        mView.onFailItem(error);
    }

    @Override
    public void onCancel() {
        mView.onCancal();
    }
}
