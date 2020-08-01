package cai.project.module.accountmanagement.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.R;
import cai.project.module.accountmanagement.model.type.StateType;
import cai.project.module.accountmanagement.view.AddMessageView;
import cai.project.module.common_database.DaoUtils;
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_database.entity.account.AccountMessageEntity;
import cai.project.module.common_utils.codeutils.ToastUtils;


public class AddAccountActivity extends AppCompatActivity implements AddMessageView.ClickListener {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.add_content)
    LinearLayout addContent;

    ArrayList<AddMessageView> messageViews;
    @BindView(R.id.iv_add)
    ImageView ivAdd;

    private @StateType int type;
    private Long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountmanagement_activity_add);
        ButterKnife.bind(this);
        getIntents(getIntent());
        initData();
        initListener();

    }

    private void initListener() {
        for (AddMessageView addMessage : messageViews) {
            addMessage.setOnClickListener(this);
        }
    }

    private void initData() {
        messageViews = new ArrayList<>();
        switch (type){
            case Constants.ADD://添加


                messageViews.add(new AddMessageView(this, Constants.ACCOUNT_MESSAGE_NOT_DELETE,"账号"));
                messageViews.add(new AddMessageView(this,Constants.ACCOUNT_MESSAGE_NOT_DELETE, "密码"));
                messageViews.add(new AddMessageView(this, Constants.ACCOUNT_MESSAGE_ERASABLE,"备注"));

                for (AddMessageView addMessage : messageViews) {
                    addContent.addView(addMessage.toView());
                }
                break;
                case Constants.EDITOR://编辑
                    AccountEntity data = DaoUtils.getAccountDao().getAccount(id);
                    data.resetAccountMessages();
                    if (data != null) {
                        for (AccountMessageEntity message :data.getAccountMessages()) {
                            AddMessageView addMessageView =  new AddMessageView(this, Constants.ACCOUNT_MESSAGE_NOT_DELETE);
                            addMessageView.setTitle(message.getName());
                            addMessageView.setMessage(message.getDetail());
                            if (message.getSORTNO() == 1 || message.getSORTNO() == 2){
                                addMessageView.setShowDelete( Constants.ACCOUNT_MESSAGE_NOT_DELETE);
                            }else{
                                addMessageView.setShowDelete( Constants.ACCOUNT_MESSAGE_ERASABLE);
                            }
                            addMessageView.setId(message.getId());
                            messageViews.add(addMessageView);

                        }

                        for (AddMessageView addMessage : messageViews) {
                            addContent.addView(addMessage.toView());
                        }
                    }else{

                        type = Constants.ADD;
                        messageViews = new ArrayList<>();

                        messageViews.add(new AddMessageView(this, Constants.ACCOUNT_MESSAGE_NOT_DELETE,"账号"));
                        messageViews.add(new AddMessageView(this,Constants.ACCOUNT_MESSAGE_NOT_DELETE, "密码"));
                        messageViews.add(new AddMessageView(this, Constants.ACCOUNT_MESSAGE_ERASABLE,"备注"));

                        for (AddMessageView addMessage : messageViews) {
                            addContent.addView(addMessage.toView());
                        }
                    }

                    break;

        }


    }

    @OnClick({R.id.iv_back,R.id.iv_add,R.id.tv_complete})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back){
            finish();
        }else if (id == R.id.tv_complete){
            if (messageViews != null && messageViews.size() > 0){
                if(!TextUtils.isEmpty(messageViews.get(0).getContent())) {
                int SORTNO = 1;
                switch (type){
                    case Constants.ADD:
                            Long accountId = DaoUtils.getAccountDao().addAccount(messageViews.get(0).getContent());
                            for (AddMessageView message : messageViews) {
                                DaoUtils.getAccountDao().addAccountMessage(accountId, message.getTitle(), message.getContent(), SORTNO++);
                            }
                            finish();
                        break;
                    case Constants.EDITOR://修改
                        DaoUtils.getAccountDao().editAccount(this.id,messageViews.get(0).getContent());
                        for (AddMessageView message:messageViews) {
                            DaoUtils.getAccountDao().editAccountMessage(message.getId(),this.id,message.getTitle(),message.getContent(),SORTNO++);
                        }
                        finish();
                        break;
                }
                }else{
                    ToastUtils.showShort("请输入账号信息");
                }

            }


        }else if (id == R.id.iv_add){
            AddMessageView addMessageView = new AddMessageView(this,Constants.ACCOUNT_MESSAGE_ERASABLE);
            addMessageView.setOnClickListener(this);
            messageViews.add(addMessageView);
            addContent.addView(addMessageView.toView());
        }

    }


    @Override
    public void onDelete(AddMessageView view) {
        addContent.removeView(view.toView());
        messageViews.remove(view);
    }

    /**跳转页面*/
    public static void startAccountMessage(Context context, @StateType int type,Long id){
        Intent intent = new Intent(context,AddAccountActivity.class);
        intent.putExtra("TYPE",type);
        intent.putExtra("ID",id);
        context.startActivity(intent);
    }


    private void getIntents(Intent intent){
        type = intent.getIntExtra("TYPE",Constants.ADD);
        id = intent.getLongExtra("ID",0);
    }

}
