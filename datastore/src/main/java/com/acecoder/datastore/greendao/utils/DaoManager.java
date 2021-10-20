package com.acecoder.datastore.greendao.utils;

import android.app.Application;

import com.acecoder.datastore.greendao.db.DaoMaster;
import com.acecoder.datastore.greendao.db.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

public class DaoManager {

    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "RECORD_DB";

    private Application mApplication;

    private volatile static DaoManager manager = new DaoManager();
    private DaoMaster mDaoMaster;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoSession mDaoSession;

    public static DaoManager getInstance() {
        return manager;
    }

    private DaoManager() {
        setDebug();
    }

    public void init(Application application) {
        this.mApplication = application;
    }

    /**
     * 判断是否存在数据库，如果没有则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mApplication, DB_NAME, null);
            mDaoMaster = new DaoMaster(helper.getWritableDb());
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     */
    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 打开输出日志，默认关闭
     */
    public void setDebug() {
//        if (BuildConfig.DEBUG) {
//            QueryBuilder.LOG_SQL = true;
//            QueryBuilder.LOG_VALUES = true;
//        }
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有操作，数据库开启后，使用完毕需要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    public void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }
}

