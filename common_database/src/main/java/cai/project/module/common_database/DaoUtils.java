package cai.project.module.common_database;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DaoUtils {

    @SuppressLint("StaticFieldLeak")
    private static Application sApp;

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








}
