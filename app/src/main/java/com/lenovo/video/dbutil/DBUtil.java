package com.lenovo.video.dbutil;

import android.database.sqlite.SQLiteDatabase;

import com.lenovo.video.app.MyApplication;

/**
 * Created by wanggl8 on 2018/2/24.
 */

public class DBUtil {

    private DaoMaster.DevOpenHelper dHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private static DBUtil dbUtil;

    public static DBUtil getDBInstance() {
        if (dbUtil == null) {
            dbUtil = new DBUtil();
        }
        return dbUtil;
    }


    private DBUtil() {
        initDatabase();
    }


    private void initDatabase() {
        dHelper = new DaoMaster.DevOpenHelper(MyApplication.getInstants(), "SearchHistory-db", null);
        db = dHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
