package cai.project.module.accountmanagement.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.R;
import cai.project.module.accountmanagement.activity.account_editor.AddAccountActivity;
import cai.project.module.accountmanagement.adapter.HomeAdapter;
import cai.project.module.common.BaseActivity;
import cai.project.module.common.adapter.SpacesItemDecoration;
import cai.project.module.common.adapter.helper.WeSwipe;
import cai.project.module.common.adapter.helper.WeSwipeHelper;
import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_mvp.presenter.BasePresenter;
import cai.project.module.common_utils.codeutils.EncryptUtils;
import cai.project.module.common_utils.codeutils.FileIOUtils;
import cai.project.module.common_utils.codeutils.TimeUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;
import io.reactivex.functions.Consumer;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    HomeAdapter adapter;

    int REQUESTCODE_FROM_ACTIVITY = 1000;


    @Override
    protected void getIntents(Intent intent) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }

    private void initListener() {
        if (adapter != null){
            adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener<AccountEntity>() {
                @Override
                public void onClick(View v, int pos, AccountEntity data) {
                    AddAccountActivity.startAccountMessage(HomeActivity.this, Constants.EDITOR,data.getId());
                }

                @Override
                public void onDelete(View v, int pos, AccountEntity data) {

                    DaoUtils.getAccountDao().deleteAccount(data.getId());
                    if (adapter != null){
                            adapter.checkData(DaoUtils.getAccountDao().getAccountList());
                    }
                }
            });
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.accountmanagement_activity_home;
    }

    public void initData() {
        adapter  = new HomeAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(30));
        recyclerView.setAdapter(adapter);
        //设置WeSwipe。
        WeSwipe.attach(recyclerView).setType(WeSwipeHelper.SWIPE_ITEM_TYPE_FLOWING).setEnable(true);

        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null){
            adapter.checkData(DaoUtils.getAccountDao().getAccountList());
        }
    }



    @OnClick({R.id.iv_add ,R.id.tv})
    public void onClick(View view) {
        int id = view.getId();
        if (id ==  R.id.iv_add){
            AddAccountActivity.startAccountMessage(this, Constants.ADD,0L);
        }else if (id == R.id.tv){
            RxPermissions mRxPermissions = new RxPermissions(this);

            mRxPermissions.requestEachCombined( Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted){ // 用户已经同意该权限
                                new LFilePicker()
                                        .withActivity(HomeActivity.this)
                                        .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                                        .withStartPath(Environment.getExternalStorageDirectory().getAbsolutePath())//指定初始显示路径
                                        .withIsGreater(false)//过滤文件大小 小于指定大小的文件
                                        .withFileSize(500 * 1024)//指定文件大小为500K
                                        .withTitle("备份地址选择")
                                        .withChooseMode(false)//选择文件夹还是文件  true选择文件
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
//                //如果是文件夹选择模式，需要获取选择的文件夹路径
                String path = data.getStringExtra("path");
                String json = new Gson().toJson( DaoUtils.getAccountDao().getAccountList());
                String filePath = path+ File.separator+"备份_"+ TimeUtils.getNowString() +".cai";
                boolean is =  FileIOUtils.writeFileFromBytesByChannel(
                        filePath,
                        EncryptUtils.encryptAES(json.getBytes(),Constants.AES_KEY.getBytes(),"AES/CBC/PKCS5Padding",Constants.AES_IV.getBytes()),
                        false);
                if (is){
                    ToastUtils.showShort("备份完成:"+filePath);
                }else{
                    ToastUtils.showShort("备份失败");
                }
//                Toast.makeText(getApplicationContext(), "选中的路径为" + path, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static void startHome(Context context){
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }
}
