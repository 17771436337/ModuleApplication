package cai.project.module.moduleapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.common_arouter.GamePath;
import cai.project.module.common_arouter.HomePath;
import cai.project.module.common_arouter.UserPath;

public class MainActivity extends AppCompatActivity {
    private final String TAG ="获取包名";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



//        PackageManager packageManager = getPackageManager();
//        int flag = PackageManager.GET_UNINSTALLED_PACKAGES;
///*
//GET_UNINSTALLED_PACKAGES 这个常数在API级别24中被弃用。用MATCH_UNINSTALLED_PACKAGES替换
// */
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            flag = PackageManager.MATCH_UNINSTALLED_PACKAGES;
//        }
//        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(flag);
//        for (PackageInfo installedPackage : installedPackages) {
//            Log.i(TAG, "----------packagename = " + installedPackage.packageName);
//        }

    }



    @OnClick({R.id.bt_home, R.id.bt_user,R.id.bt_game2048,R.id.bt_game_teris})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_home:
                ARouter.getInstance().build(HomePath.ACTIVITY_MAIN).navigation();
                break;
            case R.id.bt_user:
                ARouter.getInstance().build(UserPath.ACTIVITY_MAIN).navigation();
                break;
            case R.id.bt_game2048:
                ARouter.getInstance().build(GamePath.GAME_2018).navigation();
                break;
            case R.id.bt_game_teris:
                ARouter.getInstance().build(GamePath.GAME_TERIS).navigation();
                break;
        }
    }
}
