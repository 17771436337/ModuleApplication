package cai.project.module.common_database;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import cai.project.module.common_database.dao.AccountDao;
import cai.project.module.common_database.dao.ApplyDao;

public class DaoUtils {

    @SuppressLint("StaticFieldLeak")
    private static Application sApp;


    private static AccountDao accountDao;

    private static ApplyDao applyDao;

    private DaoUtils() {
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





    /**
     * 单列模式获取AccountDao对象
     * @return
     */
    public static AccountDao getAccountDao(){
        if (accountDao == null) {
            accountDao = new AccountDao(sApp);
        }
        return accountDao;
    }

    /**
     * 单列模式获取ApplyDao对象
     * @return
     */
    public static ApplyDao getApplyDao(){
        if (applyDao == null) {
            applyDao = new ApplyDao(sApp);
        }
        return applyDao;
    }
}
