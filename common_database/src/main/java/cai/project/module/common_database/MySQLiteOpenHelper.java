package cai.project.module.common_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import cai.project.module.common_database.gen.AccountCategoryEntityDao;
import cai.project.module.common_database.gen.AccountEntityDao;
import cai.project.module.common_database.gen.AccountMessageEntityDao;
import cai.project.module.common_database.gen.DaoMaster;

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper{
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //把需要管理的数据库表DAO作为最后一个参数传入到方法中
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        } , AccountEntityDao.class, AccountMessageEntityDao.class, AccountCategoryEntityDao.class);
    }

}
