package com.chaolemen.shoppingclm.message.adapter;

import android.content.Context;

import com.chaolemen.mvplibrary.adapter.BaseAdapter;
import com.chaolemen.mvplibrary.adapter.BaseViewHolder;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.message.bean.MessageBean;

import java.util.List;

public class MessageAdapter extends BaseAdapter<MessageBean> {

    public MessageAdapter(List<MessageBean> datas, Context context, int itemlayoutId) {
        super(datas, context, itemlayoutId);
    }

    @Override
    protected void bindData(BaseViewHolder holder, MessageBean messageBean) {
        holder.setImageViewContent(R.id.iv_msg_img,messageBean.getMsgIcon());
        holder.setTextViewContent(R.id.tv_msg_login,messageBean.getMsgTitle());
        holder.setTextViewContent(R.id.tv_msg_login1,messageBean.getMsgContent());
        holder.setTextViewContent(R.id.tv_msg_time,messageBean.getMsgTime());
    }
}
