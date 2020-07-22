package cai.project.module.common_database;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

/**
 * Created by Android Studio.
 * User: admin
 * Date: 2020/7/22
 * Time: 10:50
 */
public final class BaseDataInit {
    @SuppressLint("StaticFieldLeak")
    private static Application sApp;

    private BaseDataInit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * Init utils.
     * <p>Init it in the class of UtilsFileProvider.</p>
     *
     * @param app application
     */
    public static void init(final Application app) {
        if (app == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        if (sApp == null) {
            sApp = app;
            return;
        }
        initDB();

    }

    private static void initDB(){
        DaoManager mDatabase = new DaoManager();
        mDatabase.init(sApp.getApplicationContext());
        mDatabase.getDaoMaster();
        mDatabase.setDebug(true);
    }
}
