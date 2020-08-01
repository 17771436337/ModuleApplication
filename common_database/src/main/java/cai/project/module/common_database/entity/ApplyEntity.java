package cai.project.module.common_database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ApplyEntity {

    @Id(autoincrement = true)
    private Long id;

    private int type;//应用类别

    private String packageName;//应用对应的包名

    private String password;//应用进入密码

    private String passwordHint;//应用密码提示

    @Generated(hash = 1454825201)
    public ApplyEntity(Long id, int type, String packageName, String password,
            String passwordHint) {
        this.id = id;
        this.type = type;
        this.packageName = packageName;
        this.password = password;
        this.passwordHint = passwordHint;
    }

    @Generated(hash = 385215819)
    public ApplyEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHint() {
        return this.passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }
}
