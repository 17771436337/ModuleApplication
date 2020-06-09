package cai.project.module.web.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.web.R;
import cai.project.module.web.R2;
import cai.project.module.web.activity.x5.RichWebViewActivity;
import cai.project.module.web.activity.x5.X5WebViewActivity;

public class TestActivity extends AppCompatActivity {

    @BindView(R2.id.bt_test)
    Button btTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_test_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.bt_test,R2.id.bt_rich})
    public void onClick(View view) {

            if (view.getId() == R.id.bt_test){
            X5WebViewActivity.loadUrl(this,"http://www.baidu.com","百度");
        }else
            if (view.getId() == R.id.bt_rich){
                startActivity(new Intent(this,RichWebViewActivity.class));
        }

    }
}
