package cai.project.module.accountmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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
        adapter  = new HomeAdapter(DaoUtils.getAccountDao().getAccountList());
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

    @OnClick({R.id.iv_add})
    public void onClick(View view) {
        int id = view.getId();
        if (id ==  R.id.iv_add){
            AddAccountActivity.startAccountMessage(this, Constants.ADD,0L);
        }
    }




    public static void startHome(Context context){
        Intent intent = new Intent(context,HomeActivity.class);
        context.startActivity(intent);
    }
}
