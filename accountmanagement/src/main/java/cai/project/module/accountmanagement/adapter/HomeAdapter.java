package cai.project.module.accountmanagement.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import cai.project.module.accountmanagement.R;
import cai.project.module.common.adapter.helper.WeSwipeHelper;
import cai.project.module.common_basis.adapter.BaseRecyclerHolder;
import cai.project.module.common_basis.adapter.SingleAdapter;
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_utils.codeutils.ScreenUtils;
import cai.project.module.common_utils.codeutils.SizeUtils;
import cai.project.module.common_utils.codeutils.TimeUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;

public class HomeAdapter  extends SingleAdapter<AccountEntity> {

    public HomeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void bindData(BaseRecyclerHolder holder, AccountEntity bean) {
        holder.setText(R.id.tv_name,bean.getAccountMessages().get(0).getName()+":"+bean.getAccountMessages().get(0).getDetail());
        holder.setText(R.id.tv_time,TimeUtils.millis2String(bean.getTime()));

    }


    public AccountEntity getData(int i){
        return items.get(i);
    }


}
