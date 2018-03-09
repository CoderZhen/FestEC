package com.dianbin.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by ZHEN on 2018/2/28.
 */

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHolder> {

    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
