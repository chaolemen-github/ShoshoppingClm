package com.chaolemen.shoppingclm.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaolemen.shoppingclm.R;


/**
 * 项目名：Shopping
 * 包名：  com.example.liangxq.shopping.view
 * 文件名：HeaderView
 * 创建者：liangxq
 * 创建时间：2020/8/10  18:32
 * 描述：TODO
 */
public class HeaderView extends FrameLayout {
    //是否显示返回键
    private Boolean isShowback = true;
    //标题的文字
    private String titleText;
    //右边的文字
    private String rigtText;

    private TextView mRightTv, mTitleTv;

    private ImageView mLeftIv;

    public HeaderView(@NonNull Context context) {
        this(context, null);
    }

    public HeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Header);
        rigtText = typedArray.getString(R.styleable.Header_rightText);
        titleText = typedArray.getString(R.styleable.Header_titleText);
        isShowback = typedArray.getBoolean(R.styleable.Header_isShowBack, true);
        initView();
        typedArray.recycle();
    }

    private void initView() {
        View inflate = View.inflate(getContext(), R.layout.layout_header_bar, this);
        mLeftIv = inflate.findViewById(R.id.mLeftIv);
        mRightTv = inflate.findViewById(R.id.mRightTv);
        mTitleTv = inflate.findViewById(R.id.mTitleTv);
        if (isShowback) {
            mLeftIv.setVisibility(VISIBLE);
        } else {
            mLeftIv.setVisibility(INVISIBLE);
        }
        if (titleText!=null) {
            mTitleTv.setText(titleText);
        }

        if (rigtText!=null) {
            mRightTv.setText(rigtText);
            mRightTv.setVisibility(VISIBLE);
        }
        mLeftIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity) {
                    ((Activity)getContext()).finish();
                }
            }
        });
    }


}
