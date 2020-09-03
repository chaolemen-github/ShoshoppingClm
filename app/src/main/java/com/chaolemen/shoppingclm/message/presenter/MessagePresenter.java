package com.chaolemen.shoppingclm.message.presenter;

import com.chaolemen.mvplibrary.model.ModelFractory;
import com.chaolemen.mvplibrary.presenter.BasePresenter;
import com.chaolemen.shoppingclm.message.bean.MessageBean;
import com.chaolemen.shoppingclm.message.contract.MessageContract;
import com.chaolemen.shoppingclm.message.model.MessageModel;

import java.util.List;

public class MessagePresenter extends BasePresenter<MessageContract.View> implements MessageContract.Presenter {
    @Override
    public void getDataMsg(String token) {
        MessageModel messageModel = ModelFractory.createModel(MessageModel.class);
        messageModel.getDataMsg(token, this, getLifecycle());
    }

    @Override
    public void onSuccess(List<MessageBean> messageBeans) {
        mView.onSuccess(messageBeans);
    }

    @Override
    public void onFail(String msg, int code) {
        mView.onFailItem(msg + code);
    }

    @Override
    public void onCancel() {
        mView.onCancal();
    }
}
