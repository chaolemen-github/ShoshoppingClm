package com.chaolemen.shoppingclm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chaolemen.shoppingclm.R;

import java.util.ArrayList;
import java.util.List;

public class VpAdapter extends PagerAdapter {
    Context context;
    List<String> list;

    public VpAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.vp_item, container, false);
        ImageView imageView = inflate.findViewById(R.id.iv_vp);
        String s = list.get(position);
        Glide.with(context).load(s).into(imageView);
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
