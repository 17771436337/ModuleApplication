package cai.project.module.common_view.basis;

import android.content.Intent;
import android.support.annotation.LayoutRes;

/**布局初始化接口*/
public interface BasisViewInterface {
    /**布局Id*/
    @LayoutRes int getLayoutId();
    /**初始化视图*/
    void initView();
    /**初始化数据*/
    void initData();
    /**获取从其他页面传递过来的数据*/
    void getIntents(Intent intent);


}
