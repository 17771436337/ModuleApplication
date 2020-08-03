package cai.project.module.common_basis.adapter;
import android.content.Context;

/**多布局中，从单实体到多实体*/
public class SuperAdapter extends MultiAdapter<LayoutWrapper> {

    public SuperAdapter(Context context,int[] layoutIds) { super(context, layoutIds); }


    @Override
    public int bindLayout(LayoutWrapper item, int viewType) {
        return item.getLayoutId();
    }

    @Override
    public void bindData(Context context, BaseRecyclerHolder holder, LayoutWrapper item, int layoutId, int position) {
        LayoutWrapper wrapper = items.get(position);
        wrapper.getHolder().bind(mContext,holder,wrapper.getData(),position);
    }
}
