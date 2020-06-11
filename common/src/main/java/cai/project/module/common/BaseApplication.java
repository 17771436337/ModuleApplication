package cai.project.module.common;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import cai.project.module.common_utils.codeutils.Utils;

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
    }
}
