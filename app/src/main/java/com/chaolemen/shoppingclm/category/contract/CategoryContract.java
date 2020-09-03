package com.chaolemen.shoppingclm.category.contract;

import com.chaolemen.mvplibrary.view.BaseView;
import com.chaolemen.shoppingclm.category.bean.CategoryDemo;
import com.chaolemen.shoppingclm.category.bean.CategoryList;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface CategoryContract {
    interface Model {
        void getData(int parentId, CallBack callBack, LifecycleProvider lifecycle);
        void getDataComputer(int parentId, CallBack callBack, LifecycleProvider lifecycle);
    }

    interface View extends BaseView {
        void onSuccess(List<CategoryDemo> demo);
        void onSuccessComputer(List<CategoryList> demo);
        void onSuccessPhone(List<CategoryList> demo);
    }

    interface Presenter {
        void getData(int parentId);
        void getDataComputer(int parentId);
        void getDataPhone(int parentId);

    }

    interface CallBack{
        void onSuccess(List<CategoryDemo> demo);
        void onSuccessComputer(List<CategoryList> demo);
        void onSuccessPhone(List<CategoryList> demo);
    }
}
