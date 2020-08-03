package cai.project.module.common;

import butterknife.ButterKnife;
import cai.project.module.common_mvp.BaseMvpActivity;
import cai.project.module.common_mvp.presenter.BasePresenter;

/**基类，实现相关绑定*/
public abstract class BaseActivity<P extends BasePresenter> extends BaseMvpActivity<P> {

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

}
