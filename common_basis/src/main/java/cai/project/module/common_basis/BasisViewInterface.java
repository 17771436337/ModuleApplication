package cai.project.module.common_basis;

import android.content.Intent;
import android.support.annotation.LayoutRes;

public interface BasisViewInterface {
    /**布局Id*/
    @LayoutRes
    int getLayoutId();
    /**初始化视图*/
    void initView();
    /**初始化数据*/
    void initData();

}
