package cai.project.module.common_mvp.presenter;

import android.os.Bundle;
import cai.project.module.common_mvp.BaseView;

/**
 * 控制器接口：
 * 定义P层生命周期与 V层同步
 */
public interface IPresenter <V extends BaseView> {
    void onMvpAttachView(V view, Bundle savedInstanceState);

    void onMvpStart();

    void onMvpResume();

    void onMvpPause();

    void onMvpStop();

    void onMvpSaveInstanceState(Bundle savedInstanceState);

    void onMvpDetachView(boolean retainInstance);

    void onMvpDestroy();
}
