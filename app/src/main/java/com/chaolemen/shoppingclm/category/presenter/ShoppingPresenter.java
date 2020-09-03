package com.chaolemen.shoppingclm.category.presenter;

import com.chaolemen.mvplibrary.model.ModelFractory;
import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.shoppingclm.cart.bean.CartGoods;
import com.chaolemen.shoppingclm.category.contract.ShoppingContract;
import com.chaolemen.shoppingclm.category.model.ShoppingModel;

import java.util.List;

public class ShoppingPresenter extends BasePresenter<ShoppingContract.View> implements ShoppingContract.Presenter {
    @Override
    public void getDataShopping() {
        ShoppingModel shoppingModel = ModelFractory.createModel(ShoppingModel.class);
        shoppingModel.getDataShopping(this, getLifecycle());
    }

    @Override
    public void onSuccess(List<CartGoods> goods) {
        mView.onSuccess(goods);
    }

    @Override
    public void onFail(String error) {
        mView.onFailItem(error);
    }

    @Override
    public void onCancal() {

    }
}
