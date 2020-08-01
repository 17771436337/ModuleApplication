package cai.project.module.common_database.dao;

import android.content.Context;

import cai.project.module.common_database.entity.ApplyEntity;
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_database.gen.ApplyEntityDao;

public class ApplyDao extends BaseDao<ApplyEntity>{
    public ApplyDao(Context context) {
        super(context);
    }

    /**添加应用信息*/
    public void addApply(String packageName,int type,String password,String passwordHint){
        ApplyEntity entity = new ApplyEntity(null,type,packageName,password,passwordHint);
        insertObject(entity);
    }


    /**修改应用信息*/
    public void editApply(Long id,String packageName,int type,String password,String passwordHint){
        ApplyEntity entity = new ApplyEntity(id,type,packageName,password,passwordHint);
        updateObject(entity);
    }


    /**获取引用信息*/
    public ApplyEntity getApply(String packageName){
        ApplyEntityDao dao = daoSession.getApplyEntityDao();
       return dao.queryBuilder().where(ApplyEntityDao.Properties.PackageName.eq(packageName)).build().unique();
    }
}
