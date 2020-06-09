package cai.project.module.moduleapplication;

import cai.project.module.common.BaseApplication;
import cai.project.module.web.QCInit;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        QCInit.getInstance().init(this);
    }
}
