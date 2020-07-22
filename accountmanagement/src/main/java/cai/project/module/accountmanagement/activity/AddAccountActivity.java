package cai.project.module.accountmanagement.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.R;
import cai.project.module.accountmanagement.view.AddMessageView;

public class AddAccountActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.add_content)
    LinearLayout addContent;

    ArrayList <AddMessageView> messageViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmanagement_activity_add);
        ButterKnife.bind(this);
        
        initData();

    }

    private void initData() {
        messageViews = new ArrayList<>();

        messageViews.add(new AddMessageView(this,"账号"));
        messageViews.add(new AddMessageView(this,"密码"));
        messageViews.add(new AddMessageView(this,"备注"));

        for (AddMessageView addMessage:messageViews) {
            addContent.addView(addMessage.toView());
        }

    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        
    }
}
