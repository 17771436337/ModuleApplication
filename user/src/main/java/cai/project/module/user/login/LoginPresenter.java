package cai.project.module.user.login;

import android.text.TextUtils;

import cai.project.module.common_mvp.BaseView;
import cai.project.module.common_mvp.presenter.BasePresenter;
import cai.project.module.common_utils.codeutils.ToastUtils;

public class LoginPresenter extends BasePresenter<LoginView> {


    /**用户登录*/
    public void login(){
     if (TextUtils.isEmpty( getView().getUserName())){
         ToastUtils.showShort("请输入用户名");
         return;
     }

        if (TextUtils.isEmpty( getView().getPassWord())){
            ToastUtils.showShort("请输入用户名");
            return;
        }


        ToastUtils.showShort("登录成功"+getView().getPassWord());
    }

}
