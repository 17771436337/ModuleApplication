package cai.project.module.accountmanagement.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import cai.project.module.accountmanagement.R;

public class AddMessageView extends BaseView {

    private EditText tvTitle;
    private EditText tvMessage;
    private ImageView ivDelete;

    public AddMessageView(Context context) {
        super(context);
    }

    public AddMessageView(Context context,String title) {
        super(context);
        setTitle(title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.accountmanagement_view_add_message;
    }

    @Override
    protected void init() {
       initView();
    }

    private void initView() {
        tvTitle = (EditText) $(R.id.tv_title);
        tvMessage = (EditText) $(R.id.tv_message);
        ivDelete = (ImageView) $(R.id.iv_delete);
    }

    //----------------------------

    public void setTitle(CharSequence text){
        tvTitle.setText(text);
    }

    public void setMessage(CharSequence text){
        tvMessage.setText(text);
    }


    /**是否显示删除*/
    public void showDelete(boolean is){
        if (is){
            ivDelete.setVisibility(View.VISIBLE);
        }else{
            ivDelete.setVisibility(View.GONE);
        }
    }


}
