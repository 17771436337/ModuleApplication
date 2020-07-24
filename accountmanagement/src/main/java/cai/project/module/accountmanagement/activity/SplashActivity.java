package cai.project.module.accountmanagement.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.R;
import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_database.entity.ApplyEntity;
import cai.project.module.common_utils.codeutils.AppUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.tv_password_hint)
    TextView tvPasswordHint;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    //--------------------
    private ApplyEntity date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmanagement_activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        date =  DaoUtils.getApplyDao().getApply(AppUtils.getAppPackageName());
        if (date != null){
            initData();
        }else{
            SetPasswordActivity.startSetPassword(this, Constants.ADD);
        }
    }

    private void initData() {
        tvPasswordHint.setText(date.getPasswordHint());
    }

    @OnClick({R.id.bt_confirm, R.id.tv_forgot_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                if (date != null){
                    String password = etPassword.getText().toString().trim();
                    if (TextUtils.isEmpty(password)){
                        ToastUtils.showShort("请输入密码");
                        return;
                    }
                    if (!password.equals(date.getPassword())){
                        ToastUtils.showShort("密码不正确，请重新输入");
                        return;
                    }
                    HomeActivity.startHome(this);
                    finish();
                }

                break;
            case R.id.tv_forgot_password:
                SetPasswordActivity.startSetPassword(this, Constants.EDITOR);
                break;
        }
    }
}
