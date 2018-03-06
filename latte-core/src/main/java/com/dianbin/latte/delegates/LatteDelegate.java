package com.dianbin.latte.delegates;

/**
 * Created by ZHEN on 2018/2/27.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate{

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate(){
        return (T) getParentFragment();
    }

}
