package cai.project.module.common_database.dao;

import android.content.Context;

import cai.project.module.common_database.entity.account.AccountEntity;

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
}
