package com.chaolemen.shoppingclm.category;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chaolemen.httplibrary.utils.LogUtils;
import com.chaolemen.mvplibrary.adapter.BaseAdapter;
import com.chaolemen.mvplibrary.base.BaseMvpActivity;
import com.chaolemen.shoppingclm.R;
import com.chaolemen.shoppingclm.category.adapter.CategoryItemAdapter;
import com.chaolemen.shoppingclm.category.adapter.ItemCategoryAdapter;
import com.chaolemen.shoppingclm.category.adapter.MyCategoryAdapter;
import com.chaolemen.shoppingclm.category.bean.CategoryItem;
import com.chaolemen.shoppingclm.category.parmesan.CategoryPramse;
import com.chaolemen.shoppingclm.category.contract.CateItemContract;
import com.chaolemen.shoppingclm.category.parmesan.ItemParmesan;
import com.chaolemen.shoppingclm.category.presenter.CateItemPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryActivity extends BaseMvpActivity<CateItemContract.View, CateItemPresenter> implements CateItemContract.View {


    @BindView(R.id.recycler_category)
    RecyclerView recyclerCategory;
    @BindView(R.id.tv_notifin)
    TextView tvNotifin;
    @BindView(R.id.toolbar_category_item)
    Toolbar toolbar;
    @BindView(R.id.iv_toolbar)
    ImageView ivToolbar;
    private List<CategoryItem> list;

    private MyCategoryAdapter adapter1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category;
    }

    @Override
    protected void initData() {
//        CategoryPramse pramse = new CategoryPramse();

         //添加toolbar
        toolbar.setTitle("商品列表");
        setSupportActionBar(toolbar);
        TextView childAt = (TextView) toolbar.getChildAt(0);
        childAt.getLayoutParams().width= LinearLayout.LayoutParams.MATCH_PARENT;
        childAt.setGravity(Gravity.CENTER);

        //系统回退键
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /**
         * 从意图中取值
         */
        Intent intent = getIntent();
        int parentId = intent.getIntExtra("parentId", 0);
        int position = intent.getIntExtra("position", 0);
        int id = intent.getIntExtra("id", 0);

        /**
         * 判断类型
         * parentId = 1 = 电脑
         * parentId = 2 = 手机
         *
         * 隐藏属性= 敬请期待。。。
         * 传值
         * 调用P层方法执行网络请求
         */

        ItemParmesan itemParmesan = new ItemParmesan();
        if (parentId==1&&position<2){
            tvNotifin.setVisibility(View.GONE);
            itemParmesan.setCategoryId(id);
            itemParmesan.setPageNo(1);
            mPresenter.getDataItem(itemParmesan);
        } else if (parentId==2&&position==0){
            tvNotifin.setVisibility(View.GONE);
            itemParmesan.setCategoryId(id);
            itemParmesan.setPageNo(1);
            mPresenter.getDataItem(itemParmesan);
        } else {
            //都不符合条件，就不进行网络请求，将隐藏的属性显示出来
            tvNotifin.setVisibility(View.VISIBLE);
        }


        list = new ArrayList<>();
        recyclerCategory.setLayoutManager(new GridLayoutManager(this,2));

//        adapter = new ItemCategoryAdapter(R.layout.activity_category_item, list);
        adapter1 = new MyCategoryAdapter(list, this, R.layout.activity_category_item);
        recyclerCategory.setAdapter(adapter1);

        //监听返回按钮
        initFinish();
        //监听点击事件
        initListener();
    }

    /**
     * 按下返回按钮返回上一界面
     */
    private void initFinish() {
        ivToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 监听点击事件
     * 用意图带下标跳转
     */
    private void initListener() {

        adapter1.setItemClickListener(new BaseAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int postion) {
                int id = list.get(postion).getId();
                Intent intent = new Intent(CategoryActivity.this, DitailActivity.class);
                intent.putExtra("position",id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected CateItemPresenter initPresenter() {
        return new CateItemPresenter();
    }

    @Override
    public void onSuccess(List<CategoryItem> itemList) {
        list.addAll(itemList);
        adapter1.setNewData(list);
    }

    @Override
    public void onFailItem(String error) {
        LogUtils.e(error);
    }

    @Override
    public void onCancal() {
        mPresenter.onCancal();
    }


}
