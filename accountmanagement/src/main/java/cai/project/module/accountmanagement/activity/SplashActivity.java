package cai.project.module.accountmanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.leon.lfilepickerlibrary.LFilePicker;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.FFileUtils;
import cai.project.module.accountmanagement.R;
import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_database.entity.ApplyEntity;
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_utils.codeutils.AppUtils;
import cai.project.module.common_utils.codeutils.FileIOUtils;
import cai.project.module.common_utils.codeutils.FileUtils;
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

    int REQUESTCODE_FROM_ACTIVITY = 1000;
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

    @OnClick({R.id.bt_confirm, R.id.tv_forgot_password,R.id.tv_restore})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_confirm){
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
        }else if(id == R.id.tv_forgot_password){//忘记密码
            SetPasswordActivity.startSetPassword(this, Constants.EDITOR);
        }else if (id == R.id.tv_restore){//恢复备份

//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            }
//            intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
//            startActivityForResult(intent,1);

            new LFilePicker()
                    .withActivity(this)
                    .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                    .withStartPath(Environment.getExternalStorageDirectory().getAbsolutePath())//指定初始显示路径
                    .withIsGreater(false)//过滤文件大小 小于指定大小的文件
                    .withFileSize(500 * 1024)//指定文件大小为500K
                    .withTitle("备份文件选择")
                    .withBackgroundColor("#111111")
                    .withMutilyMode(false)//单选还是多选
                    .start();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                //如果是文件选择模式，需要获取选择的所有文件的路径集合
                //List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);//Constant.RESULT_INFO == "paths"
                List<String> list = data.getStringArrayListExtra("paths");
                if (list != null && list.size() > 0){
                  byte[] bytes =  FileIOUtils.readFile2BytesByStream(list.get(0));

                 String json =  new String(bytes);
               AccountEntity[] listA = new Gson().fromJson(json, AccountEntity[].class);
               DaoUtils.getAccountDao().addAccounts(Arrays.asList(listA));

               ToastUtils.showShort("数据备份完成");

//                    Toast.makeText(getApplicationContext(), "选中了" + listA.length + "个文件", Toast.LENGTH_SHORT).show();
                }


//                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
//                //如果是文件夹选择模式，需要获取选择的文件夹路径
//                String path = data.getStringExtra("path");
//                Toast.makeText(getApplicationContext(), "选中的路径为" + path, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
