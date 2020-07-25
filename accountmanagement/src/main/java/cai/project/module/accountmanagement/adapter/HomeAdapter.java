package cai.project.module.accountmanagement.adapter;

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
import cai.project.module.common_database.entity.account.AccountEntity;
import cai.project.module.common_utils.codeutils.ScreenUtils;
import cai.project.module.common_utils.codeutils.SizeUtils;
import cai.project.module.common_utils.codeutils.TimeUtils;
import cai.project.module.common_utils.codeutils.ToastUtils;

public class HomeAdapter  extends RecyclerView.Adapter<HomeAdapter.ViewHolde> {

    List<AccountEntity> date;

    public HomeAdapter(List<AccountEntity> date) {
        this.date = date;
    }


    /**
     * just is empty
     *
     * @param dataList
     */
    public void checkData(List<AccountEntity> dataList) {
        if (dataList == null) {
            dataList = Collections.emptyList();
        }
        this.date = dataList;

        notifyDataSetChanged();
//        if (date != null && date.size() > 0) {
//            notifyItemMoved(0, date.size() - 1);
//        }else{
//            notifyDataSetChanged();
//        }
    }

    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.accountmanagement_item_home_message, viewGroup, false);
        ViewHolde holde = new ViewHolde(view);
        return holde;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde viewHolde, int i) {
        AccountEntity bean = date.get(i);

        viewHolde.tvTime.setText(TimeUtils.millis2String(bean.getTime()));
        viewHolde.tvName.setText(bean.getAccountMessages().get(0).getName()+":"+bean.getAccountMessages().get(0).getDetail());
        if (listener != null ) {
            viewHolde.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, i, i >= 0 ? date.get(i) : null);
                }
            });

            viewHolde.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDelete(v,i, i >= 0 ? date.get(i) : null);
                }
            });

            viewHolde.tvLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtils.showShort("请等待更新");
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return date == null?0:date.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolde extends RecyclerView.ViewHolder  implements WeSwipeHelper.SwipeLayoutTypeCallBack{

        TextView tvName;
        TextView tvTime;
        TextView tvDelete;
        TextView tvLabel;
        RelativeLayout item;
        RelativeLayout slideItem;


        public ViewHolde(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            tvLabel = itemView.findViewById(R.id.tv_label);
            item = itemView.findViewById(R.id.item);
            slideItem = itemView.findViewById(R.id.slide_itemView);
        }

        @Override
        public float getSwipeWidth() {
            return SizeUtils.dp2px(150);
        }

        @Override
        public View needSwipeLayout() {
            return slideItem;
        }

        @Override
        public View onScreenView() {
            return item;
        }
    }
    public OnItemClickListener listener;

    public interface OnItemClickListener<T>{
        void onClick(View v, int pos, T data);
        void onDelete(View v,int pos, T data);
    }
}
