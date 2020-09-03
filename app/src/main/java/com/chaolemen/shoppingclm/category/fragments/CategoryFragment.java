package com.chaolemen.shoppingclm.category.fragments;


import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.base.BaseMvpFragment;
import com.chaolemen.shoppingclm.category.CategoryActivity;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.adapter.CGAdapterLeft;
import com.chaolemen.shoppingclm.category.adapter.CategoryListAdapter;
import com.chaolemen.shoppingclm.category.bean.CategoryDemo;
import com.chaolemen.shoppingclm.category.bean.CategoryList;
import com.chaolemen.shoppingclm.category.contract.CategoryContract;
import com.chaolemen.shoppingclm.category.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseMvpFragment<CategoryContract.View, CategoryPresenter> implements CategoryContract.View {


    @BindView(R.id.toolbar_category)
    Toolbar toolbarCategory;
    @BindView(R.id.rv_cg_left)
    RecyclerView rvCgLeft;
    @BindView(R.id.rv_cg_right)
    RecyclerView rvCgRight;
    private List<CategoryDemo> list;
    private CGAdapterLeft cgAdapterLeft;
    private List<CategoryList> beanList;
    private CategoryListAdapter listAdapter;


    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
//        return R.layout.fragment_category;
        return R.layout.fragment_category;
    }

    @Override
    protected void initData() {
        super.initData();
        //调用presenter的方法
        mPresenter.getData(0);
        mPresenter.getDataComputer(1);

        toolbarCategory.setTitle("商品分类");

        //toolbar居中
        TextView childAt = (TextView) toolbarCategory.getChildAt(0);
        childAt.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        childAt.setGravity(Gravity.CENTER);
        //左侧导航界面
        initTab();
        //右侧列表界面
        initList();
        //导航界面点击监听
        initListener();
        //列表的点击监听，跳转到二级页面，详情
        initListListener();
    }

    private void initListListener() {
        listAdapter.setOnClickCategoryItemListener(new CategoryListAdapter.OnClickCategoryItemListener() {
            @Override
            public void onClick(int parentId,int id,int position) {
                //跳转到二级页面，详情

                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.putExtra("parentId",parentId);
                intent.putExtra("id",id);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    //右侧列表界面
    private void initList() {
        beanList = new ArrayList<>();
        rvCgRight.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        listAdapter = new CategoryListAdapter(getActivity(), beanList);
        rvCgRight.setAdapter(listAdapter);
    }

    //左侧导航界面
    private void initTab() {
        //设置布局管理器，垂直布局
        rvCgLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCgLeft.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));

        //创建集合
        list = new ArrayList<>();
        //创建适配器
        cgAdapterLeft = new CGAdapterLeft(getActivity(), list);
        //绑定适配器
        rvCgLeft.setAdapter(cgAdapterLeft);
    }

    private void initListener() {
        cgAdapterLeft.setOnClickItemCategoryListener(new CGAdapterLeft.OnClickItemCategoryListener() {
            @Override
            public void onClick(int position, int id) {
                LogUtils.e(id + "==" + position);
                //判断下标，传相应的id
                if (position == 0) {
                    mPresenter.getDataComputer(position + 1);
                } else if (position == 1) {
                    mPresenter.getDataComputer(position + 1);
                } else {
                    beanList.clear();
                    listAdapter.notifyDataSetChanged();
                }
                cgAdapterLeft.setmPosition(position);
                cgAdapterLeft.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(List<CategoryDemo> demo) {
        LogUtils.e(demo.toString());
        list.addAll(demo);
        cgAdapterLeft.notifyDataSetChanged();
    }

    @Override
    public void onSuccessComputer(List<CategoryList> demo) {
        beanList.clear();
        beanList.addAll(demo);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessPhone(List<CategoryList> demo) {

    }


    @Override
    protected CategoryPresenter initPresenter() {
        return new CategoryPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onFailItem(String error) {

    }

    @Override
    public void onCancal() {

    }
}
