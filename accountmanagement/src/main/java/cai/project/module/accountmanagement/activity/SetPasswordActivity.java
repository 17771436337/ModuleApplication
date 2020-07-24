package cai.project.module.accountmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.R;
import cai.project.module.accountmanagement.model.type.StateType;
import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_database.entity.ApplyEntity;
import cai.project.module.common_utils.codeutils.AppUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;

public class SetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.et_password_hint)
    EditText etPasswordHint;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    //--------------
    private @StateType
    int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmanagement_activity_set_password);
        ButterKnife.bind(this);
        getIntents(getIntent());
        initData();

    }

    private void initData() {
        switch (type) {
            case Constants.EDITOR:
                ivBack.setVisibility(View.VISIBLE);
                tvTitle.setText("修改密码");
                break;
            case Constants.ADD:
                ivBack.setVisibility(View.GONE);
                tvTitle.setText("初始化设置应用密码");
                break;
        }
    }


    @OnClick({R.id.save,R.id.iv_back})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.save) {
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();
            String passwordHint = etPasswordHint.getText().toString().trim();

            if (TextUtils.isEmpty(password)) {
                ToastUtils.showShort("请输入密码");
                return;
            }

            if (TextUtils.isEmpty(confirmPassword)) {
                ToastUtils.showShort("请输入确认密码");
                return;
            }
            if (TextUtils.isEmpty(passwordHint)) {
                ToastUtils.showShort("请输入密码提示");
                return;
            }

            if (!password.equals(confirmPassword)) {
                ToastUtils.showShort("两次输入密码不一致，请确认后再次输入");
                return;
            }

            String packageName = AppUtils.getAppPackageName();
            if (type == Constants.ADD) {
                DaoUtils.getApplyDao().addApply(packageName, 1, password, passwordHint);
            } else {
                ApplyEntity entity = DaoUtils.getApplyDao().getApply(packageName);
                DaoUtils.getApplyDao().editApply(entity.getId(), packageName, 1, password, passwordHint);
            }
            finish();
        }else if (id == R.id.iv_back){
            finish();
        }
    }

    public static void startSetPassword(Context context, @StateType int type) {
        Intent intent = new Intent(context, SetPasswordActivity.class);
        intent.putExtra("TYPE", type);
        context.startActivity(intent);
    }

    private void getIntents(Intent intent) {
        type = intent.getIntExtra("TYPE", Constants.ADD);
    }


    @Override
    public void onBackPressed() {
        if (type == Constants.ADD) {
           AppUtils.exitApp();
        } else {
             finish();
        }
    }
}
