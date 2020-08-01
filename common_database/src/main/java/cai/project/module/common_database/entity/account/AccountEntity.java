package cai.project.module.common_database.entity.account;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import cai.project.module.common_database.gen.DaoSession;
import cai.project.module.common_database.gen.AccountMessageEntityDao;
import cai.project.module.common_database.gen.AccountCategoryEntityDao;
import cai.project.module.common_database.gen.AccountEntityDao;

/**
 * Created by Android Studio.
 * User: admin
 * Date: 2020/7/22
 * Time: 10:55
 */
@Entity
public class AccountEntity {
    //定于主键
    @Id(autoincrement = true)
    private Long id;

    //定于此字段在数据库列的别名，如果不定义，会默认定义为CUSTOM_NAME
    @Property(nameInDb = "ACCOUNT")
    @NotNull
    private String account;

    @NotNull
    private long time;//账号对应的时间

    private Long classesId;//分类Id

    @ToOne(joinProperty = "classesId")
    private AccountCategoryEntity accountCategory;

    @ToMany(referencedJoinProperty = "accountId")
    @OrderBy("SORTNO ASC")//按照排序码
    private List<AccountMessageEntity> accountMessages;//一个账号对应的多条信息

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1708931600)
    private transient AccountEntityDao myDao;

    @Generated(hash = 444441095)
    public AccountEntity(Long id, @NotNull String account, long time,
            Long classesId) {
        this.id = id;
        this.account = account;
        this.time = time;
        this.classesId = classesId;
    }

    @Generated(hash = 40307897)
    public AccountEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Long getClassesId() {
        return this.classesId;
    }

    public void setClassesId(Long classesId) {
        this.classesId = classesId;
    }

    @Generated(hash = 1177076474)
    private transient Long accountCategory__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 716732455)
    public AccountCategoryEntity getAccountCategory() {
        Long __key = this.classesId;
        if (accountCategory__resolvedKey == null
                || !accountCategory__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AccountCategoryEntityDao targetDao = daoSession
                    .getAccountCategoryEntityDao();
            AccountCategoryEntity accountCategoryNew = targetDao.load(__key);
            synchronized (this) {
                accountCategory = accountCategoryNew;
                accountCategory__resolvedKey = __key;
            }
        }
        return accountCategory;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 447986938)
    public void setAccountCategory(AccountCategoryEntity accountCategory) {
        synchronized (this) {
            this.accountCategory = accountCategory;
            classesId = accountCategory == null ? null : accountCategory.getId();
            accountCategory__resolvedKey = classesId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 323550298)
    public List<AccountMessageEntity> getAccountMessages() {
        if (accountMessages == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AccountMessageEntityDao targetDao = daoSession
                    .getAccountMessageEntityDao();
            List<AccountMessageEntity> accountMessagesNew = targetDao
                    ._queryAccountEntity_AccountMessages(id);
            synchronized (this) {
                if (accountMessages == null) {
                    accountMessages = accountMessagesNew;
                }
            }
        }
        return accountMessages;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1766877619)
    public synchronized void resetAccountMessages() {
        accountMessages = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 442337859)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAccountEntityDao() : null;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "account='" + account + '\'' +
                ", time=" + time +
                ", classesId=" + classesId +
                ", accountCategory=" + accountCategory +
                ", accountMessages=" + accountMessages +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", accountCategory__resolvedKey=" + accountCategory__resolvedKey +
                '}';
    }
}
