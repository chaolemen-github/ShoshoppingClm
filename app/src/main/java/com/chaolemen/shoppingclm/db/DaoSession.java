package com.chaolemen.shoppingclm.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.chaolemen.shoppingclm.category.bean.DBBean;

import com.chaolemen.shoppingclm.db.DBBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dBBeanDaoConfig;

    private final DBBeanDao dBBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dBBeanDaoConfig = daoConfigMap.get(DBBeanDao.class).clone();
        dBBeanDaoConfig.initIdentityScope(type);

        dBBeanDao = new DBBeanDao(dBBeanDaoConfig, this);

        registerDao(DBBean.class, dBBeanDao);
    }
    
    public void clear() {
        dBBeanDaoConfig.clearIdentityScope();
    }

    public DBBeanDao getDBBeanDao() {
        return dBBeanDao;
    }

}
