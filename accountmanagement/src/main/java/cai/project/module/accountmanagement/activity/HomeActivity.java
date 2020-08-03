package cai.project.module.accountmanagement.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

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
import cai.project.module.common_utils.codeutils.SizeUtils;
import cai.project.module.common_utils.codeutils.TimeUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;
import io.reactivex.functions.Consumer;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.iv_add)
    ImageView ivAdd;
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView recyclerView;

    HomeAdapter adapter;

    int REQUESTCODE_FROM_ACTIVITY = 1000;


    @Override
    protected void getIntents(Intent intent) {

    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }


    @Override
    public int getLayoutId() {
        return R.layout.accountmanagement_activity_home;
    }

    public void initData() {
        adapter  = new HomeAdapter(this,R.layout.accountmanagement_item_home_message);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
           /* SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
            // 各种文字和图标属性设置。
            leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。*/
                // 在Item右侧添加一个菜单。
                // 1.编辑
                // 各种文字和图标属性设置。
                SwipeMenuItem modifyItem = new SwipeMenuItem(HomeActivity.this)
                        .setBackgroundColor(getResources().getColor(R.color.delete))
                        .setText("编辑")
                        .setTextColor(Color.WHITE)
                        .setTextSize(14) // 文字大小。
                        .setWidth(SizeUtils.dp2px(80))
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                rightMenu.addMenuItem(modifyItem);
                // 2 删除
                SwipeMenuItem deleteItem = new SwipeMenuItem(HomeActivity.this);
                deleteItem.setText("删除")
                        .setBackgroundColor(getResources().getColor(R.color.lable))
                        .setTextColor(Color.WHITE) // 文字颜色。
                        .setTextSize(14) // 文字大小。
                        .setWidth(SizeUtils.dp2px(80))
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

                rightMenu.addMenuItem(deleteItem);

                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };
        // 设置监听器。
        recyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                AccountEntity data = adapter.getData(adapterPosition);
                if (menuPosition == 0) {
                    AddAccountActivity.startAccountMessage(HomeActivity.this, Constants.EDITOR,data.getId());
                } else {
                    ToastUtils.showShort("删除");
                    DaoUtils.getAccountDao().deleteAccount(data.getId());
                    if (adapter != null){
                        adapter.setData(DaoUtils.getAccountDao().getAccountList());
                    }

                }
            }
        };

        // 菜单点击监听。
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);
        recyclerView.addItemDecoration(new SpacesItemDecoration(30));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null){
            adapter.setData(DaoUtils.getAccountDao().getAccountList());
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
