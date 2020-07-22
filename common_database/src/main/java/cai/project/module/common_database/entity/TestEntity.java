package cai.project.module.common_database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 数据库测试表
 * User: admin
 * Date: 2020/7/22
 * Time: 10:31
 */
@Entity
public class TestEntity {
    private Long id;

    @Generated(hash = 2079041192)
    public TestEntity(Long id) {
        this.id = id;
    }

    @Generated(hash = 1020448049)
    public TestEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
