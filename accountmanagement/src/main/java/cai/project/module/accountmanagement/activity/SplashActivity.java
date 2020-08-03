package cai.project.module.accountmanagement.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.R;
import cai.project.module.common.BaseActivity;
import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_database.entity.ApplyEntity;
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_mvp.presenter.BasePresenter;
import cai.project.module.common_utils.codeutils.AppUtils;
import cai.project.module.common_utils.codeutils.EncryptUtils;
import cai.project.module.common_utils.codeutils.FileIOUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

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
    protected void getIntents(Intent intent) { }

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

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.accountmanagement_activity_splash;
    }

    @Override
    public void initData() {
        if (date != null) {
            tvPasswordHint.setText(date.getPasswordHint());
        }

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
            RxPermissions mRxPermissions = new RxPermissions(this);
            mRxPermissions.requestEachCombined( Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(Permission permission) throws Exception {
                    if (permission.granted){ // 用户已经同意该权限
                        new LFilePicker()
                                .withActivity(SplashActivity.this)
                                .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                                .withStartPath(Environment.getExternalStorageDirectory().getAbsolutePath())//指定初始显示路径
                                .withIsGreater(false)//过滤文件大小 小于指定大小的文件
                                .withFileSize(500 * 1024)//指定文件大小为500K
                                .withTitle("备份文件选择")
                                .withChooseMode(true)//选择文件夹还是文件  true选择文件
                                .withBackgroundColor("#008577")
                                .withMutilyMode(false)//单选还是多选
                                .start();
                    }else{ // 用户拒绝了该权限，并且选中『不再询问』
                        ToastUtils.showShort("请开启权限");
                    }
                }
            }).dispose();



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
                  byte[] bytes1 = EncryptUtils.decryptAES(bytes,Constants.AES_KEY.getBytes(),"AES/CBC/PKCS5Padding",Constants.AES_IV.getBytes());
                 String json =  new String( bytes1);
                 if (json != null) {
                     AccountEntity[] listA = new Gson().fromJson(json, AccountEntity[].class);
                     DaoUtils.getAccountDao().addAccounts(Arrays.asList(listA));
                     ToastUtils.showShort("数据备份完成");
                 }
                }


//                Toast.makeText(getApplicationContext(), "选中了" + list.size() + "个文件", Toast.LENGTH_SHORT).show();
//                //如果是文件夹选择模式，需要获取选择的文件夹路径
//                String path = data.getStringExtra("path");
//                Toast.makeText(getApplicationContext(), "选中的路径为" + path, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
