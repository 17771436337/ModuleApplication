package cai.project.module.common_basis.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**单数据布局*/
public abstract class SingleAdapter<T> extends BaseRecylerAdapter<T> {

    private @LayoutRes int layoutId;
    public SingleAdapter(Context context, int layoutId) {
        super(context);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return BaseRecyclerHolder.createViewHolder(viewGroup.getContext(),inflater.inflate(layoutId,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerHolder baseRecyclerHolder, int i) {
        bindData(baseRecyclerHolder,items.get(i));
    }

    public abstract void bindData(BaseRecyclerHolder holder,T item);
}
