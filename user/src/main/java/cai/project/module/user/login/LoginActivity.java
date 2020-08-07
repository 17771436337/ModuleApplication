package cai.project.module.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.common.BaseActivity;
import cai.project.module.user.R;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.bt1)
    ImageView bt1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.bt2)
    ImageView bt2;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.forgot)
    Button forgot;
    @BindView(R.id.registered)
    Button registered;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void getIntents(Intent intent) {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_long;
    }


    @Override
    public void initData() {
    }


    @Override
    public String getUserName() {
        return et1.getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return et2.getText().toString().trim();
    }


    @OnClick({R.id.bt1, R.id.bt2, R.id.btn_login, R.id.forgot, R.id.registered})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.bt1) {
            et1.setText("");

        } else if (i == R.id.bt2) {
            et1.setText("");
            et2.setText("");
        } else if (i == R.id.btn_login) {
            mPresenter.login();
        } else if (i == R.id.forgot) {//忘记密码

        } else if (i == R.id.registered) {//注册

        }
    }
}
