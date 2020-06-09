package cai.project.module.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.common_utils.ToolsUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main_activity);
        ButterKnife.bind(this);
    }


    @OnClick(R2.id.bt_test)
    public void onClick(View view) {
        if (view.getId() == R.id.bt_test){
            if (ToolsUtils.checkApkExist(this, "com.ARTest.cn")) {
                Intent intent = new Intent();
//                intent.setAction();
                intent.setClassName("com.ARTest.cn", "com.unity3d.player.UnityPlayerActivity");
                intent.putExtra("data", "我是A的值");
                startActivity(intent);
            } else {
                Toast.makeText(this, "请安装对应的app", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
