package cai.project.module.common_basis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasisFragment extends Fragment implements BasisViewInterface{

    /**
     * 使用自己的view
     */
    public static final int USE_SELEF_VIEW = -1;

    protected View mRootView;
    protected LayoutInflater inflater;
    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;
    //标志位 fragment是否可见
    protected boolean isVisible;
    //是否用了懒加载
    protected boolean isLazy;
    protected Context mContext;
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null){
                parent.removeView(mRootView);
            }

        } else {
            if (getLayoutId() == USE_SELEF_VIEW){
                mRootView = getPageView();
            }else {
                mRootView = inflater.inflate(getLayoutId(), container, false);
            }

            mActivity = getActivity();
            mContext = mActivity;
            this.inflater = inflater;
        }
        return mRootView;
    }


    /**
     * getLayoutId() == -1的情况
     * @return
     */
    protected View getPageView() {
        return null;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       getIntents(getArguments());
        isPrepared = true;
        init();
    }



    private void init(){
        initView();
        initData();
        lazyLoad();
    }





    /**
     * 获取viewid
     * @param id
     * @return
     */
    public View findViewById(@IdRes int id) {
        View view;
        if (mRootView != null) {
            view = mRootView.findViewById(id);
            return view;
        }
        return null;
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        lazyLoadData();
        isPrepared = false;
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**获取返回数据的方法*/
    protected abstract void getIntents(Bundle arguments);

    /**
     * 懒加载
     */
    protected void lazyLoadData() {
        isLazy = true;
    }

    @Override
    public void initData(){
        isLazy = false;
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() { }
}
