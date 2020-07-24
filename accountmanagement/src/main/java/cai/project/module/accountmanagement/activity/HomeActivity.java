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
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.R;
import cai.project.module.accountmanagement.adapter.HomeAdapter;
import cai.project.module.common.adapter.SpacesItemDecoration;
import cai.project.module.common.adapter.helper.WeSwipe;
import cai.project.module.common.adapter.helper.WeSwipeHelper;
import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_utils.codeutils.EncryptUtils;
import cai.project.module.common_utils.codeutils.FileIOUtils;
import cai.project.module.common_utils.codeutils.FileUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;
import io.reactivex.functions.Consumer;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    HomeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmanagement_activity_home);
        ButterKnife.bind(this);
        initData();
        initListener();
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

    private void initData() {
        adapter  = new HomeAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(30));
        recyclerView.setAdapter(adapter);
        //设置WeSwipe。
        WeSwipe.attach(recyclerView).setType(WeSwipeHelper.SWIPE_ITEM_TYPE_FLOWING).setEnable(true);
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

            String json = new Gson().toJson( DaoUtils.getAccountDao().getAccountList());
         boolean is=   FileIOUtils.writeFileFromBytesByChannel(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/aimusic/备份.txt",
                 json.getBytes(),true);
            FileIOUtils.writeFileFromBytesByChannel(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/aimusic/备份2.txt",
                    EncryptUtils.encryptHmacMD5( json.getBytes(),new byte[]{15}),true);

            if (is){
                ToastUtils.showShort("点击了备份");
            }else{
                ToastUtils.showShort("保存文件失败");
            }
        }
    }




    public static void startHome(Context context){
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }
}
