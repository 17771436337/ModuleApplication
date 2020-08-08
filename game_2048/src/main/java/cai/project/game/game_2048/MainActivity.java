package cai.project.game.game_2048;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import cai.project.game.game_2048.view.Game2048Layout;
import cai.project.module.common.BaseActivity;
import cai.project.module.common_arouter.GamePath;
import cai.project.module.common_mvp.presenter.BasePresenter;


@Route(path = GamePath.GAME_2018)
public class MainActivity extends BaseActivity implements Game2048Layout.OnGame2048Listener {

    private Game2048Layout mGame2048Layout;

    private TextView mScore;

    @Override
    public void initView() {
        super.initView();
        mScore = (TextView) findViewById(R.id.id_score);
        mGame2048Layout = (Game2048Layout) findViewById(R.id.id_game2048);
        mGame2048Layout.setOnGame2048Listener(this);
    }

    @Override
    protected void getIntents(Intent intent) {

    }
    @Override
    public void initData() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }

    @Override
    public void onScoreChange(int score) {
        mScore.setText("SCORE: " + score);
    }

    @Override
    public void onGameOver() {
        new AlertDialog.Builder(this).setTitle("GAME OVER")
                .setMessage("YOU HAVE GOT " + mScore.getText())
                .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mGame2048Layout.restart();
                    }
                }).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_game2048_main;
    }


}
