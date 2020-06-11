package cai.project.module.androidtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cai.project.module.androidtest.adapter.PlanetsAdapter;
import cai.project.module.common_view.animation.snow.FallObject;
import cai.project.module.common_view.animation.snow.FallingView;
import cai.project.module.common_view.animation.star_cloud.view.PlanetsView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main_activity);




    }


}
