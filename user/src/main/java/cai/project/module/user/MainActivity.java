package cai.project.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import cai.project.module.common.BaseActivity;
import cai.project.module.common_mvp.presenter.BasePresenter;
import com.alibaba.android.arouter.facade.annotation.Route;
import cai.project.module.common_arouter.UserPath;

@Route(path = UserPath.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {


    @Override
    protected void getIntents(Intent intent) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_main;
    }

    @Override
    public void initData() {

    }
}
