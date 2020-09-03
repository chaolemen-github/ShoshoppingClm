package com.chaolemen.shoppingclm.message;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.base.BaseMvpFragment;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.app.Contents;
import com.chaolemen.shoppingclm.fragments.HomeFragment;
import com.chaolemen.shoppingclm.message.adapter.MessageAdapter;
import com.chaolemen.shoppingclm.message.bean.MessageBean;
import com.chaolemen.shoppingclm.message.contract.MessageContract;
import com.chaolemen.shoppingclm.message.presenter.MessagePresenter;
import com.chaolemen.shoppingclm.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends BaseMvpFragment<MessageContract.View, MessagePresenter> implements MessageContract.View {
    @BindView(R.id.tv_msg_text)
    TextView mTvMsgText;
    @BindView(R.id.recycler_msg)
    RecyclerView mRecyclerMsg;
    Unbinder unbinder;
    private List<MessageBean> list;
    private MessageAdapter messageAdapter;

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MsgFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(, container, false);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_msg;
    }

    @Override
    protected MessagePresenter initPresenter() {
        return new MessagePresenter();
    }

    @Override
    protected void initData() {
        super.initData();

        mRecyclerMsg.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerMsg.addItemDecoration(new DividerItemDecoration(getActivity(),RecyclerView.VERTICAL));

        list = new ArrayList<>();
        messageAdapter = new MessageAdapter(list, getActivity(), R.layout.fragment_msg_item);
        mRecyclerMsg.setAdapter(messageAdapter);

//        String token = (String) SpUtil.getParam(Contents.SP_TOKEN, "");
        boolean param = (boolean) SpUtil.getParam(Contents.SP_LOGIN, false);
        if (param){
            mTvMsgText.setVisibility(View.GONE);
            mPresenter.getDataMsg("0");
        } else {
            mTvMsgText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccess(List<MessageBean> messageBeans) {

        list.addAll(messageBeans);
        messageAdapter.setNewData(list);
    }

    @Override
    public void onFailItem(String error) {
        LogUtils.e(error);
    }

    @Override
    public void onCancal() {
        mPresenter.onCancel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
