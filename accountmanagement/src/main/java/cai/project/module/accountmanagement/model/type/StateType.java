package cai.project.module.accountmanagement.model.type;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cai.project.module.accountmanagement.Constants;

@Documented // 表示开启Doc文档
@Target({
        ElementType.PARAMETER,
        ElementType.FIELD,
        ElementType.METHOD,
}) //表示注解作用范围，参数注解( ElementType.PARAMETER)，成员注解( ElementType.FIELD)，方法注解(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@IntDef({Constants.EDITOR,Constants.ADD,Constants.CHECK})
public @interface StateType {
}
