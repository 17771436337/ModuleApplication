package cai.project.module.common_basis.adapter;


public class LayoutWrapper<T> {
    private int layoutId;//布局Id
    private int spanSize;//布局所占列数
    private T data;//数据源
    private Dataholder<T> holder;

    /**设置每个item所占列数*/
    public LayoutWrapper(int layoutId, int spanSize, T data, Dataholder<T> holder) {
        this.layoutId = layoutId;
        this.spanSize = spanSize;
        this.data = data;
        this.holder = holder;
    }

    /**设置每个item默认只占一列*/
    public LayoutWrapper(int layoutId, T data, Dataholder<T> holder) {
        this.layoutId = layoutId;
        this.data = data;
        this.holder = holder;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Dataholder<T> getHolder() {
        return holder;
    }

    public void setHolder(Dataholder<T> holder) {
        this.holder = holder;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
}
