package cai.project.module.moduleapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.common_arouter.GamePath;
import cai.project.module.common_arouter.HomePath;
import cai.project.module.common_arouter.UserPath;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_home, R.id.bt_user,R.id.bt_game2048,R.id.bt_game_teris})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_home:
                ARouter.getInstance().build(HomePath.ACTIVITY_MAIN).navigation();
                break;
            case R.id.bt_user:
                ARouter.getInstance().build(UserPath.ACTIVITY_MAIN).navigation();
                break;
            case R.id.bt_game2048:
                ARouter.getInstance().build(GamePath.GAME_2018).navigation();
                break;
            case R.id.bt_game_teris:
                ARouter.getInstance().build(GamePath.GAME_TERIS).navigation();
                break;
        }
    }
}
