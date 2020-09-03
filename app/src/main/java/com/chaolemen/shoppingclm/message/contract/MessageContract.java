package com.chaolemen.shoppingclm.message.contract;

import com.chaolemen.mvplibrary.model.BaseModel;
import com.chaolemen.mvplibrary.view.BaseView;
import com.chaolemen.shoppingclm.message.bean.MessageBean;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface MessageContract {
    interface Model extends BaseModel {
        void getDataMsg(String token, MsgCallBack msgCallBack, LifecycleProvider lifecycleProvider);
    }

    interface View extends BaseView {
        void onSuccess(List<MessageBean> messageBeans);
    }

    interface Presenter extends MsgCallBack{
        void getDataMsg(String token);
    }

    interface MsgCallBack{
        void onSuccess(List<MessageBean> messageBeans);
        void onFail(String msg,int code);
        void onCancel();
    }
}
