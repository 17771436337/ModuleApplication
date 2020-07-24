package cai.project.module.accountmanagement.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cai.project.module.accountmanagement.Constants;
import cai.project.module.accountmanagement.R;
import cai.project.module.accountmanagement.model.type.AddMessageType;

public class AddMessageView extends BaseView implements View.OnClickListener {

    private EditText tvTitle;
    private EditText tvMessage;
    private ImageView ivDelete;

    private @AddMessageType int type;

    private ClickListener onClick;//监听接口

    private Long id;



    public AddMessageView(Context context,@AddMessageType int type) {
        super(context);
        setShowDelete(type);
    }

    public AddMessageView(Context context,@AddMessageType int type,String title) {
        super(context);

        setTitle(title);
        setShowDelete(type);
    }

    /**设置是否隐藏删除按钮*/
    public void setShowDelete(@AddMessageType int type){
        this.type = type;
        switch (type){
            case Constants
                    .ACCOUNT_MESSAGE_ERASABLE:
                showDelete(true);
                break;
            case Constants.ACCOUNT_MESSAGE_NOT_DELETE:
                showDelete(false);
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.accountmanagement_view_add_message;
    }

    @Override
    protected void init() {
       initView();
       initListener();
    }



    private void initView() {
        tvTitle = (EditText) $(R.id.tv_title);
        tvMessage = (EditText) $(R.id.tv_message);
        ivDelete = (ImageView) $(R.id.iv_delete);
    }
    private void initListener() {
        ivDelete.setOnClickListener(this);
    }

    //----------------------------

    public void setTitle(CharSequence text){
        if (tvTitle != null) {
            tvTitle.setText(text);
        }
    }

    public void setMessage(CharSequence text){
        tvMessage.setText(text);
    }


    /**获得标题名称*/
    public String getTitle(){
        return tvTitle.getText().toString().trim();
    }

    /**获得信息内容*/
    public String getContent(){
        return tvMessage.getText().toString().trim();
    }


    /**是否显示删除*/
    public void showDelete(boolean is){
        if (is){
            ivDelete.setVisibility(View.VISIBLE);
        }else{
            ivDelete.setVisibility(View.GONE);
        }
    }


    /**设置监听接口*/
    public void setOnClickListener(ClickListener listener){
            this.onClick = listener;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public Long getId(){
        return id;
    }

    //----------------------------------

    @Override
    public void onClick(View v) {
        if (onClick != null){
            onClick.onDelete(this);
        }
    }




    /**监听接口*/
    public interface ClickListener{
       void onDelete(AddMessageView view);
    }

}
