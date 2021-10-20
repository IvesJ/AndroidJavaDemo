package com.acecoder.datastore.greendao.utils;

import com.acecoder.datastore.greendao.db.DaoSession;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class CommonDaoUtils<T> {
    private DaoSession mDaoSession;
    private Class<T> entityClass;
    private AbstractDao<T, Long> entityDao;

    public CommonDaoUtils(Class<T> entityClass, AbstractDao<T, Long> entityDao) {
        DaoManager daoManager = DaoManager.getInstance();
        mDaoSession = daoManager.getDaoSession();
        this.entityClass = entityClass;
        this.entityDao = entityDao;
    }

    /**
     * 插入记录，如果未创建，先创建表
     */
    public boolean insert(T entity) {
        return entityDao.insert(entity) != -1;
    }

    /**
     * 插入多条数据，在子线程操作
     */
    public boolean insertMultiple(final List<T> entityList) {
        try {
            mDaoSession.runInTx(() -> {
                for (T entity : entityList) {
                    mDaoSession.insertOrReplace(entity);
                }
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 修改一条数据
     */
    public boolean update(T entity) {
        try {
            mDaoSession.update(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除单条记录
     */
    public boolean delete(T entity) {
        try {
            //按照 id 删除
            mDaoSession.delete(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除所有记录
     */
    public boolean deleteAll() {
        try {
            //按照 id 删除
            mDaoSession.deleteAll(entityClass);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 查询所有记录
     */
    public List<T> queryAll() {
        return mDaoSession.loadAll(entityClass);
    }

    /**
     * 根据主键 id 查询记录
     */
    public T queryById(long key) {
        return mDaoSession.load(entityClass, key);
    }

    /**
     * 使用 native sql 进行查询操作
     */
    public List<T> queryByNativeSql(String sql, String[] conditions) {
        return mDaoSession.queryRaw(entityClass, sql, conditions);
    }

    /**
     * 使用 queryBuilder进行查询
     */
    public List<T> queryQueryBuilder(WhereCondition condition, WhereCondition... conditions) {
        QueryBuilder<T> queryBuilder = mDaoSession.queryBuilder(entityClass);
        return queryBuilder.where(condition, conditions).list();
    }

}
