package cai.project.module.accountmanagement.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import cai.project.module.accountmanagement.R;

public abstract class BaseView {

    protected View rootView;

    public BaseView(Context context) {
        rootView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        init();
    }

    /**设置布局ID*/
   protected abstract @LayoutRes int getLayoutId();

   /**初始化*/
   protected abstract void init();

    protected View $(@IdRes int id){
        return rootView.findViewById(id);
    }

    public View toView(){
        return rootView;
    }
}
