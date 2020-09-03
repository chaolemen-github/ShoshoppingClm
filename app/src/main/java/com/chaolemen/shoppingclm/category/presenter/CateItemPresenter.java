package com.chaolemen.shoppingclm.category.presenter;

import com.chaolemen.mvplibrary.model.ModelFractory;
import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;
import com.chaolemen.shoppingclm.category.parmesan.CategoryPramse;
import com.chaolemen.shoppingclm.category.contract.CateItemContract;
import com.chaolemen.shoppingclm.category.model.CateItemModel;
import com.chaolemen.shoppingclm.category.parmesan.ItemParmesan;

import java.util.List;

public class CateItemPresenter extends BasePresenter<CateItemContract.View> implements CateItemContract.Presenter, CateItemContract.ItemCallBack {


    @Override
    public void getDataItem(ItemParmesan itemParmesan) {
        ModelFractory.createModel(CateItemModel.class)
                .getDataItem(itemParmesan, this, getLifecycle());
    }

    @Override
    public void onSuccessItem(List<CategoryItem> itemList) {
        mView.onSuccess(itemList);
    }

    @Override
    public void onFailItem(String error) {
        mView.onFailItem(error);
    }

    @Override
    public void onCancal() {
        mView.onCancal();
    }
}
