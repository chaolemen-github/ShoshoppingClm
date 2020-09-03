package com.chaolemen.shoppingclm.category.presenter;

import com.chaolemen.shoppingclm.category.bean.CategoryDemo;
import com.chaolemen.shoppingclm.category.bean.CategoryList;
import com.chaolemen.shoppingclm.category.contract.CategoryContract;
import com.chaolemen.shoppingclm.category.model.CategoryModel;
import com.chaolemen.mvplibrary.model.ModelFractory;
import com.chaolemen.mvplibrary.presenter.BasePresenter;

import java.util.List;

public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter, CategoryContract.CallBack {

    @Override
    public void getData(int parentId) {
        CategoryModel model = ModelFractory.createModel(CategoryModel.class);
        model.getData(parentId,this,getLifecycle());
    }

    @Override
    public void getDataComputer(int parentId) {
        CategoryModel model = ModelFractory.createModel(CategoryModel.class);
        model.getDataComputer(parentId,this,getLifecycle());
    }

    @Override
    public void getDataPhone(int parentId) {

    }

    @Override
    public void onSuccess(List<CategoryDemo> demo) {
        mView.onSuccess(demo);
    }

    @Override
    public void onSuccessComputer(List<CategoryList> demo) {
        mView.onSuccessComputer(demo);
    }

    @Override
    public void onSuccessPhone(List<CategoryList> demo) {

    }
}
