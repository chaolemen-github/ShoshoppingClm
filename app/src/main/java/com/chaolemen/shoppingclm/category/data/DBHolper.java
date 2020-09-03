package com.chaolemen.shoppingclm.category.data;

import com.chaolemen.shoppingclm.app.BaseApp;
import com.chaolemen.shoppingclm.category.bean.DBBean;
import com.chaolemen.shoppingclm.db.DBBeanDao;
import com.chaolemen.shoppingclm.db.DaoMaster;
import com.chaolemen.shoppingclm.db.DaoSession;

import java.util.List;

public class DBHolper {
    private static volatile DBHolper ourInstance;
    private final DBBeanDao dbBeanDao;

    public static DBHolper getInstance() {
        if (ourInstance==null){
            synchronized (DBHolper.class){
                if (ourInstance==null){
                    ourInstance=new DBHolper();
                }
            }
        }
        return ourInstance;
    }

    private DBHolper() {

        //创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getApp(), "shopping.db");
        //获取数据库的读取权限
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //获取管理器
        DaoSession daoSession = daoMaster.newSession();
        //获取表对象
        dbBeanDao = daoSession.getDBBeanDao();
    }

    //添加一条数据
    public void insert(DBBean dbBean){
        dbBeanDao.insertOrReplace(dbBean);
    }
    //删除一条数据
    public void delete(DBBean dbBean){
        dbBeanDao.delete(dbBean);
    }
    //查询所有
    public List<DBBean> queryAll(){
        List<DBBean> dbBeans = dbBeanDao.loadAll();
        return dbBeans;
    }
}
