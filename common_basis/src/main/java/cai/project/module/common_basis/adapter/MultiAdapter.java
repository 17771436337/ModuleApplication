package cai.project.module.common_basis.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**多布局单实体*/
public abstract class MultiAdapter<T> extends  BaseRecylerAdapter<T> {

    protected Map<Integer,Integer> layoutMap = new HashMap<>();

    public MultiAdapter(Context context,int[] layoutIds) {
        super(context);
        for (int i = 0; i < layoutIds.length; i++) {
            layoutMap.put(i,layoutIds[i]);
        }
    }

    @NonNull
    @Override
    public BaseRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return BaseRecyclerHolder.createViewHolder(viewGroup.getContext(),inflater.inflate(getLayoutType(viewType),viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerHolder baseRecyclerHolder, int i) {
        bindData(mContext,baseRecyclerHolder,items.get(i),bindLayout(items.get(i),i),i);
    }

    @Override
    public int getItemViewType(int position) {
        int layoutId = bindLayout(items.get(position),position);
        return getViewType(layoutId);
    }


    /**根据ViewType获取对应的布局*/
    public int getLayoutType(int viewType){
        return layoutMap.get(viewType);
    }
    /**根据layoutId获取对应的ViewType*/
    public int getViewType(int layoutId){
        Iterator iter = layoutMap.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            Integer key = (Integer) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (val == layoutId){
                return key;
            }

        }
        return 0;

    }

    /**获取每个item的viewType*/
    public abstract int bindLayout(T item,int viewType);
    /**绑定数据源*/
    public abstract void bindData(Context context,BaseRecyclerHolder holder,T item,int layoutId,int position);

}
