package cai.project.module.common_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DaoUtils {

    public  static Context context;


    private static SQLiteDatabase sqLiteDatabase;

    public static void init(Context context){
        DaoUtils.context = context.getApplicationContext();
    }





}
