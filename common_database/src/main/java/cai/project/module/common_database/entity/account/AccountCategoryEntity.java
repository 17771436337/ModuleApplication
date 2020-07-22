package cai.project.module.common_database.entity.account;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 账号对应的类别
 * User: admin
 * Date: 2020/7/22
 * Time: 10:59
 */
@Entity
public class AccountCategoryEntity {

    //定于主键
    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String name;//账号类别名称

    private int level;//类别等级

    @Generated(hash = 2012613929)
    public AccountCategoryEntity(Long id, @NotNull String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    @Generated(hash = 1497149184)
    public AccountCategoryEntity() {
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

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
