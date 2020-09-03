package com.chaolemen.shoppingclm;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.chaolemen.shoppingclm.category.fragments.CategoryFragment;
import com.chaolemen.mvplibrary.base.BaseActivity;
import com.chaolemen.shoppingclm.fragments.CartFragment;
import com.chaolemen.shoppingclm.fragments.HomeFragment;
import com.chaolemen.shoppingclm.message.MsgFragment;
import com.chaolemen.shoppingclm.user.UserFragment;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.frameLayout_main)
    FrameLayout frameLayoutMain;
    @BindView(R.id.bnr_main)
    BottomNavigationBar bnrMain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private HomeFragment homeFragment;
    private MsgFragment msgFragment;
    private CartFragment cartFragment;
    private CategoryFragment categoryFragment;
    private UserFragment userFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initEvent() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
//        homeFragment = new HomeFragment();
        /** 导航基础设置 包括按钮选中效果 导航栏背景色等 */
        bnrMain.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                /**
                 *  setMode() 内的参数有三种模式类型：
                 *  MODE_DEFAULT 自动模式：导航栏Item的个数<=3 用 MODE_FIXED 模式，否则用 MODE_SHIFTING 模式
                 *  MODE_FIXED 固定模式：未选中的Item显示文字，无切换动画效果。
                 *  MODE_SHIFTING 切换模式：未选中的Item不显示文字，选中的显示文字，有切换动画效果。
                 */
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                /**
                 *  setBackgroundStyle() 内的参数有三种样式
                 *  BACKGROUND_STYLE_DEFAULT: 默认样式 如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC
                 *                                    如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。
                 *  BACKGROUND_STYLE_STATIC: 静态样式 点击无波纹效果
                 *  BACKGROUND_STYLE_RIPPLE: 波纹样式 点击有波纹效果
                 */
                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#808080") //未选中颜色
                .setBarBackgroundColor("#ffffff");//导航栏背景色

        /** 添加导航按钮 */
        int lastSelectedPosition = 5;//个数
        bnrMain.addItem(new BottomNavigationItem(R.drawable.select_home, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.select_category, "分类"))
                .addItem(new BottomNavigationItem(R.drawable.select_cart, "购物车"))
                .addItem(new BottomNavigationItem(R.drawable.select_msg, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.select_user, "我的"))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise(); //initialise 一定要放在 所有设置的最后一项

        setDefaultFragment();//设置默认导航栏
    }


    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.replace(R.id.frameLayout_main, homeFragment);
        transaction.commit();
    }

    @Override
    protected void initData() {

    }

    /**
     * 设置导航选中的事件
     */
    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                //判空，为空创建并加载fragment
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.frameLayout_main, homeFragment);
                break;
            case 1:
                if (categoryFragment == null) {
                    categoryFragment = new CategoryFragment();
                }
                transaction.replace(R.id.frameLayout_main, categoryFragment);
                break;
            case 2:
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                }
                transaction.replace(R.id.frameLayout_main, cartFragment);
                break;
            case 3:
                if (msgFragment == null) {
                    msgFragment = new MsgFragment();
                }
                transaction.replace(R.id.frameLayout_main, msgFragment);
                break;
            case 4:
                if (userFragment == null) {
                    userFragment = new UserFragment();
                }
                transaction.replace(R.id.frameLayout_main, userFragment);
                break;

            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    /**
     * 设置未选中Fragment 事务
     */
    @Override
    public void onTabUnselected(int position) {

    }

    /**
     * 设置释放Fragment 事务
     */
    @Override
    public void onTabReselected(int position) {

    }


}
