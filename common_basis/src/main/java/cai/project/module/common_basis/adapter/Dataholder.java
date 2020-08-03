package cai.project.module.common_basis.adapter;

import android.content.Context;

/**
 *数据控制器
 * 将item与holder进行绑定
 * @param <T>
 */
public interface Dataholder<T> {
    void bind(Context context,BaseRecyclerHolder holder,T item,int position);
}
