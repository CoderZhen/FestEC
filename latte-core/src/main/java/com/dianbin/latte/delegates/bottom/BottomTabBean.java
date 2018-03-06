package com.dianbin.latte.delegates.bottom;

/**
 * Created by ZHEN on 2018/3/3.
 * 存储信息
 */

public final class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITlE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITlE = title;
    }


    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITlE;
    }
}
