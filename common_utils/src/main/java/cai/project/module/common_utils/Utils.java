package cai.project.module.common_utils;

import android.app.Application;

public final class Utils {

    /**基础的环境*/
    private  Application application;

    public Application getApplication() {
        return application;
    }

    private static Utils instance;

    private Utils() { }

    public static Utils getInstance(){
        if (instance == null){
            instance = new Utils();
        }
        return instance;
    }




    /**
     * 初始化基础环境变量
     * @param application
     */
    public void init(Application application){ this.application = application; }

}
