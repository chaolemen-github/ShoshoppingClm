package com.chaolemen.shoppingclm.category.contract;

import com.chaolemen.mvplibrary.model.BaseModel;
import com.chaolemen.mvplibrary.view.BaseView;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;
import com.chaolemen.shoppingclm.category.parmesan.CategoryPramse;
import com.chaolemen.shoppingclm.category.parmesan.ItemParmesan;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface CateItemContract {
    interface Model extends BaseModel {
        void getDataItem(ItemParmesan itemParmesan, ItemCallBack itemCallBack, LifecycleProvider lifecycleProvider);
    }

    interface View extends BaseView {
        void onSuccess(List<CategoryItem> itemList);
    }

    interface Presenter {
        void getDataItem(ItemParmesan itemParmesan);
    }

    interface ItemCallBack{
        void onSuccessItem(List<CategoryItem> itemList);
        void onFailItem(String error);
        void onCancal();
    }
}
