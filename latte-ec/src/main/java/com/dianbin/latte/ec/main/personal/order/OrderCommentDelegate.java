package com.dianbin.latte.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.dianbin.latte.delegates.LatteDelegate;
import com.dianbin.latte.ec.R;
import com.dianbin.latte.ui.widget.StarLayout;

/**
 * Created by ZHEN on 2018/3/12.
 */

public class OrderCommentDelegate extends LatteDelegate {

    private StarLayout mStarLayout;
    private AppCompatTextView mTvSmbit;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initView();
        mTvSmbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int starCount = mStarLayout.getStarCount();
                Toast.makeText(_mActivity, String.valueOf(starCount), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mStarLayout = $(R.id.custom_star_layout);
        mTvSmbit = $(R.id.top_tv_comment_commit);
    }
}
