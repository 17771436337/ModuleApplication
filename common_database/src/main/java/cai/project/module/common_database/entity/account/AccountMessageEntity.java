package cai.project.module.common_database.entity.account;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 账号信息表
 * User: admin
 * Date: 2020/7/22
 * Time: 11:02
 */
@Entity
public class AccountMessageEntity {
    //定于主键
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String name;//账号信息的名称

    @NotNull
    private String detail;//账号信息的详情

    @Property(nameInDb = "ACCOUNT_ID")
    @NotNull
    private Long accountId;//账号ID


    @NotNull
    @Property(nameInDb = "SORTNO")
    private int SORTNO;//排序，防止错乱


    @Generated(hash = 1743915778)
    public AccountMessageEntity(Long id, @NotNull String name,
            @NotNull String detail, @NotNull Long accountId, int SORTNO) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.accountId = accountId;
        this.SORTNO = SORTNO;
    }


    @Generated(hash = 1875074060)
    public AccountMessageEntity() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDetail() {
        return this.detail;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }


    public Long getAccountId() {
        return this.accountId;
    }


    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }


    public int getSORTNO() {
        return this.SORTNO;
    }


    public void setSORTNO(int SORTNO) {
        this.SORTNO = SORTNO;
    }

    @Override
    public String toString() {
        return "AccountMessageEntity{" +
                "name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", accountId=" + accountId +
                ", SORTNO=" + SORTNO +
                '}';
    }
}
