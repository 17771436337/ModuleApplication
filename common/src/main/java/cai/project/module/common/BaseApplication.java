package cai.project.module.common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_httplibrary.OkGoHttpUtil;
import cai.project.module.common_utils.codeutils.Utils;
import cai.project.module.common_view.LinNotify;

public class BaseApplication extends Application {

    private boolean isDebugARouter = true;

    @Override
    public void onCreate() {
        super.onCreate();

        if (isDebugARouter) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        Utils.init(this);

        //通知栏初始化（适配8.0）
        LinNotify.setNotificationChannel(this);

        DaoUtils.init(this);


        OkGoHttpUtil.init(this);
    }
}
