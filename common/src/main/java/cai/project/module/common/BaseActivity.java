package cai.project.module.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    /*
    对应的点击事件等，如果是单个使用，也是使用R2.id.xx。如果是多个id一起使用，内部通过id来判断，
    则需要使用if...else if...，不能使用switch...case，并且if判断的id需要使用R.id.xx默认是会报错，
    找不到R2相关的class，需要手动build一次才会生成。注意：ButterKnife.9.0以后，需要jdk版本1.8以上，否则编译会报错。
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
