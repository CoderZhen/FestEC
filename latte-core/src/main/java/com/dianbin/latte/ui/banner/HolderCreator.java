package com.dianbin.latte.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by ZHEN on 2018/3/5.
 */

public class HolderCreator implements CBViewHolderCreator<ImageHolder>{

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
