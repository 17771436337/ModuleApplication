package cai.project.module.common_basis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public abstract class BasisActivity extends AppCompatActivity implements BasisViewInterface{
    /**
     * 使用自己的view
     */
    public static final int USE_SELEF_VIEW = -1;

    //软键盘管理
    private InputMethodManager mInputMethodManager;
    protected Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() != USE_SELEF_VIEW){
            setContentView(getLayoutId());
        }

        preInit(savedInstanceState);
        mContext = this;
        init();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInputMethodManager = null;
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }


    /**关闭软键盘*/
    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.mInputMethodManager != null)) {
            this.mInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    /**
     * 初始化相关内容
     */
    private void init(){
        getIntents(getIntent());
        initView();
        initData();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntents(intent);
    }


    /**设置返回获取Intent*/
    protected abstract void getIntents(Intent intent);

    /**
     * 在init方法之前 用于getContentView == -1的情况
     * @param savedInstanceState
     */
    protected void preInit(Bundle savedInstanceState) {}

}
