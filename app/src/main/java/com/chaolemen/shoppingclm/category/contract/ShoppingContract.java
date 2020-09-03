package com.chaolemen.shoppingclm.category.contract;

import com.chaolemen.mvplibrary.model.BaseModel;
import com.chaolemen.mvplibrary.view.BaseView;
import com.chaolemen.shoppingclm.cart.bean.CartGoods;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface ShoppingContract {
    interface Model extends BaseModel {
        void getDataShopping(ShoppingCallBack shoppingCallBack, LifecycleProvider lifecycleProvider);
    }

    interface View extends BaseView {
        void onSuccess(List<CartGoods> goods);
    }

    interface Presenter extends ShoppingCallBack{
        void getDataShopping();
    }

    interface ShoppingCallBack{
        void onSuccess(List<CartGoods> goods);
        void onFail(String error);
        void onCancal();
    }
}
