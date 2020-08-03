package cai.project.module.common;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cai.project.module.common_mvp.BaseMvpFragment;
import cai.project.module.common_mvp.presenter.BasePresenter;
public abstract class BaseFramnet<P extends BasePresenter> extends BaseMvpFragment<P> {

    Unbinder unbinder;
    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, mRootView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
