package cai.project.module.user.login;

import cai.project.module.common_mvp.BaseView;

public interface LoginView extends BaseView {

    /**用户名*/
   String getUserName();
   /**密码*/
   String getPassWord();
}
