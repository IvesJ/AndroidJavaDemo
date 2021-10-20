package com.acecoder.datastore.greendao.utils;

import com.acecoder.datastore.bean.User;
import com.acecoder.datastore.greendao.db.UserDao;

/**
 * 初始化、存放及获取DaoUtils
 */
public class DaoUtilsStore {
    private volatile static DaoUtilsStore instance = new DaoUtilsStore();
    private CommonDaoUtils<User> mUserDaoUtils;

    public static DaoUtilsStore getInstance() {
        return instance;
    }

    private DaoUtilsStore() {
        DaoManager daoManager = DaoManager.getInstance();
        UserDao _UserDao = daoManager.getDaoSession().getUserDao();
        mUserDaoUtils = new CommonDaoUtils<>(User.class, _UserDao);
    }

    public CommonDaoUtils<User> getUserDaoUtils() {
        return mUserDaoUtils;
    }
}
