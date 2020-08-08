package cai.project.module.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

import cai.project.module.common_arouter.HomePath;
import cai.project.module.common_view.widget.CustomToolBar;

@Route(path = HomePath.ACTIVITY_MAIN)
public class MainActivity extends AppCompatActivity {
    CustomToolBar  titleView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        titleView = findViewById(R.id.title);
        titleView.setTitle("这是一个标题");
    }
}
