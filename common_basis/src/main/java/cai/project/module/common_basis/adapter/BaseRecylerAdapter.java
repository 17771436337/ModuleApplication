package cai.project.module.common_basis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRecylerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder>{

    protected Context mContext;
    protected LayoutInflater inflater;
    protected List<T> items = new ArrayList<>();

    public BaseRecylerAdapter(Context context) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<T> list){
        if (list == null){
            list = Collections.emptyList();
        }
        this.items = list;
        notifyDataSetChanged();
    }

}
