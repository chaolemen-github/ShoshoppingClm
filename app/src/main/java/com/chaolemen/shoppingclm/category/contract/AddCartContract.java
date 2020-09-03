package com.chaolemen.shoppingclm.category.contract;

import com.chaolemen.mvplibrary.model.BaseModel;
import com.chaolemen.mvplibrary.view.BaseView;
import com.chaolemen.shoppingclm.category.parmesan.AddCartPrams;
import com.trello.rxlifecycle2.LifecycleProvider;

public interface AddCartContract {
    interface Model extends BaseModel {
        void getDataAddCart(AddCartPrams addCartPrams, AddCartCallBack addCartCallBack, LifecycleProvider lifecycleProvider);
    }

    interface View extends BaseView {
        void onSuccessAddCart(String count);
    }

    interface Presenter extends AddCartCallBack{
        void getDataAddCart(AddCartPrams addCartPrams);
    }

    interface AddCartCallBack{
        void onSuccessAddCart(String count);
        void onFail(String error);
        void onCancel();
    }
}
