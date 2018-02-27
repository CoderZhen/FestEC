package com.dianbin.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by ZHEN on 2018/2/27.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue6dd'),
    icon_alipay('\ue64b');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
