package cai.project.module.common_database.dao;

import android.content.Context;

import java.util.List;

import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_database.entity.account.AccountMessageEntity;
import cai.project.module.common_database.gen.AccountEntityDao;
import cai.project.module.common_database.gen.AccountMessageEntityDao;

/**
 * 数据逻辑
 * User: admin
 * Date: 2020/7/22
 * Time: 11:23
 */
public class AccountDao extends BaseDao<AccountEntity>{
    public AccountDao(Context context) {
        super(context);
    }

    /**添加账号*/
    public Long addAccount(String account){
        AccountEntity entity = new AccountEntity();
        entity.setId(null);
        entity.setAccount(account);
        entity.setTime(System.currentTimeMillis());

        AccountEntityDao dao = daoSession.getAccountEntityDao();
       return dao.insert(entity);
    }

    /**批量添加账号*/
    public void addAccounts(List<AccountEntity> data){
        AccountEntityDao dao = daoSession.getAccountEntityDao();
        for (AccountEntity temp:data) {
            AccountEntity b =  dao.queryBuilder().where(AccountEntityDao.Properties.Id.eq(temp.getId())).build().unique();
         if (b == null) {
             Long id = dao.insert(temp);
             int SORTNO = 1;
             for (AccountMessageEntity temp2 : temp.getAccountMessages()) {
                 addAccountMessage(id, temp2.getName(), temp2.getDetail(), SORTNO++);
             }
         }
        }
    }

    /**添加账号信息*/
    public void addAccountMessage(Long accountId,String title,String content,int SORTNO){
        AccountMessageEntity entity = new AccountMessageEntity(null,title,content,accountId,SORTNO);
        AccountMessageEntityDao dao = daoSession.getAccountMessageEntityDao();
        dao.insertOrReplace(entity);
    }


    //----------------------------------

    public void editAccount(Long id,String account){
        AccountEntityDao dao = daoSession.getAccountEntityDao();

        AccountEntity entity = dao.load(id);
        if (entity != null){
            entity.setId(id);
            entity.setAccount(account);
            entity.setTime(System.currentTimeMillis());
            dao.update(entity);

            //删除账号之前的信息，防止信息删除还会显示
            AccountMessageEntityDao messageDao = daoSession.getAccountMessageEntityDao();
            List<AccountMessageEntity> accountMessageEntities =  messageDao.queryBuilder().where(AccountMessageEntityDao.Properties.AccountId.eq(id)).orderAsc(AccountMessageEntityDao.Properties.SORTNO).build().list();
            for (AccountMessageEntity e:accountMessageEntities) {
                messageDao.delete(e);
            }

            daoSession.clear();

        }
    }

    public void editAccountMessage(Long id,Long accountId,String title,String content,int SORTNO){
        AccountMessageEntityDao dao = daoSession.getAccountMessageEntityDao();
        AccountMessageEntity entity = dao.load(id);
        if (entity == null){
            addAccountMessage(accountId,title,content,SORTNO);
        }else{
            entity = new AccountMessageEntity(id,title,content,accountId,SORTNO);
            dao.update(entity);
        }
        daoSession.clear();
    }


    //---------------------------------

    /**获取所有账号列表*/
    public List<AccountEntity> getAccountList(){
        AccountEntityDao dao = daoSession.getAccountEntityDao();
        return dao.loadAll();
    }

    /**获取当前的账号信息*/
    public AccountEntity getAccount(Long id){
        AccountEntityDao dao = daoSession.getAccountEntityDao();
        dao.detachAll();
        return dao.load(id);
    }

    public List<AccountMessageEntity> getAccountMessageList(Long id){
        AccountMessageEntityDao dao = daoSession.getAccountMessageEntityDao();
       return dao.queryBuilder().where(AccountMessageEntityDao.Properties.AccountId.eq(id)).orderAsc(AccountMessageEntityDao.Properties.SORTNO).build().list();
    }

    /**删除账号*/
    public void deleteAccount(Long id) {
        AccountEntityDao dao = daoSession.getAccountEntityDao();
        AccountMessageEntityDao messageDao = daoSession.getAccountMessageEntityDao();
        AccountEntity entity = dao.load(id);
        entity.resetAccountMessages();
        for (AccountMessageEntity temp:entity.getAccountMessages()) {
            messageDao.delete(temp);
        }

        dao.deleteByKey(id);

    }
}
